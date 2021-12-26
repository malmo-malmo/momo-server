import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post_detail.dart';
import 'package:momo/app/provider/post/post_detail_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/util/format/post_date_format.dart';
import 'package:momo/app/util/navigation_service.dart';

class PostDetailCard extends StatelessWidget {
  const PostDetailCard({Key? key, required this.postDetail}) : super(key: key);

  final PostDetail postDetail;

  @override
  Widget build(BuildContext context) {
    return SliverToBoxAdapter(
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 24),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SizedBox(
              height: 72,
              width: double.infinity,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      ProfileAvatar(
                        img: postDetail.authorImage ??
                            'https://www.theguru.co.kr/data/photos/20210937/art_16316071303022_bf8378.jpg',
                        rad: 18,
                      ),
                      const SizedBox(width: 16),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            postDetail.authorNickname,
                            style: MomoTextStyle.normalR,
                          ),
                          const SizedBox(height: 6),
                          Text(
                            postDateFormat(postDetail.createdDate),
                            style: MomoTextStyle.smallR.copyWith(
                              color: MomoColor.unSelIcon,
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ],
              ),
            ),
            Text(postDetail.title, style: MomoTextStyle.defaultStyle),
            const SizedBox(height: 20),
            Text(postDetail.contents, style: MomoTextStyle.normalR),
            const SizedBox(height: 32),
            Consumer(builder: (context, ref, _) {
              return Wrap(
                spacing: 8,
                runSpacing: 8,
                children: [
                  for (int i = 0; i < postDetail.imageUrls.length; i++)
                    InkWell(
                      borderRadius: BorderRadius.circular(16),
                      onTap: () {
                        ref.read(navigatorProvider).navigateTo(
                              routeName: AppRoutes.fullImage,
                              arguments: postDetail.imageUrls[i],
                            );
                      },
                      child: ClipRRect(
                        borderRadius: BorderRadius.circular(16),
                        child: Image.network(
                          postDetail.imageUrls[i],
                          width: 72,
                          height: 72,
                          fit: BoxFit.cover,
                        ),
                      ),
                    ),
                ],
              );
            }),
            const SizedBox(height: 30),
            Container(height: 1, color: MomoColor.backgroundColor),
            const SizedBox(height: 20),
            Consumer(builder: (context, ref, _) {
              return Text(
                '댓글 수 ${ref.watch(postDetailCommentCntStateProvider)}',
                style: MomoTextStyle.smallR.copyWith(
                  color: MomoColor.unSelIcon,
                ),
              );
            }),
          ],
        ),
      ),
    );
  }
}
