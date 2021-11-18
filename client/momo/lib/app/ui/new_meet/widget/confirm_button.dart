import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/new_meet/new_meet_provider.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class ConfirmButton extends ConsumerWidget {
  const ConfirmButton({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final check = ref.watch(newMeetCheckProvider);

    return SizedBox(
      height: 57,
      width: double.infinity,
      child: ElevatedButton(
        style: ButtonStyle(
          shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(32),
            ),
          ),
          backgroundColor: MaterialStateProperty.resolveWith(
            (states) {
              if (states.contains(MaterialState.disabled)) {
                return const Color(0xfff2f2f2);
              }
              return const Color(0xff000000);
            },
          ),
        ),
        onPressed: check
            ? () async {
                await showDialog(
                  context: context,
                  builder: (context) {
                    return _confirmDialog(ref.read(newMeetProvider).meetName);
                  },
                );
                ref.read(navigatorProvider).pop();
              }
            : null,
        child: Text(
          '완료',
          style: TextStyle(
            fontSize: 16.sp,
          ),
        ),
      ),
    );
  }

  Widget _confirmDialog(String title) {
    return Dialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        padding:
            const EdgeInsets.only(right: 24, left: 24, top: 48, bottom: 24),
        height: 162,
        width: 294,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              "'$title' 모임이 생성되었어요.",
              style: TextStyle(color: MomoColor.black, fontSize: 16.sp),
            ),
            SizedBox(
              height: 44,
              width: 241,
              child: Consumer(builder: (context, ref, _) {
                return ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    primary: MomoColor.main,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20),
                    ),
                  ),
                  onPressed: () {
                    ref.read(navigatorProvider).pop();
                  },
                  child: Text(
                    '확인',
                    style: TextStyle(
                      fontSize: 16.sp,
                    ),
                  ),
                );
              }),
            ),
          ],
        ),
      ),
    );
  }
}
