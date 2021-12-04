import 'dart:developer' as dp;

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:kakao_flutter_sdk/all.dart';
import 'package:momo/app/model/token_data.dart';
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
    final _height = MediaQuery.of(context).size.height;
    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.main,
        body: Padding(
          padding: EdgeInsets.only(
              left: 24, right: 24, top: _height * 0.35, bottom: 56),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              SvgPicture.asset(
                'assets/icon/ic_momo.svg',
              ),
              // InkWell(
              //   onTap: () async {
              //     await _loginWithKakao();
              //     ref
              //         .read(navigatorProvider)
              //         .navigateToRemove(routeName: AppRoutes.trems);
              //   },
              //   child: Image.asset(
              //     'assets/image/kakao_login_large_wide.png',
              //   ),
              // )

              //  제플린 디자인
              SizedBox(
                width: double.infinity,
                height: 56,
                child: ElevatedButton(
                  onPressed: () async {
                    await _loginWithKakao();
                    ref
                        .read(navigatorProvider)
                        .navigateToRemove(routeName: AppRoutes.trems);
                  },
                  style: ElevatedButton.styleFrom(
                      primary: const Color(0xffffffff),
                      elevation: 0,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20),
                      )),
                  child: Text(
                    '카카오 계정으로 로그인',
                    style: MomoTextStyle.defaultStyle
                        .copyWith(color: MomoColor.main),
                  ),
                ),
              )
            ],
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
