import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:kakao_flutter_sdk/all.dart';
import 'package:momo/app/model/common/token_data.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/provider_log.dart';
import 'package:momo/app/util/theme.dart';

void main() async {
  await Hive.initFlutter();
  Hive.registerAdapter(TokenDataAdapter());
  await Hive.openBox('auth');

  KakaoContext.clientId = '51af7920a3ab81a3de0020af102e70cd';

  runApp(
    ProviderScope(
      child: const MyApp(),
      observers: [
        Logger(),
      ],
    ),
  );
}

class MyApp extends ConsumerWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    SystemChrome.setEnabledSystemUIMode(SystemUiMode.manual);
    return ScreenUtilInit(
      designSize: const Size(360, 640),
      builder: () => MaterialApp(
        builder: (context, child) {
          return MediaQuery(
            data: MediaQuery.of(context).copyWith(textScaleFactor: 1),
            child: child!,
          );
        },
        debugShowCheckedModeBanner: false,
        navigatorKey: ref.watch(navigatorProvider).navigatorKey,
        scrollBehavior: MyBehavior(),
        initialRoute: AppRoutes.splash,
        theme: momoThemeData,
        onGenerateRoute: (settings) => AppRouter.onGenerateRoute(settings),
        localizationsDelegates: const [
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
          GlobalCupertinoLocalizations.delegate,
        ],
        supportedLocales: const [
          Locale('ko', 'KR'),
        ],
      ),
    );
  }
}
