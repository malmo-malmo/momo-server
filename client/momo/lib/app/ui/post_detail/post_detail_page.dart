import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/provider/comment/comment_paging_controller_provider.dart';
import 'package:momo/app/provider/post/post_detail_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/floating_text_button.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/post_detail/widget/comments_list.dart';
import 'package:momo/app/ui/post_detail/widget/post_detail_card.dart';

class PostDetailPage extends ConsumerWidget {
  const PostDetailPage({
    Key? key,
    required this.postId,
    required this.commentCnt,
  }) : super(key: key);

  final int postId;
  final int commentCnt;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final postDetailResponse = ref.watch(postDetailFutureProvider(postId));

    return SafeArea(
      child: postDetailResponse.when(
        error: (error, stackTrace) => Scaffold(body: errorCard()),
        loading: () => Scaffold(body: loadingCard()),
        data: (data) {
          final postDetail = ref.watch(postDetailProvider(data));

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
                          commentCnt: commentCnt,
                        ),
                        CommentsList(postId: postDetail.id),
                      ],
                    ),
                  ),
                  FloatingTextButton(
                    hintText: '댓글을 입력하세요',
                    onTextChanged: (text) {},
                    onTapIcon: () {
                      ref
                          .read(commentPagingControllerProvider(postId))
                          .itemList!
                          .add(
                            Comment(
                              id: 1,
                              authorId: 1,
                              authorNickname: '엘슨',
                              authorImage:
                                  'https://img.hankyung.com/photo/202102/20210223123724_6034787402939_1.jpg',
                              contents: '댓글 입력 테스트',
                              createdDate: DateTime.now().toIso8601String(),
                            ),
                          );
                      print(
                          'itmeList: ${ref.read(commentPagingControllerProvider(postId)).itemList}');
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
