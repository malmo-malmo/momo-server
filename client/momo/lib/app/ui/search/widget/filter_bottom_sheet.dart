import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/confirm_button.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/search/widget/category_icon.dart';
import 'package:momo/app/ui/search/widget/location_card.dart';
import 'package:momo/app/util/theme.dart';

Widget filterBottomSheet() {
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 16),
    height: double.infinity,
    width: double.infinity,
    child: SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const SizedBox(height: 36),
          Icon(
            CupertinoIcons.xmark,
            size: 21.w,
          ),
          Padding(
            padding: const EdgeInsets.only(top: 20, bottom: 24),
            child: Text('검색필터', style: MomoTextStyle.mainTitle),
          ),
          _subTitle('지역 선택'),
          Wrap(
            spacing: 10,
            runSpacing: 10,
            children: [
              locationCard('서울'),
              locationCard('경기'),
              locationCard('인천'),
              locationCard('강원'),
              locationCard('대전/충남/세종'),
              locationCard('충북'),
              locationCard('경남/부산/울산'),
              locationCard('경북/대구'),
              locationCard('전북'),
              locationCard('광주/전남'),
              locationCard('제주'),
            ],
          ),
          _subTitle('활동모임 선택'),
          Wrap(
            spacing: 16,
            runSpacing: 16,
            children: [
              categoryIcon(true, '건강', 0,
                  selImg: 'assets/icon/category/icon_health_64.svg',
                  unSelImg: 'assets/icon/category/icon_healthlightgray_64.svg'),
              categoryIcon(true, '밥약', 1,
                  selImg: 'assets/icon/category/icon_food_64.svg',
                  unSelImg: 'assets/icon/category/icon_foodlightgray_64.svg'),
              categoryIcon(true, '자기관리', 2,
                  selImg: 'assets/icon/category/icon_self_64.svg',
                  unSelImg: 'assets/icon/category/icon_selflightgray_64.svg'),
              categoryIcon(true, '생활', 3,
                  selImg: 'assets/icon/category/icon_life_64.svg',
                  unSelImg: 'assets/icon/category/icon_lifelightgray_64.svg'),
              categoryIcon(true, '취미', 4,
                  selImg: 'assets/icon/category/icon_hobby_64.svg',
                  unSelImg: 'assets/icon/category/icon_hobbylightgray_64.svg'),
              categoryIcon(true, '자산', 5,
                  selImg: 'assets/icon/category/icon_stock_64.svg',
                  unSelImg: 'assets/icon/category/icon_stockightgray_64.svg'),
              categoryIcon(true, '힐링', 6,
                  selImg: 'assets/icon/category/icon_healing_64.svg',
                  unSelImg:
                      'assets/icon/category/icon_healinglightgray_64.svg'),
              categoryIcon(true, '취업', 7,
                  selImg: 'assets/icon/category/icon_job_64.svg',
                  unSelImg: 'assets/icon/category/icon_joblightgray_64.svg'),
            ],
          ),
          const SizedBox(height: 48),
          const ConfirmButton(
            check: true,
            buttonText: '완료',
            isShowDialog: false,
          ),
          const SizedBox(height: 36),
        ],
      ),
    ),
  );
}

Widget _subTitle(String title) {
  return Padding(
    padding: const EdgeInsets.only(top: 36, bottom: 16),
    child: Text(
      title,
      style: MomoTextStyle.subTitle,
    ),
  );
}
