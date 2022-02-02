import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:momo/app/model/enum/post_type.dart';
import 'package:momo/app/provider/post/post_list_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/routes/custom_arg/post_request_arg.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/group_detail/widget/group_close_dialog.dart';
import 'package:momo/app/ui/group_detail/widget/user_bottom_sheet.dart';
import 'package:momo/app/util/navigation_service.dart';

class AdminBottomSheet extends ConsumerStatefulWidget {
  const AdminBottomSheet({
    Key? key,
    required this.groupId,
  }) : super(key: key);

  final int groupId;

  @override
  _AdminBottomSheetState createState() => _AdminBottomSheetState();
}

class _AdminBottomSheetState extends ConsumerState<AdminBottomSheet> {
  final fToast = FToast();

  @override
  void initState() {
    super.initState();
    fToast.init(context);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.only(top: 18, left: 16, right: 16),
      height: 310,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(
            height: 4,
            width: 52,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(16),
              color: MomoColor.bottomSheetTop,
            ),
          ),
          const SizedBox(height: 18),
          InkWell(
            onTap: () async {
              final result = await ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.postRequest,
                    arguments: PostRequestArg(
                      postType: PostType.normal,
                      groupId: widget.groupId,
                    ),
                  );
              ref
                  .read(postListProvider(widget.groupId).notifier)
                  .addPost(result);
              ref.read(navigatorProvider).pop();
            },
            child: sheetTabButtob(
              title: '게시물 작성',
              icon: 'assets/icon/icon_write_28.svg',
            ),
          ),
          InkWell(
            onTap: () async {
              final result = await ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.postRequest,
                    arguments: PostRequestArg(
                      postType: PostType.notice,
                      groupId: widget.groupId,
                    ),
                  );
              ref
                  .read(noticeListProvider(widget.groupId).notifier)
                  .addPost(result);
              ref.read(navigatorProvider).pop();
            },
            child: sheetTabButtob(
              title: '공지사항 작성',
              icon: 'assets/icon/icon_notice_28.svg',
            ),
          ),
          InkWell(
            onTap: () async {
              await ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.scheduleRequest,
                    arguments: widget.groupId,
                  );
              ref.read(navigatorProvider).pop();
            },
            child: sheetTabButtob(
              title: '일정 추가',
              icon: 'assets/icon/icon_todoadd_28.svg',
            ),
          ),
          InkWell(
            onTap: () async {
              final isTransfer = await ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.memberList,
                    arguments: widget.groupId,
                  );
              if (isTransfer != null && isTransfer) {
                ref.read(navigatorProvider).pop();
                _showToast('권한을 넘겼어요');
              }
            },
            child: sheetTabButtob(
              title: '관리자 권한 넘기기',
              icon: 'assets/icon/icon_manager_28.svg',
            ),
          ),
          InkWell(
            onTap: () async {
              final isClose = await showDialog(
                context: context,
                builder: (context) {
                  return groupCloseDialog();
                },
              );
              if (isClose) {
                _showToast('모임이 종료되었어요.');
                ref.read(navigatorProvider).pop();
              }
            },
            child: sheetTabButtob(
              title: '모임 종료',
              icon: 'assets/icon/icon_moimclose.svg',
            ),
          ),
        ],
      ),
    );
  }

  void _showToast(String title) {
    fToast.showToast(
      child: Container(
        width: 320,
        height: 52,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20.0),
          color: MomoColor.black.withOpacity(0.8),
        ),
        child: Center(
          child: Text(
            title,
            style: MomoTextStyle.smallR.copyWith(
              color: MomoColor.white,
            ),
          ),
        ),
      ),
      gravity: ToastGravity.BOTTOM,
      toastDuration: const Duration(seconds: 1),
    );
  }
}
