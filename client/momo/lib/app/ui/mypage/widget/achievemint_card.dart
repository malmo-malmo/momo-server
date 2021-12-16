import 'dart:math';

import 'package:flutter/material.dart';
import 'package:momo/app/ui/mypage/widget/custom_expantion_list.dart';
import 'package:momo/app/util/theme.dart';
import 'package:pie_chart/pie_chart.dart';

class AchievementCard extends StatefulWidget {
  const AchievementCard({Key? key}) : super(key: key);

  @override
  _AchievementCardState createState() => _AchievementCardState();
}

class _AchievementCardState extends State<AchievementCard> {
  bool _expanded = false;

  @override
  Widget build(BuildContext context) {
    return CustomExpansionPanelList(
      animationDuration: const Duration(milliseconds: 500),
      children: [
        ExpansionPanel(
          backgroundColor: MomoColor.backgroundColor,
          headerBuilder: (context, isExpanded) => Padding(
            padding: const EdgeInsets.only(top: 18, left: 24),
            child: RichText(
              text: TextSpan(
                text: '내 달성률 ',
                style: MomoTextStyle.normal.copyWith(
                  fontWeight: FontWeight.w400,
                  color: MomoColor.main,
                ),
                children: [
                  TextSpan(
                    text: '살펴보기',
                    style: MomoTextStyle.normal
                        .copyWith(fontWeight: FontWeight.w400),
                  ),
                ],
              ),
            ),
          ),
          body: Padding(
            padding: const EdgeInsets.only(right: 24, left: 24, bottom: 24),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(color: MomoColor.white, height: 1),
                const SizedBox(height: 20),
                RichText(
                  text: TextSpan(
                    text: '당신은 진정한 ',
                    style: MomoTextStyle.subTitle
                        .copyWith(fontWeight: FontWeight.w400),
                    children: [
                      TextSpan(
                        text: '프로 모임러!',
                        style: MomoTextStyle.subTitle.copyWith(
                          color: MomoColor.main,
                        ),
                      ),
                    ],
                  ),
                ),
                const SizedBox(height: 26),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    Stack(
                      alignment: Alignment.center,
                      children: [
                        Transform(
                          alignment: Alignment.center,
                          transform: Matrix4.rotationY(pi),
                          child: PieChart(
                            dataMap: {
                              '1': (100 - 80).toDouble(),
                              '2': (80).toDouble()
                            },
                            chartType: ChartType.ring,
                            chartRadius: 100,
                            ringStrokeWidth: 16,
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
                          '80',
                          style: MomoTextStyle.subTitle.copyWith(
                            color: MomoColor.main,
                          ),
                        ),
                      ],
                    ),
                    SizedBox(
                      width: 150,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const Text('내 모임 달성률 top3',
                              style: MomoTextStyle.small),
                          const SizedBox(height: 16),
                          _groupRateRow(
                              groupName: '기초를 위한 영어 회화 모임', rate: 100),
                          _groupRateRow(groupName: '서울 맛집 탐방', rate: 60),
                          _groupRateRow(groupName: '청계천 달리기&산책', rate: 60),
                        ],
                      ),
                    )
                  ],
                ),
              ],
            ),
          ),
          isExpanded: _expanded,
        ),
      ],
      expansionCallback: (panelIndex, isExpanded) {
        _expanded = !_expanded;
        setState(() {});
      },
    );
  }

  Widget _groupRateRow({
    required String groupName,
    required int rate,
  }) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 8.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          SizedBox(
            width: 103,
            child: Text(
              groupName,
              style: MomoTextStyle.small.copyWith(
                fontWeight: FontWeight.w400,
              ),
              overflow: TextOverflow.ellipsis,
            ),
          ),
          Text(
            '$rate%',
            style: MomoTextStyle.small.copyWith(
              fontWeight: FontWeight.w400,
            ),
          ),
        ],
      ),
    );
  }
}
