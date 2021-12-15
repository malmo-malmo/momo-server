import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/provider/post/post_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/format/post_date_format.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class PostCard extends StatefulWidget {
  const PostCard({Key? key, required this.post}) : super(key: key);

  final Post post;

  @override
  State<PostCard> createState() => _PostCardState();
}

class _PostCardState extends State<PostCard> {
  int? _commentCnt;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 7),
      child: Consumer(builder: (context, ref, _) {
        final postState = ref.watch(postProvider(widget.post));
        return InkWell(
          onTap: () async {
            final commentCnt = await ref.read(navigatorProvider).navigateTo(
                  routeName: AppRoutes.postDetail,
                  arguments: widget.post,
                );
            _commentCnt = commentCnt;
            setState(() {});
          },
          child: Container(
            height: 182,
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
                          backgroundImage: NetworkImage(widget
                                  .post.authorImage ??
                              'https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/cbdef037365169.573db7853cebb.jpg'),
                        ),
                      ),
                      const SizedBox(width: 10),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            widget.post.authorNickname,
                            style: MomoTextStyle.small,
                          ),
                          const SizedBox(height: 4),
                          Text(
                            postDateFormat(widget.post.createdDate),
                            style: MomoTextStyle.small.copyWith(
                              color: const Color(0xff9e9e9e),
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),
                  Text(widget.post.title, style: MomoTextStyle.defaultStyle),
                  const SizedBox(height: 12),
                  Text(widget.post.contents,
                      style: MomoTextStyle.normal,
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis),
                  const SizedBox(height: 12),
                  Text(
                    '댓글 수 ${_commentCnt ?? postState.commentCnt}',
                    style: MomoTextStyle.small.copyWith(
                        color: MomoColor.unSelIcon,
                        fontWeight: FontWeight.w400),
                  ),
                ],
              ),
            ),
          ),
        );
      }),
    );
  }
}
