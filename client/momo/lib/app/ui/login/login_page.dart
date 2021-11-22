import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:kakao_flutter_sdk/all.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';

import 'dart:developer' as dp;

import 'package:momo/app/util/theme.dart';

class LoginPage extends ConsumerStatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  ConsumerState<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends ConsumerState<LoginPage> {
  final dio = Dio();

  Future<void> loginWithKakao() async {
    String? authCode;
    try {
      authCode = await AuthCodeClient.instance.requestWithTalk();

      dp.log('authCode: $authCode');
    } on KakaoAuthException catch (e) {
      dp.log('KakaoAuthException: ${e.toString()}');
    } on KakaoClientException catch (e) {
      dp.log('KakaoClientException: ${e.toString()}');
    } catch (e) {
      dp.log('OnotherException: ${e.toString()}');
    }

    final response = await dio.post(
      'http://localhost:8080/api/oauth/login',
      data: {
        'authorizationCode': '$authCode',
        'provider': 'kakao',
      },
    );
    dp.log('${response.data}');
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
                  // await loginWithKakao();
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
}
