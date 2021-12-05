import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/provider/comment/comment_paging_controller_provider.dart';
import 'package:momo/app/provider/comment/comment_request_provider.dart';
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
    required this.postId,
    required this.commentCnt,
  }) : super(key: key);

  final int postId;
  final int commentCnt;

  @override
  ConsumerState<PostDetailPage> createState() => _PostDetailPageState();
}

class _PostDetailPageState extends ConsumerState<PostDetailPage> {
  @override
  Widget build(BuildContext context) {
    final postDetailResponse =
        ref.watch(postDetailFutureProvider(widget.postId));

    return SafeArea(
      child: postDetailResponse.when(
        error: (error, stackTrace) => Scaffold(body: errorCard()),
        loading: () => Scaffold(body: loadingCard()),
        data: (data) {
          final postDetail = ref.watch(postDetailProvider(data));
          final commentRequest =
              ref.watch(commentRequestProvider(postDetail.id));
          final commentCheck =
              ref.watch(commentContentsCheckProvider(postDetail.id));
          final curCommentCnt = ref
              .watch(
                postStateProvider(
                  Post(
                    id: postDetail.id,
                    authorNickname: postDetail.authorNickname,
                    authorImage: postDetail.authorImage,
                    title: postDetail.title,
                    contents: postDetail.contents,
                    commentCnt: widget.commentCnt,
                    createdDate: postDetail.createdDate,
                  ),
                ),
              )
              .commentCnt;

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
                          // commentCnt: widget.commentCnt,
                          commentCnt: curCommentCnt,
                        ),
                        CommentsList(postId: postDetail.id),
                      ],
                    ),
                  ),
                  FloatingTextButton(
                    hintText: '댓글을 입력하세요',
                    onTextChanged: ref
                        .read(
                            commentRequestStateProvider(postDetail.id).notifier)
                        .setContents,
                    check: commentCheck,
                    onTapIcon: () async {
                      final comment = await ref
                          .read(commentRequestStateProvider(postDetail.id)
                              .notifier)
                          .createComment();
                      ref
                          .read(commentPagingControllerProvider(widget.postId))
                          .itemList!
                          .add(comment);
                      ref
                          .read(commentRequestStateProvider(postDetail.id)
                              .notifier)
                          .resetContents();
                      ref
                          .read(
                            postStateProvider(
                              Post(
                                id: postDetail.id,
                                authorNickname: postDetail.authorNickname,
                                authorImage: postDetail.authorImage,
                                title: postDetail.title,
                                contents: postDetail.contents,
                                commentCnt: widget.commentCnt,
                                createdDate: postDetail.createdDate,
                              ),
                            ).notifier,
                          )
                          .addComment();
                      FocusScope.of(context).unfocus();
                      setState(() {});
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
