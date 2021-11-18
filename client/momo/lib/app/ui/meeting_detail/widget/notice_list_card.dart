import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class NoticeListCard extends StatelessWidget {
  const NoticeListCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
      height: 125,
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                '공지사항',
                style: TextStyle(fontSize: 16.sp),
              ),
              Transform.rotate(
                angle: pi,
                child: Icon(
                  CupertinoIcons.back,
                  color: MomoColor.black,
                  size: 18.w,
                ),
              ),
            ],
          ),
          const SizedBox(height: 8),
          SizedBox(
            height: 80,
            child: ListView.builder(
              padding: const EdgeInsets.symmetric(vertical: 16),
              scrollDirection: Axis.horizontal,
              itemBuilder: (context, index) {
                return Container(
                  margin: const EdgeInsets.symmetric(horizontal: 4),
                  height: 40,
                  width: 200,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(16),
                    color: MomoColor.main,
                  ),
                  child: Center(
                    child: Text(
                      '공지사항',
                      style: TextStyle(
                        fontSize: 14.sp,
                        color: MomoColor.white,
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
