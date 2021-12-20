import 'package:device_info_plus/device_info_plus.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class SplashPage extends ConsumerStatefulWidget {
  const SplashPage({Key? key}) : super(key: key);

  @override
  ConsumerState<SplashPage> createState() => _SplashPageState();
}

class _SplashPageState extends ConsumerState<SplashPage> {
  @override
  initState() {
    super.initState();
    _pushToNextPage();
  }

  Future<void> _pushToNextPage() async {
    final check = hasNoToken();

    if (check) {
      await Future.delayed(const Duration(seconds: 1));
      ref.read(navigatorProvider).navigateToRemove(routeName: AppRoutes.login);
    } else {
      await ref.watch(categoryResultProvider.future);
      await ref.watch(locationResultProvider.future);
      await ref.watch(userDataProvider.future);
      ref.read(navigatorProvider).navigateToRemove(routeName: AppRoutes.main);
    }
  }

  bool hasNoToken() {
    final token = Hive.box('auth').get('tokenData') ?? 'no token';
    return token == 'no token';
  }

  Future<bool> getDeviceInfo() async {
    DeviceInfoPlugin deviceInfo = DeviceInfoPlugin();
    AndroidDeviceInfo androidInfo = await deviceInfo.androidInfo;
    return androidInfo.model == 'sdk_gphone_x86' ? true : false;
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
}
