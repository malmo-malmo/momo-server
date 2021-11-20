import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget homeMeetCard({
  required String onOff,
  required String title,
  required int headNum,
  required String date,
  required String img,
  required double width,
  required double height,
}) {
  return Consumer(builder: (context, ref, _) {
    return InkWell(
      onTap: () {
        ref
            .read(navigatorProvider)
            .navigateTo(routeName: AppRoutes.meetingDetail);
      },
      child: Stack(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: Image.network(
              img,
              fit: BoxFit.fill,
              width: width,
              height: height,
            ),
          ),
          Container(
            padding: const EdgeInsets.all(14),
            width: width,
            height: height,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: Colors.transparent,
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Container(
                      width: onOff.length * 11.0 + 21.w,
                      height: 25.h,
                      child: Center(
                          child: Text(
                        onOff,
                        style:
                            TextStyle(fontSize: 12.sp, color: MomoColor.white),
                      )),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(13),
                        color: MomoColor.main,
                      ),
                    ),
                    Icon(
                      CupertinoIcons.heart,
                      size: 24.w,
                      color: MomoColor.white,
                    ),
                  ],
                ),
                Column(
                  children: [
                    Text(
                      title,
                      style: TextStyle(
                        fontSize: 16.sp,
                        fontWeight: FontWeight.bold,
                        color: MomoColor.white,
                      ),
                    ),
                    SizedBox(height: 8.h),
                    Row(
                      children: [
                        Text(
                          '$headNum',
                          style: TextStyle(
                            fontSize: 12.sp,
                            color: MomoColor.white,
                          ),
                        ),
                        SizedBox(width: 15.w),
                        Text(
                          date,
                          style: TextStyle(
                            fontSize: 12.sp,
                            color: MomoColor.white,
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  });
}