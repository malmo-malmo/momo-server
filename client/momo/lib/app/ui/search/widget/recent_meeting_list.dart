import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class RecentMeetingList extends StatelessWidget {
  const RecentMeetingList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SliverGrid.count(
      crossAxisCount: 2,
      mainAxisSpacing: 14,
      crossAxisSpacing: 14,
      children: [
        for (int i = 0; i < 10; i++)
          _meetingCard(
            onOff: '오프라인',
            title: '주식&재테크 왕초보 스터디',
            headNum: 3,
            date: '11/3~',
            img:
                'https://img.hani.co.kr/imgdb/resize/2019/0606/53_1559732893_00500014_20190606.JPG',
          ),
      ],
    );
  }

  Widget _meetingCard({
    required String onOff,
    required String title,
    required int headNum,
    required String date,
    required String img,
  }) {
    return InkWell(
      onTap: () {},
      child: Stack(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: Image.network(
              img,
              fit: BoxFit.fill,
              width: double.infinity,
              height: 200.h,
            ),
          ),
          Container(
            padding: const EdgeInsets.all(14),
            width: double.infinity,
            height: 200.h,
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
  }
}
