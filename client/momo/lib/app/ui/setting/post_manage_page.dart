import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/post/management_post_response.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/provider/user/user_post_list.dart';
import 'package:momo/app/provider/user/user_post_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/post_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/setting/widget/settings_app_bar.dart';
import 'package:momo/app/util/format/post_date_format.dart';
import 'package:momo/app/util/navigation_service.dart';

class PostManagePage extends ConsumerStatefulWidget {
  const PostManagePage({Key? key}) : super(key: key);

  @override
  ConsumerState<PostManagePage> createState() => _PostManagePageState();
}

class _PostManagePageState extends ConsumerState<PostManagePage> {
  final _pagingController =
      PagingController<int, ManagementPostResponse>(firstPageKey: 0);

  @override
  void initState() {
    super.initState();
    _pagingController.addPageRequestListener((pageKey) {
      ref.read(userPostStateProvider.notifier).getPosts(pageKey);
    });
  }

  @override
  Widget build(BuildContext context) {
    ref.listen<UserPostList>(userPostStateProvider, (previous, next) {
      _pagingController.value = PagingState(
        itemList: next.posts,
        nextPageKey: next.hasNext ? next.nextPage : null,
        error: null,
      );
    });

    return SafeArea(
      child: Scaffold(
        appBar: const SettingsAppBar(
          leadingIcon: CupertinoIcons.back,
          isAction: false,
          title: '게시물 관리',
          backgroundColor: MomoColor.flutterWhite,
        ),
        body: PagedListView.separated(
          padding: const EdgeInsets.only(top: 14, right: 16, left: 16),
          builderDelegate: PagedChildBuilderDelegate<ManagementPostResponse>(
            itemBuilder: (context, item, index) =>
                _ManagementPostCard(data: item),
            newPageProgressIndicatorBuilder: (context) => const LoadingCard(),
            firstPageProgressIndicatorBuilder: (context) => const LoadingCard(),
            noItemsFoundIndicatorBuilder: (context) => const NoItemCard(),
          ),
          pagingController: _pagingController,
          separatorBuilder: (context, index) => const SizedBox(height: 14),
        ),
      ),
    );
  }
}

class _ManagementPostCard extends StatelessWidget {
  const _ManagementPostCard({
    Key? key,
    required this.data,
  }) : super(key: key);

  final ManagementPostResponse data;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 7),
      child: Consumer(builder: (context, ref, _) {
        return InkWell(
          onTap: () async {
            ref.read(navigatorProvider).navigateTo(
                  routeName: AppRoutes.postDetail,
                  arguments: data.post.id,
                );
          },
          child: Container(
            height: 182,
            decoration: BoxDecoration(
              color: MomoColor.flutterWhite,
              borderRadius: BorderRadius.circular(20),
            ),
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 20),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      CircleAvatar(
                        radius: 16.w,
                        backgroundColor: MomoColor.black,
                        child: CircleAvatar(
                          radius: 15.w,
                          backgroundColor: Colors.transparent,
                          backgroundImage: NetworkImage(data.post.authorImage ??
                              'https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/cbdef037365169.573db7853cebb.jpg'),
                        ),
                      ),
                      const SizedBox(width: 10),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            data.post.authorNickname,
                            style: MomoTextStyle.small,
                          ),
                          const SizedBox(height: 4),
                          Text(
                            postDateFormat(data.post.createdDate),
                            style: MomoTextStyle.small.copyWith(
                              color: MomoColor.unSelIcon,
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),
                  Text(data.post.title, style: MomoTextStyle.defaultStyle),
                  const SizedBox(height: 12),
                  Text(data.post.contents,
                      style: MomoTextStyle.normal,
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis),
                  const SizedBox(height: 12),
                  Text(
                    data.groupName,
                    style: MomoTextStyle.smallR
                        .copyWith(color: MomoColor.unSelIcon),
                  ),
                ],
              ),
            ),
          ),
        );
      }),
    );
  }
}
