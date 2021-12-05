import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget postCard({required Post post}) {
  return Padding(
    padding: const EdgeInsets.symmetric(vertical: 7),
    child: Consumer(builder: (context, ref, _) {
      return InkWell(
        onTap: () {
          ref.read(navigatorProvider).navigateTo(
                routeName: AppRoutes.postDetail,
                arguments: post.id,
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
                        backgroundImage: NetworkImage(post.authorImage ??
                            'https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/cbdef037365169.573db7853cebb.jpg'),
                      ),
                    ),
                    const SizedBox(width: 10),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          post.authorNickname,
                          style: MomoTextStyle.small,
                        ),
                        const SizedBox(height: 4),
                        Text(
                          post.createdDate,
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
                  post.title,
                  style: MomoTextStyle.defaultStyle,
                ),
                const SizedBox(height: 12),
                Text(
                  post.contents,
                  style: MomoTextStyle.small,
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                ),
              ],
            ),
          ),
        ),
      );
    }),
  );
}
