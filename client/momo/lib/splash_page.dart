import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/util/navigation_service.dart';

class SplashPage extends ConsumerStatefulWidget {
  const SplashPage({Key? key}) : super(key: key);

  @override
  ConsumerState<SplashPage> createState() => _SplashPageState();
}

class _SplashPageState extends ConsumerState<SplashPage> {
  @override
  initState() {
    super.initState();
    Future.delayed(Duration.zero).then((value) => _pushToNextPage());
  }

  Future<void> _pushToNextPage() async {
    final _tokenCheck = _hasNoToken();

    if (!_tokenCheck) {
      final _userDataCheck = await _isFirstLogin();
      await ref.watch(categoryResultProvider.future);
      await ref.watch(locationResultProvider.future);
      if (!_userDataCheck) {
        ref.read(navigatorProvider).navigateToRemove(
              routeName: AppRoutes.main,
            );
      } else {
        ref.read(navigatorProvider).navigateToRemove(
              routeName: AppRoutes.trems,
            );
      }
    } else {
      ref.read(navigatorProvider).navigateToRemove(
            routeName: AppRoutes.login,
          );
    }
  }

  Future<bool> _isFirstLogin() async {
    final _isFirstLogin =
        await ref.read(userDataProvider.notifier).isFirstLogin();
    return _isFirstLogin;
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.main,
        body: Stack(
          alignment: Alignment.center,
          children: [
            SvgPicture.asset('assets/icon/ic_momo.svg'),
            Align(
              alignment: Alignment.bottomCenter,
              child: Padding(
                padding: const EdgeInsets.only(bottom: 53),
                child: Text(
                  'Malmomalmo',
                  style: TextStyle(
                    fontFamily: 'BMJua',
                    fontSize: 19.sp,
                    fontWeight: FontWeight.w400,
                    color: MomoColor.white,
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  bool _hasNoToken() {
    final token = Hive.box('auth').get('tokenData') ?? 'no token';
    return token == 'no token';
  }
}
