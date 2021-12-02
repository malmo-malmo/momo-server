import 'dart:developer' as dp;

import 'package:device_info_plus/device_info_plus.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:kakao_flutter_sdk/all.dart';
import 'package:momo/app/provider/auth/token_provider.dart';
import 'package:momo/app/provider/user/category_result_provider.dart';
import 'package:momo/app/provider/user/location_result_provider.dart';
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

  Future<void> loginWithKakao() async {
    final isGPhone = await getDeviceInfo();
    baseUrl =
        isGPhone ? 'http://10.0.2.2:8080/api' : 'http://192.168.0.2:8080/api';

    final authCode = await getAuthCode();

    final response = await dio.post(
      '$baseUrl/oauth/login',
      data: {
        'authorizationCode': '$authCode',
        'provider': 'kakao',
      },
    );
    ref.watch(tokenStateProvider.state).state = response.data['accessToken'];
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
              InkWell(
                onTap: () async {
                  await loginWithKakao();
                  ref
                      .read(navigatorProvider)
                      .navigateToRemove(routeName: AppRoutes.trems);
                },
                child: Image.asset(
                  'assets/image/kakao_login_large_wide.png',
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  Future<bool> getDeviceInfo() async {
    DeviceInfoPlugin deviceInfo = DeviceInfoPlugin();
    AndroidDeviceInfo androidInfo = await deviceInfo.androidInfo;
    return androidInfo.model == 'sdk_gphone_x86' ? true : false;
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
