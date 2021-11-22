import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/group/group_request_provider.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';
import 'package:photo_manager/photo_manager.dart';

class TopBox extends ConsumerWidget {
  const TopBox({Key? key}) : super(key: key);

  Future<bool> photoManager() async {
    PermissionState result = await PhotoManager.requestPermissionExtend();
    if (result.isAuth) {
      return true;
    } else {
      return false;
    }
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final img = ref.watch(groupRequestProvider).imageUrl;

    return SliverToBoxAdapter(
      child: SizedBox(
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
                    fit: BoxFit.fill,
                  ),
            Padding(
              padding: const EdgeInsets.only(left: 16, top: 16),
              child: InkWell(
                onTap: () {
                  ref.read(navigatorProvider).pop();
                },
                child: const Icon(
                  CupertinoIcons.back,
                  color: MomoColor.black,
                ),
              ),
            ),
            Align(
              alignment: Alignment.bottomRight,
              child: Padding(
                padding: const EdgeInsets.only(right: 8, bottom: 8),
                child: InkWell(
                  onTap: () {
                    ref.read(navigatorProvider).pop();
                  },
                  child: Container(
                    width: 42,
                    height: 42,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(16),
                      color: const Color(0xffffffff),
                    ),
                    child: InkWell(
                      onTap: () async {
                        final check = await photoManager();
                        if (check) {
                          // String? imagePath = await ref
                          //     .read(navigatorProvider)
                          //     .navigateTo(routeName: AppRoutes.gallery);
                          // ref
                          //     .read(newMeetStateProvider.notifier)
                          //     .setImage(imagePath!);
                        }
                      },
                      child: const Icon(
                        CupertinoIcons.photo,
                        color: MomoColor.black,
                      ),
                    ),
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
