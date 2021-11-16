import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/my_meet/widget/manage_meeting_list.dart';
import 'package:momo/app/util/theme.dart';

class MyMeetPage extends StatelessWidget {
  const MyMeetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: SingleChildScrollView(
        child: Padding(
          padding:
              const EdgeInsets.only(right: 16, left: 16, top: 43, bottom: 32),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Icon(
                    CupertinoIcons.chat_bubble_fill,
                    size: 28.w,
                    color: MomoColor.main,
                  ),
                ],
              ),
              SizedBox(height: 12.h),
              Text(
                '내 모임',
                style: TextStyle(
                  fontSize: 28.sp,
                  fontWeight: FontWeight.bold,
                ),
              ),
              _subTitle(title: '직접 관리 모임', icon: CupertinoIcons.pencil),
              const ManageMeetingList(),
              _subTitle(
                  title: '그 외 참여 모임', icon: CupertinoIcons.download_circle),
            ],
          ),
        ),
      ),
    );
  }

  Widget _subTitle({required String title, required IconData icon}) {
    return Padding(
      padding: const EdgeInsets.only(top: 43, bottom: 14),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Icon(
                icon,
                size: 28.sp,
                color: MomoColor.main,
              ),
              SizedBox(width: 10.w),
              Text(
                title,
                style: TextStyle(
                  fontSize: 20.sp,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
