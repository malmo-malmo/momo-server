import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/model/enum/photo_request_type.dart';
import 'package:momo/app/provider/group/group_request_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:photo_manager/photo_manager.dart';

class SetImageBox extends ConsumerWidget {
  const SetImageBox({Key? key, required this.img}) : super(key: key);

  Future<bool> photoManager() async {
    PermissionState result = await PhotoManager.requestPermissionExtend();
    return result.isAuth ? true : false;
  }

  final String img;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Container(
      color: MomoColor.backgroundColor,
      width: double.infinity,
      height: 215.h,
      child: Stack(
        children: [
          img.isEmpty
              ? SizedBox(
                  height: 215.h,
                  width: double.infinity,
                  child: const Center(
                    child: Text('등록된 배경사진이 없습니다'),
                  ),
                )
              : Image.file(
                  File(img),
                  height: 215.h,
                  width: double.infinity,
                  fit: BoxFit.cover,
                ),
          Align(
            alignment: Alignment.bottomRight,
            child: Padding(
              padding: const EdgeInsets.only(right: 24, bottom: 20),
              child: InkWell(
                onTap: () {
                  ref.read(navigatorProvider).pop();
                },
                child: InkWell(
                  onTap: () async {
                    final check = await photoManager();
                    if (check) {
                      String? imagePath =
                          await ref.read(navigatorProvider).navigateTo(
                                routeName: AppRoutes.gallery,
                                arguments: PhotoRequestType.one,
                              );
                      if (imagePath != null) {
                        ref
                            .read(groupRequestStateProvider.notifier)
                            .setImageUrl(imagePath);
                      }
                    }
                  },
                  child: SvgPicture.asset(
                    'assets/icon/btn_camera_32.svg',
                    color: img.isEmpty ? null : MomoColor.flutterWhite,
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
