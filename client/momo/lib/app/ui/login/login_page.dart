import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_web_auth/flutter_web_auth.dart';
import 'package:momo/app/routes/routes.dart';
import 'dart:io' show HttpServer;

import 'dart:developer' as dp;

import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class LoginPage extends ConsumerStatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  ConsumerState<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends ConsumerState<LoginPage> {
  String resultToken = 'No token';

  Future<void> loginWithKakao() async {
    dp.log('>>>>>>>> Flutter Server Open <<<<<<<<<');

    final authUri = Uri.http(
      'http://localhost:8080',
      '/oauth2/authorize/kakao',
      {
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
