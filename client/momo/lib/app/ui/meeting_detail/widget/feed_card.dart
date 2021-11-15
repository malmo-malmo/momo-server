import 'package:flutter/material.dart';

class FeedCard extends StatelessWidget {
  const FeedCard({
    Key? key,
    required this.img,
    required this.text,
    required this.comments,
  }) : super(key: key);

  final String img;
  final String text;
  final int comments;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 16),
      child: Card(
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Image.network(img),
              const SizedBox(height: 8),
              Text('댓글: $comments'),
              const SizedBox(height: 8),
              Text(text),
            ],
          ),
        ),
      ),
    );
  }
}
