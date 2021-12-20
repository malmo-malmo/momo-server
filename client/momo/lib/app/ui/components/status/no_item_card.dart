import 'package:flutter/material.dart';

class NoItemCard extends StatelessWidget {
  const NoItemCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text(
        '아이템이 없습니다!',
      ),
    );
  }
}
