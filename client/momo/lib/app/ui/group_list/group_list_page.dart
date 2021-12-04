import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/group_list/widget/category_list.dart';
import 'package:momo/app/ui/group_list/widget/group_list_view.dart';

class GroupListPage extends StatelessWidget {
  const GroupListPage({
    Key? key,
    required this.name,
    required this.pagingController,
  }) : super(key: key);

  final String name;
  final PagingController<int, GroupInfo> pagingController;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xffffffff),
        appBar: customAppBar(
          leadingIcon: CupertinoIcons.back,
          isAction: true,
          title: name + ' 더보기',
          actionWidget: SvgPicture.asset('assets/icon/icon_msg_28.svg'),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              name == '추천' ? const CategoryList() : const SizedBox(),
              GroupListView(
                pagingController: pagingController,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
