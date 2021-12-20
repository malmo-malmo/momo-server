import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:momo/app/model/enum/post_type.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/provider/group/group_provider.dart';
import 'package:momo/app/provider/post/post_paging_controller_provider.dart';
import 'package:momo/app/routes/custom_arg/post_request_arg.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/group_detail/widget/withdraw_dialog.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class UserBottomSheet extends ConsumerStatefulWidget {
  const UserBottomSheet({Key? key, required this.group}) : super(key: key);

  final GroupInfo group;

  @override
  _UserBottomSheetState createState() => _UserBottomSheetState();
}

class _UserBottomSheetState extends ConsumerState<UserBottomSheet> {
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
      height: 154,
      width: double.infinity,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(
            height: 4,
            width: 52,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(16),
              color: const Color(0xffd1d1d1),
            ),
          ),
          const SizedBox(height: 18),
          InkWell(
            onTap: () async {
              await ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.postRequest,
                    arguments: PostRequestArg(
                      postType: PostType.normal,
                      groupId: widget.group.id,
                    ),
                  );
              ref.read(navigatorProvider).pop();
              ref
                  .read(postPaigingControllerProvider(widget.group.id))
                  .refresh();
            },
            child: sheetTabButtob(
              title: '게시물 작성',
              icon: 'assets/icon/icon_write_28.svg',
            ),
          ),
          InkWell(
            onTap: () async {
              final isWithdraw = await showDialog(
                context: context,
                builder: (context) => withdrawDialog(widget.group.id),
              );
              if (isWithdraw != null && isWithdraw) {
                ref
                    .read(groupStateProvider(widget.group).notifier)
                    .subParticipantCnt();
                _showToast('탈퇴되었어요');
                ref.read(navigatorProvider).pop();
                ref.read(navigatorProvider).pop();
              }
            },
            child: sheetTabButtob(
              title: '모임 탈퇴',
              icon: 'assets/icon/icon_memberexit_28.svg',
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
            style: MomoTextStyle.small.copyWith(
              color: MomoColor.white,
              fontWeight: FontWeight.w400,
            ),
          ),
        ),
      ),
      gravity: ToastGravity.BOTTOM,
      toastDuration: const Duration(seconds: 1),
    );
  }
}

Widget sheetTabButtob({
  required String title,
  required String icon,
}) {
  return SizedBox(
    height: 52,
    child: Row(
      children: [
        SvgPicture.asset(icon),
        const SizedBox(width: 10),
        Text(
          title,
          style: MomoTextStyle.defaultStyle,
        ),
      ],
    ),
  );
}
