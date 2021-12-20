import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/user/university_controller_provider.dart';
import 'package:momo/app/ui/components/dialog/university_result_dialog.dart';

Widget universityInputBox({
  required Function(String school) setUniversity,
  Color? backgroundColor,
}) {
  return Consumer(
    builder: (context, ref, _) {
      return Container(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(16),
          color: backgroundColor ?? const Color(0xffffffff),
        ),
        height: 44,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            SizedBox(
              width: 240.w,
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 14),
                child: TextFormField(
                  controller: ref.watch(universityTextController),
                ),
              ),
            ),
            InkWell(
              child: SvgPicture.asset('assets/icon/search/icon_search_28.svg'),
              onTap: () {
                showDialog(
                    context: context,
                    builder: (context) =>
                        UniversityResultDialog(onSelect: setUniversity));
                FocusScope.of(context).unfocus();
              },
            ),
          ],
        ),
      );
    },
  );
}
