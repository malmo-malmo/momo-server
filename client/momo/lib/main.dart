import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/provider_log.dart';
import 'package:momo/app/util/theme.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';

void main() async {
  await dotenv.load(fileName: ".env");

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
    SystemChrome.setEnabledSystemUIMode(
      SystemUiMode.manual,
      overlays: [
        SystemUiOverlay.top,
      ],
    );

    return ScreenUtilInit(
      designSize: const Size(360, 640),
      builder: () => MaterialApp(
        debugShowCheckedModeBanner: false,
        navigatorKey: ref.watch(navigatorProvider).navigatorKey,
        scrollBehavior: MyBehavior(),
        initialRoute: AppRoutes.login,
        theme: MomoThemeData,
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
