import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/provider/comment/comment_list_provider.dart';
import 'package:momo/app/provider/post/post_detail_provider.dart';
import 'package:momo/app/provider/post/post_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/floating_text_button.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/post_detail/widget/comments_list.dart';
import 'package:momo/app/ui/post_detail/widget/post_detail_card.dart';

class PostDetailPage extends ConsumerStatefulWidget {
  const PostDetailPage({
    Key? key,
    required this.post,
  }) : super(key: key);

  final Post post;

  @override
  ConsumerState<PostDetailPage> createState() => _PostDetailPageState();
}

class _PostDetailPageState extends ConsumerState<PostDetailPage> {
  @override
  Widget build(BuildContext context) {
    final postDetailResponse =
        ref.watch(postDetailFutureProvider(widget.post.id));

    return SafeArea(
      child: postDetailResponse.when(
        error: (error, stackTrace) => Scaffold(body: errorCard()),
        loading: () => Scaffold(body: loadingCard()),
        data: (data) {
          final postDetail = ref.watch(postDetailProvider(data));

          final curCommentCnt =
              ref.watch(postStateProvider(widget.post)).commentCnt;

          return Scaffold(
            backgroundColor: const Color(0xffffffff),
            appBar: customAppBar(
              leadingIcon: CupertinoIcons.back,
              isAction: true,
              actionWidget: SvgPicture.asset('assets/icon/icon_msg_28.svg'),
            ),
            body: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: Column(
                children: [
                  Expanded(
                    child: CustomScrollView(
                      slivers: [
                        postDetailCard(
                          postDetail: postDetail,
                          commentCnt: curCommentCnt,
                        ),
                        CommentsList(postId: postDetail.id),
                      ],
                    ),
                  ),
                  FloatingTextButton(
                    hintText: '댓글을 입력하세요',
                    sendMessage: (text) async {
                      await ref
                          .read(
                              commentListStateProvider(postDetail.id).notifier)
                          .createComment(text);
                    },
                    onTapIcon: () async {
                      ref
                          .read(postStateProvider(widget.post).notifier)
                          .addComment();
                      FocusScope.of(context).unfocus();
                    },
                  ),
                ],
              ),
            ),
          );
        },
      ),
    );
  }
}
