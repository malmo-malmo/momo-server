import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';
import 'package:pie_chart/pie_chart.dart';

class AchievementCard extends StatelessWidget {
  const AchievementCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      borderRadius: BorderRadius.circular(20),
      elevation: 2,
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 24),
        height: 304.h,
        width: 328.w,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: MomoColor.white,
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            _achievementRow(
              title: '하루 3시간 공부 인증',
              icon: CupertinoIcons.at_circle,
              rate: 95,
            ),
            Container(height: 1, width: 280.w, color: MomoColor.divider),
            _achievementRow(
              title: '영어회화 초급 스터디',
              icon: CupertinoIcons.at_circle,
              rate: 90,
            ),
            Container(height: 1, width: 280.w, color: MomoColor.divider),
            _achievementRow(
              title: '서울 맛집탐방',
              icon: CupertinoIcons.at_circle,
              rate: 80,
            ),
            Container(height: 1, width: 280.w, color: MomoColor.divider),
            _achievementRow(
              title: '주제가 있는 독서모임',
              icon: CupertinoIcons.at_circle,
              rate: 60,
            ),
          ],
        ),
      ),
    );
  }

  Widget _achievementRow({
    required String title,
    required IconData icon,
    required int rate,
  }) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Icon(icon, size: 36),
              SizedBox(width: 14.w),
              Text(
                title,
                style: MomoTextStyle.defaultStyle,
              ),
            ],
          ),
          Stack(
            alignment: Alignment.center,
            children: [
              Transform(
                alignment: Alignment.center,
                transform: Matrix4.rotationY(pi),
                child: PieChart(
                  dataMap: {
                    '1': (100 - rate).toDouble(),
                    '2': (rate).toDouble()
                  },
                  chartType: ChartType.ring,
                  chartRadius: 32,
                  ringStrokeWidth: 5,
                  gradientList: const [
                    [
                      MomoColor.unRated,
                      MomoColor.unRated,
                    ],
                    [
                      MomoColor.main,
                      MomoColor.main,
                    ],
                  ],
                  initialAngleInDegree: 270,
                  // colorList: const [Color(0xff846eaa), Color(0xfff5f5fb)],
                  legendOptions: const LegendOptions(
                    showLegendsInRow: false,
                    showLegends: false,
                  ),
                  chartValuesOptions: const ChartValuesOptions(
                    showChartValueBackground: false,
                    showChartValues: false,
                    showChartValuesInPercentage: false,
                    showChartValuesOutside: false,
                    decimalPlaces: 1,
                  ),
                ),
              ),
              Text(
                '$rate',
                style: MomoTextStyle.small.copyWith(
                  fontSize: 10.sp,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
