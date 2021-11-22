import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

Widget adminScheduleCard({
  required int postId,
  required String profile,
  required String userName,
  required String title,
  required String text,
  required String date,
  required bool attendance,
  required bool isCheck,
}) {
  return InkWell(
    onTap: () {},
    child: Material(
      elevation: 3,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        height: 204,
        decoration: BoxDecoration(
          color: const Color(0xffffffff),
          borderRadius: BorderRadius.circular(20),
        ),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 20),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Row(
                    children: [
                      CircleAvatar(
                        radius: 16.w,
                        backgroundColor: MomoColor.black,
                        child: CircleAvatar(
                          radius: 15.w,
                          backgroundColor: Colors.transparent,
                          backgroundImage: NetworkImage(profile),
                        ),
                      ),
                      const SizedBox(width: 10),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            userName,
                            style: MomoTextStyle.small,
                          ),
                          const SizedBox(height: 4),
                          Text(
                            date,
                            style: MomoTextStyle.small.copyWith(
                              color: const Color(0xff9e9e9e),
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                  Container(
                    height: 32,
                    width: 60,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      color: attendance
                          ? const Color(0xffedf2ff)
                          : const Color(0xfffff0f0),
                    ),
                    child: Center(
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(
                            attendance ? Icons.check : CupertinoIcons.xmark,
                            size: 12,
                            color: attendance
                                ? const Color(0xff8da8fa)
                                : const Color(0xffff5e5c),
                          ),
                          const SizedBox(width: 4),
                          Text(
                            attendance ? '출석' : '불참',
                            style: MomoTextStyle.small.copyWith(
                              color: attendance
                                  ? const Color(0xff8da8fa)
                                  : const Color(0xffff5e5c),
                              fontSize: 10.sp,
                            ),
                          ),
                        ],
                      ),
                    ),
                  )
                ],
              ),
              const SizedBox(height: 20),
              Text(
                title,
                style: MomoTextStyle.defaultStyle,
              ),
              const SizedBox(height: 12),
              Text(
                text,
                style: MomoTextStyle.small,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
              ),
              const SizedBox(height: 16),
              Align(
                alignment: Alignment.bottomRight,
                child: InkWell(
                  onTap: () {},
                  child: Container(
                    height: 32,
                    width: 72,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      color: isCheck ? const Color(0xfff0f0f0) : MomoColor.main,
                    ),
                    child: Center(
                      child: Text(
                        isCheck ? '확인완료' : '출석체크',
                        style: MomoTextStyle.small.copyWith(
                          fontSize: 10.sp,
                          color:
                              isCheck ? MomoColor.unSelIcon : MomoColor.white,
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    ),
  );
}
