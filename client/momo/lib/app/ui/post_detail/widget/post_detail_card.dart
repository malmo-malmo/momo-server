import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/profile_avatar.dart';

class PostDetailCard extends StatelessWidget {
  const PostDetailCard({
    Key? key,
    required this.name,
    required this.profile,
    required this.title,
    required this.contents,
    required this.img,
    required this.comments,
  }) : super(key: key);

  final String name;
  final String profile;
  final String title;
  final String contents;
  final String img;
  final int comments;

  @override
  Widget build(BuildContext context) {
    return SliverToBoxAdapter(
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 24),
        child: Container(
          padding: const EdgeInsets.all(16),
          height: 383,
          width: double.infinity,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(20),
            color: Colors.yellowAccent,
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  profileAvatar(
                    img: profile,
                    rad: 18.w,
                  ),
                  const SizedBox(width: 16),
                  Text(name),
                ],
              ),
              const SizedBox(height: 16),
              Text(title),
              const SizedBox(height: 16),
              Text(contents),
              const SizedBox(height: 24),
              SizedBox(
                height: 100,
                width: 100,
                child: Image.network(img),
              ),
              const SizedBox(height: 16),
              Text('댓글 수 $comments'),
            ],
          ),
        ),
      ),
    );
  }
}
