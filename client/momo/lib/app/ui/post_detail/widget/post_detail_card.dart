import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class PostDetailCard extends StatelessWidget {
  const PostDetailCard({
    Key? key,
    required this.name,
    required this.profile,
    required this.title,
    required this.contents,
    required this.img,
    required this.comments,
    required this.date,
  }) : super(key: key);

  final String name;
  final String profile;
  final String title;
  final String contents;
  final String img;
  final int comments;
  final String date;

  @override
  Widget build(BuildContext context) {
    return SliverToBoxAdapter(
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 24),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              height: 72,
              padding: const EdgeInsets.all(16),
              width: double.infinity,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      profileAvatar(
                        img: profile,
                        rad: 18,
                      ),
                      const SizedBox(width: 16),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            name,
                            style: MomoTextStyle.normal.copyWith(
                              fontWeight: FontWeight.w400,
                            ),
                          ),
                          const SizedBox(height: 6),
                          Text(
                            date,
                            style: MomoTextStyle.small.copyWith(
                              fontWeight: FontWeight.w400,
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
            Text(
              title,
              style: MomoTextStyle.defaultStyle,
            ),
            const SizedBox(height: 20),
            Text(
              contents,
              style: MomoTextStyle.normal.copyWith(
                fontWeight: FontWeight.w400,
              ),
            ),
            const SizedBox(height: 32),
            Consumer(builder: (context, ref, _) {
              return InkWell(
                borderRadius: BorderRadius.circular(16),
                onTap: () {
                  ref.read(navigatorProvider).navigateTo(
                        routeName: AppRoutes.fullImage,
                        arguments: img,
                      );
                },
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(16),
                  child: Image.network(
                    img,
                    width: 72,
                    height: 72,
                    fit: BoxFit.fill,
                  ),
                ),
              );
            }),
            const SizedBox(height: 30),
            Container(
              height: 1,
              color: MomoColor.backgroundColor,
            ),
            const SizedBox(height: 20),
            Text(
              '댓글 수 $comments',
              style: MomoTextStyle.small.copyWith(
                fontWeight: FontWeight.w400,
                color: MomoColor.unSelIcon,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
