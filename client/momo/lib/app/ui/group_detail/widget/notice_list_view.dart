import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/provider/post/post_list_dto.dart';
import 'package:momo/app/provider/post/post_list_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class NoticeListView extends ConsumerStatefulWidget {
  const NoticeListView({Key? key, required this.groupId}) : super(key: key);

  final int groupId;

  @override
  ConsumerState<NoticeListView> createState() => _NoticeListViewState();
}

class _NoticeListViewState extends ConsumerState<NoticeListView> {
  final _pagingController = PagingController<int, Post>(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((pageKey) => ref
        .read(noticeListProvider(widget.groupId).notifier)
        .getNotices(pageKey));

    super.initState();
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    ref.listen<PostListDto>(
      noticeListProvider(widget.groupId),
      (previous, next) {
        _pagingController.value = PagingState(
          itemList: next.posts,
          nextPageKey: next.hasNext ? next.nextPage : null,
          error: null,
        );
      },
    );
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
      height: 182,
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Text('공지사항', style: MomoTextStyle.subTitle),
              InkWell(
                onTap: () {
                  ref.read(navigatorProvider).navigateTo(
                        routeName: AppRoutes.noticeList,
                        arguments: _pagingController,
                      );
                },
                child: Transform.rotate(
                  angle: pi,
                  child: Icon(
                    CupertinoIcons.back,
                    color: MomoColor.black,
                    size: 24.w,
                  ),
                ),
              ),
            ],
          ),
          const SizedBox(height: 17),
          SizedBox(
            height: 86,
            child: PagedListView.separated(
              pagingController: _pagingController,
              scrollDirection: Axis.horizontal,
              builderDelegate: PagedChildBuilderDelegate<Post>(
                itemBuilder: (context, item, index) => _NoticeCard(
                  post: item,
                  onPressCard: () async {
                    await ref.read(navigatorProvider).navigateTo(
                          routeName: AppRoutes.postDetail,
                          arguments: item.id,
                        );
                  },
                ),
                newPageProgressIndicatorBuilder: (context) =>
                    const LoadingCard(),
                firstPageProgressIndicatorBuilder: (context) =>
                    const LoadingCard(),
                noItemsFoundIndicatorBuilder: (context) => const NoItemCard(),
              ),
              separatorBuilder: (context, index) => const SizedBox(width: 14),
            ),
          ),
        ],
      ),
    );
  }
}

class _NoticeCard extends StatelessWidget {
  const _NoticeCard({
    Key? key,
    required this.post,
    required this.onPressCard,
  }) : super(key: key);

  final Post post;
  final Function onPressCard;

  @override
  Widget build(BuildContext context) {
    return Consumer(builder: (context, ref, _) {
      return InkWell(
        onTap: () async {
          await onPressCard();
        },
        child: Container(
          height: 86,
          width: 304,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(16),
            color: MomoColor.main,
          ),
          child: Center(
            child: Text(
              post.title,
              style: MomoTextStyle.defaultStyle.copyWith(
                color: MomoColor.white,
                height: 1.2,
              ),
              maxLines: 2,
              overflow: TextOverflow.ellipsis,
            ),
          ),
        ),
      );
    });
  }
}
