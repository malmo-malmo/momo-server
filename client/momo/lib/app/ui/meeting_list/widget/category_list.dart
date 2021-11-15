import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class CategoryList extends StatelessWidget {
  const CategoryList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 70,
      child: ListView.builder(
        padding: const EdgeInsets.symmetric(vertical: 16),
        scrollDirection: Axis.horizontal,
        itemBuilder: (context, index) {
          return Container(
            margin: const EdgeInsets.symmetric(horizontal: 4),
            height: 30,
            width: 70,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(16),
              color: MomoColor.main,
            ),
            child: Center(
                child: Text(
              '힐링',
              style: TextStyle(
                fontSize: 14.sp,
              ),
            )),
          );
        },
        itemCount: 10,
      ),
    );
  }
}
