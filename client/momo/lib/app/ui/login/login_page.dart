import 'dart:developer' as dp;

import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:kakao_flutter_sdk/all.dart';
import 'package:momo/app/model/common/token_data.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';
import 'package:momo/main.dart';

class LoginPage extends ConsumerStatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  ConsumerState<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends ConsumerState<LoginPage> {
  final dio = Dio();

  Future<void> _loginWithKakao() async {
    final authCode = await getAuthCode();

    final response = await dio.post(
      '$baseUrl/oauth/login',
      data: {
        'authorizationCode': '$authCode',
        'provider': 'kakao',
      },
    );

    final tokenData = TokenData(
      accessToken: response.data['accessToken'],
      accessTokenType: response.data['accessTokenType'],
      refreshToken: response.data['refreshToken'],
    );

    dp.log('$tokenData');
    Hive.box('auth').put('tokenData', tokenData);

    await getLocatinosAndCategories();
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.main,
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 56),
          child: SizedBox(
            height: double.infinity,
            width: double.infinity,
            child: Stack(
              alignment: Alignment.center,
              children: [
                SvgPicture.asset('assets/icon/ic_momo.svg'),
                Align(
                  alignment: Alignment.bottomCenter,
                  child: InkWell(
                    onTap: () async {
                      await _loginWithKakao();
                      ref
                          .read(navigatorProvider)
                          .navigateToRemove(routeName: AppRoutes.trems);
                    },
                    child: Container(
                      height: 56,
                      width: double.infinity,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(20),
                        color: const Color(0xfffee500),
                      ),
                      child: Stack(
                        alignment: Alignment.center,
                        children: [
                          const Align(
                            alignment: Alignment.centerLeft,
                            child: Padding(
                              padding: EdgeInsets.only(left: 25),
                              child: Icon(
                                CupertinoIcons.chat_bubble_fill,
                                color: Color(0xff191700),
                                size: 18,
                              ),
                            ),
                          ),
                          Text(
                            '카카오로 시작하기',
                            style: MomoTextStyle.defaultStyle.copyWith(
                              fontSize: 15.sp,
                              color: const Color(0xff191919),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Future<void> getLocatinosAndCategories() async {
    await ref.watch(categoryResultProvider.future);
    await ref.watch(locationResultProvider.future);
  }

  Future<String?> getAuthCode() async {
    String? authCode;
    try {
      final check = await isKakaoTalkInstalled();
      authCode = check
          ? await AuthCodeClient.instance.requestWithTalk()
          : await AuthCodeClient.instance.request();
    } on KakaoAuthException catch (e) {
      dp.log('KakaoAuthException: ${e.toString()}');
    } on KakaoClientException catch (e) {
      dp.log('KakaoClientException: ${e.toString()}');
    } catch (e) {
      dp.log('OnotherException: ${e.toString()}');
    }
    return authCode;
  }
}
