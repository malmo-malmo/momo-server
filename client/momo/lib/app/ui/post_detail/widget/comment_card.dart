import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';

class CommentCard extends StatelessWidget {
  const CommentCard({Key? key, required this.comment}) : super(key: key);

  final Comment comment;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 24),
      height: 80,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: MomoColor.backgroundColor,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              ProfileAvatar(
                img: comment.authorImage ??
                    'https://biz.chosun.com/resizer/kh_pcdsIH0PJWIXenLBD4Oi94Wg=/464x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/HAXYB5XB4CCHXUB6VQVALOZFVY.jpg',
                rad: 18.w,
              ),
              const SizedBox(width: 16),
              Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    comment.authorNickname,
                    style: MomoTextStyle.defaultStyle,
                  ),
                  Text(
                    comment.contents,
                    style: MomoTextStyle.normal,
                  ),
                ],
              ),
            ],
          ),
          Text(
            '삭제',
            style: MomoTextStyle.small.copyWith(
              color: MomoColor.unSelText,
              decoration: TextDecoration.underline,
            ),
          ),
        ],
      ),
    );
  }
}
