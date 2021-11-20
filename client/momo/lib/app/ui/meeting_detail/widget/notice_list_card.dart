import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class NoticeListCard extends ConsumerWidget {
  const NoticeListCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
      height: 182,
      color: const Color(0xffffffff),
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                '공지사항',
                style: MomoTextStyle.subTitle,
              ),
              InkWell(
                onTap: () {
                  ref
                      .read(navigatorProvider)
                      .navigateTo(routeName: AppRoutes.noticeList);
                },
                child: Transform.rotate(
                  angle: pi,
                  child: Icon(
                    CupertinoIcons.back,
                    color: MomoColor.black,
                    size: 24.w,
                  ),
                ),
              ),
            ],
          ),
          const SizedBox(height: 17),
          SizedBox(
            height: 86,
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemBuilder: (context, index) {
                return InkWell(
                  onTap: () {
                    ref.read(navigatorProvider).navigateTo(
                          routeName: AppRoutes.postDetail,
                          arguments: index,
                        );
                  },
                  child: Container(
                    margin: const EdgeInsets.symmetric(horizontal: 4),
                    height: 86,
                    width: 304,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(16),
                      color: MomoColor.main,
                    ),
                    child: Center(
                      child: Text(
                        '공지사항',
                        style: MomoTextStyle.defaultStyle
                            .copyWith(color: MomoColor.white),
                      ),
                    ),
                  ),
                );
              },
              itemCount: 10,
            ),
          ),
        ],
      ),
    );
  }
}
