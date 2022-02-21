import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/group/group_list_dto.dart';
import 'package:momo/app/provider/group/group_provider.dart';
import 'package:momo/app/provider/group/recommend_group_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/card/group_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/group_list/widget/category_list.dart';

class RecommendGroupListPage extends ConsumerStatefulWidget {
  const RecommendGroupListPage({
    Key? key,
    required this.favoriteCallBack,
  }) : super(key: key);

  final void Function({
    required int groupId,
    required bool favorite,
  }) favoriteCallBack;

  @override
  ConsumerState<RecommendGroupListPage> createState() =>
      _RecommendGroupListPageState();
}

class _RecommendGroupListPageState
    extends ConsumerState<RecommendGroupListPage> {
  final _pagingController = PagingController<int, GroupInfo>(firstPageKey: 0);

  @override
  void initState() {
    super.initState();
    _pagingController.addPageRequestListener(
      (pageKey) => ref
          .read(categoryGroupListProvider.notifier)
          .getGroupsByCategoriesDetail(
            pageKey,
            ref.read(groupCategoryCheckStateProvider.notifier).makeFilter(),
          ),
    );
  }

  @override
  Widget build(BuildContext context) {
    ref.listen<GroupListDto>(categoryGroupListProvider, (previous, next) {
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
          title: '추천 더보기',
          actionWidget: SvgPicture.asset('assets/icon/icon_msg_28.svg'),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              CategoryList(
                refresh: () {
                  ref.read(categoryGroupListProvider.notifier).resetGroups();
                  _pagingController.refresh();
                },
              ),
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
                              .read(categoryGroupListProvider.notifier)
                              .deleteLike(item.id);
                          widget.favoriteCallBack(
                              groupId: item.id, favorite: false);
                        } else {
                          await ref
                              .read(categoryGroupListProvider.notifier)
                              .createLike(item.id);
                          widget.favoriteCallBack(
                              groupId: item.id, favorite: true);
                        }
                      },
                    ),
                    newPageProgressIndicatorBuilder: (context) =>
                        const LoadingCard(),
                    firstPageProgressIndicatorBuilder: (context) =>
                        const LoadingCard(),
                    noItemsFoundIndicatorBuilder: (context) =>
                        const NoItemCard(),
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
              ),
            ],
          ),
        ),
      ),
    );
  }
}
