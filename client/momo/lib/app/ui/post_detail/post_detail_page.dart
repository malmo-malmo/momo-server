import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
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
  }) : super(key: key);

  final int postId;

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
              actionWidget: SvgPicture.asset(
                'assets/icon/icon_msg_28.svg',
              ),
            ),
            body: Padding(
              padding: const EdgeInsets.only(left: 16, right: 16),
              child: Column(
                children: [
                  Expanded(
                    child: CustomScrollView(
                      slivers: [
                        postDetailCard(postDetail),
                        const CommentsList(),
                      ],
                    ),
                  ),
                  FloatingTextButton(
                    hintText: '댓글을 입력하세요',
                    onTextChanged: (text) {},
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
