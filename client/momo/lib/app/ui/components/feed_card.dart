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
  }) : super(key: key);

  final int postId;
  final String profile;
  final String userName;
  final String title;
  final String text;
  final int comments;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 16),
      child: InkWell(
        onTap: () {
          ref.read(navigatorProvider).navigateTo(
                routeName: AppRoutes.postDetail,
                arguments: postId,
              );
        },
        child: Card(
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
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
                    Text(userName)
                  ],
                ),
                Text(title),
                const SizedBox(height: 8),
                Text(text),
                const SizedBox(height: 8),
                Text('댓글 수 $comments'),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
