import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class FeedCard extends ConsumerWidget {
  const FeedCard({
    Key? key,
    required this.postId,
    required this.profile,
    required this.text,
    required this.comments,
    required this.userName,
    required this.title,
    required this.date,
  }) : super(key: key);

  final int postId;
  final String profile;
  final String userName;
  final String title;
  final String text;
  final int comments;
  final String date;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 7),
      child: InkWell(
        onTap: () {
          ref.read(navigatorProvider).navigateTo(
                routeName: AppRoutes.postDetail,
                arguments: postId,
              );
        },
        child: Container(
          height: 160,
          decoration: BoxDecoration(
            color: const Color(0xffffffff),
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
                        backgroundImage: NetworkImage(profile),
                      ),
                    ),
                    const SizedBox(width: 10),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          userName,
                          style: MomoTextStyle.small,
                        ),
                        const SizedBox(height: 4),
                        Text(
                          date,
                          style: MomoTextStyle.small.copyWith(
                            color: const Color(0xff9e9e9e),
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                Text(
                  title,
                  style: MomoTextStyle.defaultStyle,
                ),
                const SizedBox(height: 12),
                Text(
                  text,
                  style: MomoTextStyle.small,
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
