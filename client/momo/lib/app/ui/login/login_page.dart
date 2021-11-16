import 'package:flutter/material.dart';
import 'package:flutter_web_auth/flutter_web_auth.dart';
import 'dart:io' show HttpServer;

import 'dart:developer' as dp;

import 'package:momo/app/ui/login/login_helper.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  String resultToken = 'No token';

  Future<void> loginWithKakao() async {
    dp.log('>>>>>>>> Flutter Server Open <<<<<<<<<');
    final server = await HttpServer.bind('127.0.0.1', 43823);

    server.listen((req) async {
      if (req.method == 'GET') {
        dp.log('>>>>>>>>> ${req.uri} <<<<<<<<');
      }
    });
    final authUri = Uri.https(
      'http://localhost:8080',
      '/oauth2/authorize/kakao',
      {
        // 'redirect_uri': 'http://localtest.me:43823/',
        'redirect_uri': 'webauthcallback://',
      },
    );

    // token과 함께 uri가 넘어옴
    final authResponse = await FlutterWebAuth.authenticate(
      url: authUri.toString(),
      callbackUrlScheme: 'webauthcallback',
    );

    //  넘어온 uri에서 토큰 추출
    final token = Uri.parse(authResponse).queryParameters['token'];

    setState(() {
      resultToken = token!;
    });
    // 결과 확인
    dp.log('>>>>>>>> $token <<<<<<<<<<');
    await server.close();
    dp.log('>>>>>>>> Flutter Server Close <<<<<<<<<');
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 24),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: [
              const Text(
                'KaKaoLogin',
                style: TextStyle(
                  fontSize: 32,
                ),
              ),
              Text(
                'resultToken: $resultToken',
                style: const TextStyle(
                  fontSize: 32,
                ),
              ),
              InkWell(
                onTap: () async {
                  await loginWithKakao();
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
