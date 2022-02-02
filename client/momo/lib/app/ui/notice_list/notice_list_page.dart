import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/provider/post/post_list_dto.dart';
import 'package:momo/app/provider/post/post_list_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/message_button.dart';
import 'package:momo/app/ui/components/card/post_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';

class NoticeListPage extends ConsumerStatefulWidget {
  const NoticeListPage({Key? key, required this.groupId}) : super(key: key);

  final int groupId;

  @override
  ConsumerState<NoticeListPage> createState() => _NoticeListPageState();
}

class _NoticeListPageState extends ConsumerState<NoticeListPage> {
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

    return SafeArea(
      child: Scaffold(
        appBar: const CustomAppBar(
          leadingIcon: CupertinoIcons.back,
          isAction: true,
          actionWidget: MessageButton(),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: PagedListView.separated(
            pagingController: _pagingController,
            builderDelegate: PagedChildBuilderDelegate<Post>(
              itemBuilder: (context, item, index) => PostCard(post: item),
              newPageProgressIndicatorBuilder: (context) => const LoadingCard(),
              firstPageProgressIndicatorBuilder: (context) =>
                  const LoadingCard(),
              noItemsFoundIndicatorBuilder: (context) => const NoItemCard(),
            ),
            separatorBuilder: (context, index) => const SizedBox(height: 14),
          ),
        ),
      ),
    );
  }
}
