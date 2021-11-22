import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/ui/components/button/floating_text_button.dart';
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
        backgroundColor: const Color(0xffffffff),
        appBar: AppBar(
          elevation: 0,
          backgroundColor: const Color(0xffffffff),
          leading: Consumer(builder: (context, ref, _) {
            return InkWell(
              onTap: () {
                ref.read(navigatorProvider).pop();
              },
              child: const Icon(
                CupertinoIcons.back,
                color: MomoColor.black,
              ),
            );
          }),
          actions: [
            Padding(
              padding: const EdgeInsets.only(right: 16),
              child: SvgPicture.asset(
                'assets/icon/icon_msg_28.svg',
              ),
            ),
          ],
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
                      contents:
                          '안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 안녕하세요 필독 부탁드립니다 ',
                      img:
                          'https://w.namu.la/s/0b37ca973c31f23cf3e58137ce18c0668ba08220a176f480e7c66fa628a5724a4c90385043fe463af2acbcadedc3f58382a56164be6a1403b25572e6b50fda6345e94592db9bb9d1cac5cefc03604a06',
                      comments: 5,
                      date: '2021년 12월 31일 오후 11:00',
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
