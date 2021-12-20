import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/util/theme.dart';

class ManageMeetingCard extends StatelessWidget {
  const ManageMeetingCard({
    Key? key,
    required this.title,
    required this.rate,
    required this.headNum,
    required this.img,
  }) : super(key: key);

  final String title;
  final int headNum;
  final int rate;
  final String img;

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {},
      child: Stack(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: Image.network(
              img,
              fit: BoxFit.fill,
              height: 212.h,
              width: 197.w,
            ),
          ),
          Container(
            padding:
                EdgeInsets.only(top: 93.h, left: 14, right: 14, bottom: 14),
            height: 212.h,
            width: 197.w,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: Colors.transparent,
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
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
                    SizedBox(
                      width: 102.w,
                      child: ClipRRect(
                        borderRadius: BorderRadius.circular(10),
                        child: LinearProgressIndicator(
                          value: rate.toDouble() / 100,
                          color: MomoColor.main,
                          backgroundColor: MomoColor.white,
                        ),
                      ),
                    ),
                    const SizedBox(width: 10),
                    Text(
                      '$rate%',
                      style: TextStyle(
                        color: MomoColor.white,
                        fontSize: 12.sp,
                      ),
                    )
                  ],
                ),
                SizedBox(height: 8.h),
                SizedBox(
                  width: 96.w,
                  height: 28.h,
                  child: Stack(
                    children: [
                      Positioned(
                        child: ProfileAvatar(
                          rad: 16.w,
                          img:
                              'https://mblogthumb-phinf.pstatic.net/MjAyMDAyMDdfMTYw/MDAxNTgxMDg1NzUxMTUy.eV1iEw2gk2wt_YqPWe5F7SroOCkXJy2KFwmTDNzM0GQg.Z3Kd5MrDh07j86Vlb2OhAtcw0oVmGCMXtTDjoHyem9og.JPEG.7wayjeju/%EB%B0%B0%EC%9A%B0%ED%94%84%EB%A1%9C%ED%95%84%EC%82%AC%EC%A7%84_IMG7117.jpg?type=w800',
                        ),
                      ),
                      Positioned(
                        left: 21.w,
                        child: ProfileAvatar(
                          rad: 16.w,
                          img:
                              'https://mblogthumb-phinf.pstatic.net/MjAyMDAyMDdfMTYw/MDAxNTgxMDg1NzUxMTUy.eV1iEw2gk2wt_YqPWe5F7SroOCkXJy2KFwmTDNzM0GQg.Z3Kd5MrDh07j86Vlb2OhAtcw0oVmGCMXtTDjoHyem9og.JPEG.7wayjeju/%EB%B0%B0%EC%9A%B0%ED%94%84%EB%A1%9C%ED%95%84%EC%82%AC%EC%A7%84_IMG7117.jpg?type=w800',
                        ),
                      ),
                      Positioned(
                        left: 42.w,
                        child: ProfileAvatar(
                          rad: 16.w,
                          img:
                              'https://mblogthumb-phinf.pstatic.net/MjAyMDAyMDdfMTYw/MDAxNTgxMDg1NzUxMTUy.eV1iEw2gk2wt_YqPWe5F7SroOCkXJy2KFwmTDNzM0GQg.Z3Kd5MrDh07j86Vlb2OhAtcw0oVmGCMXtTDjoHyem9og.JPEG.7wayjeju/%EB%B0%B0%EC%9A%B0%ED%94%84%EB%A1%9C%ED%95%84%EC%82%AC%EC%A7%84_IMG7117.jpg?type=w800',
                        ),
                      ),
                      Positioned(
                        left: 63.w,
                        child: CircleAvatar(
                          radius: 16.w,
                          backgroundColor: const Color(0xff616161),
                          child: Text(
                            '$headNum+',
                            style: TextStyle(
                              color: MomoColor.white,
                              fontSize: 10.sp,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
