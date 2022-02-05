import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/group/group_list_dto.dart';
import 'package:momo/app/provider/group/group_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/card/group_card.dart';

class GroupListPage extends ConsumerStatefulWidget {
  const GroupListPage({
    Key? key,
    required this.name,
    required this.likeCallback,
  }) : super(key: key);

  final String name;
  final void Function({
    required int groupId,
    required bool favorite,
  }) likeCallback;

  @override
  ConsumerState<GroupListPage> createState() => _GroupListPageState();
}

class _GroupListPageState extends ConsumerState<GroupListPage> {
  final _pagingController = PagingController<int, GroupInfo>(firstPageKey: 0);

  @override
  void initState() {
    if (widget.name == '내 학교') {
      _pagingController.addPageRequestListener((pageKey) =>
          ref.read(groupListProvider.notifier).getGroupsByUniversity(pageKey));
    } else {
      _pagingController.addPageRequestListener((pageKey) =>
          ref.read(groupListProvider.notifier).getGroupsByDistrict(pageKey));
    }
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    ref.listen<GroupListDto>(groupListProvider, (previous, next) {
      _pagingController.value = PagingState(
        itemList: next.groups,
        nextPageKey: next.hasNext ? next.nextPage : null,
        error: null,
      );
    });

    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xffffffff),
        appBar: CustomAppBar(
          leadingIcon: CupertinoIcons.back,
          isAction: true,
          title: widget.name + ' 더보기',
          actionWidget: SvgPicture.asset('assets/icon/icon_msg_28.svg'),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Expanded(
                child: PagedGridView(
                  pagingController: _pagingController,
                  builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
                    itemBuilder: (context, item, index) => GroupCard(
                      group: item,
                      height: 200.h,
                      width: double.infinity,
                      setLike: () async {
                        if (item.favoriteGroup) {
                          await ref
                              .read(groupListProvider.notifier)
                              .deleteLike(item.id);
                          widget.likeCallback(
                              groupId: item.id, favorite: false);
                        } else {
                          await ref
                              .read(groupListProvider.notifier)
                              .createLike(item.id);
                          widget.likeCallback(groupId: item.id, favorite: true);
                        }
                      },
                    ),
                  ),
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 2,
                    mainAxisExtent: 200.h,
                    mainAxisSpacing: 14,
                    crossAxisSpacing: 14,
                    childAspectRatio: 100 / 150,
                  ),
                  showNewPageProgressIndicatorAsGridChild: false,
                  showNewPageErrorIndicatorAsGridChild: false,
                  showNoMoreItemsIndicatorAsGridChild: false,
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
