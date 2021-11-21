import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/member/member.dart';
import 'package:momo/app/ui/member_list/widget/admin_dialog.dart';
import 'package:momo/app/ui/member_list/widget/member_list.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class MemberListPage extends StatelessWidget {
  const MemberListPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: AppBar(
          elevation: 0,
          leading: Consumer(
            builder: (context, ref, _) {
              return InkWell(
                onTap: () {
                  ref.read(navigatorProvider).pop(result: false);
                },
                child: const Icon(
                  CupertinoIcons.back,
                  color: MomoColor.black,
                ),
              );
            },
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: const [
                  Text('이름(닉네임)'),
                  Text('선택'),
                ],
              ),
              const Expanded(
                child: MemberList(),
              ),
              Consumer(builder: (context, ref, _) {
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
                          return MomoColor.main;
                        },
                      ),
                    ),
                    onPressed: () async {
                      final check = await showDialog(
                        context: context,
                        builder: (context) => adminDialog(
                          member: Member(
                            id: 1,
                            name: '김모모',
                            profile:
                                'https://t1.daumcdn.net/cfile/blog/999B27505C55A61321',
                            rate: 90,
                          ),
                        ),
                      );
                      if (check) {
                        ref.read(navigatorProvider).pop(result: true);
                      }
                    },
                    child: Text(
                      '완료',
                      style: TextStyle(
                        fontSize: 16.sp,
                      ),
                    ),
                  ),
                );
              }),
            ],
          ),
        ),
      ),
    );
  }
}
