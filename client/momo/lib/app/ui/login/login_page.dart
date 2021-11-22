import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_web_auth/flutter_web_auth.dart';
import 'package:kakao_flutter_sdk/all.dart';
import 'package:momo/app/util/kakao_login.dart';

import 'dart:developer' as dp;

import 'package:momo/app/util/theme.dart';

class LoginPage extends ConsumerStatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  ConsumerState<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends ConsumerState<LoginPage> {
  //  카카오 계정 로그인 후 서버로 code 전송
  Future<void> loginWithKakao() async {
    dp.log('>>>>>>>> 로그인 요청 <<<<<<<<<<');
    String? authCode;
    try {
      //  카카오톡으로 인가코드 요청
      authCode = await AuthCodeClient.instance.requestWithTalk();

      dp.log('authCode: $authCode');
    } on KakaoAuthException catch (e) {
      dp.log('KakaoAuthException: ${e.toString()}');
    } on KakaoClientException catch (e) {
      dp.log('KakaoClientException: ${e.toString()}');
    } catch (e) {
      dp.log('OnotherException: ${e.toString()}');
    }

    //  서버로 요청할 주소
    final authUri =
        'http://localhost:8080/login/oauth2/code/kakao?code=$authCode';
    // final authUri =
    //     'http://localhost:8080/login/oauth2/code/kakao?code=$authCode&redirect_uri=webauthcallback://login/redirect';
    dp.log('authUri: $authUri');

    //  서버로 코드와 redirectUri와 함께 요청
    final authResponse = await FlutterWebAuth.authenticate(
      url: authUri,
      callbackUrlScheme: 'webauthcallback',
    );
    dp.log('authResponse: $authResponse');

    //  넘어온 uri에서 토큰 추출
    final token = Uri.parse(authResponse).queryParameters['token'];

    // 결과 확인
    dp.log('token: $token');
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
                  // ref
                  //     .read(navigatorProvider)
                  //     .navigateToRemove(routeName: AppRoutes.trems);
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

  Future<void> getKakaoAccessToken() async {
    /**
     *  다이렉스 로그인 --> 바로 엑세스 토큰 발급
     */

    final kakaoLogin = ref.watch(flutterKakaoLoginProvider);
    try {
      final result = await kakaoLogin.logIn();
      dp.log(result.token!.accessToken!);
    } catch (e) {
      dp.log(e.toString());
    }
  }
}
