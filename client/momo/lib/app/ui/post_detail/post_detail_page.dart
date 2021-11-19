import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/ui/components/floating_text_button.dart';
import 'package:momo/app/ui/post_detail/widget/comments_list.dart';
import 'package:momo/app/ui/post_detail/widget/post_detail_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class PostDetailPage extends StatelessWidget {
  const PostDetailPage({
    Key? key,
    required this.postId,
  }) : super(key: key);

  final int postId;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: AppBar(
          elevation: 0,
          leading: Consumer(builder: (context, ref, _) {
            return InkWell(
              onTap: () {
                ref.read(navigatorProvider).pop();
              },
              child: const Icon(
                Icons.arrow_back,
                color: MomoColor.black,
              ),
            );
          }),
        ),
        body: Padding(
          padding: const EdgeInsets.only(left: 16, right: 16),
          child: Column(
            children: [
              const Expanded(
                child: CustomScrollView(
                  slivers: [
                    PostDetailCard(
                      name: '이모모',
                      profile:
                          'https://img.insight.co.kr/static/2021/06/19/700/img_20210619102129_v06qrfrj.webp',
                      title: '필독 부탁드립니다',
                      contents: '안녕하세요',
                      img:
                          'https://lh3.googleusercontent.com/proxy/x7ZV2KMc67aYKFORnPA58XDWa1YBWCD7LcCakvwZKPp_btWNJ2cfitQJcoWH9tY8BIVU21aLrOBMJiFsnqnOghtWy89ymd_AYIXgtzOD3yMgrc2UC3BwqiEmga6QfAjNpGKF4yinlr_mYn9Y',
                      comments: 5,
                    ),
                    CommentsList(),
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
      ),
    );
  }
}
