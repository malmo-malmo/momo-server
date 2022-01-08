import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/post_card.dart';
import 'package:momo/app/ui/setting/widget/settings_app_bar.dart';

class PostManagePage extends ConsumerWidget {
  const PostManagePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return SafeArea(
      child: Scaffold(
        appBar: const SettingsAppBar(
          leadingIcon: CupertinoIcons.back,
          isAction: false,
          title: '게시물 관리',
          backgroundColor: MomoColor.flutterWhite,
        ),
        body: ListView.separated(
          padding: const EdgeInsets.only(top: 14, right: 16, left: 16),
          itemBuilder: (context, index) => PostCard(
            post: Post(
              authorNickname: 'MOMO',
              commentCnt: 5,
              contents:
                  '내가 작성한 게시물 내가 작성한 게시물 내가 작성한 게시물 내가 작성한 게시물 내가 작성한 게시물 내가 작성한 게시물 내가 작성한 게시물 내가 작성한 게시물 내가 작성한 게시물 내가 작성한 게시물 ',
              createdDate: '2022-01-06T01:56:46.916026',
              id: 1,
              title: '내가 작성한 게시물',
            ),
          ),
          separatorBuilder: (context, index) => const SizedBox(height: 14),
          itemCount: 30,
        ),
      ),
    );
  }
}
