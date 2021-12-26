import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/message_button.dart';
import 'package:momo/app/ui/components/card/post_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';

class NoticeListPage extends StatelessWidget {
  const NoticeListPage({Key? key, required this.pagingController})
      : super(key: key);

  final PagingController<int, Post> pagingController;

  @override
  Widget build(BuildContext context) {
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
            pagingController: pagingController,
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
