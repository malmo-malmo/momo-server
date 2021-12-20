import 'package:flutter/material.dart';

class ErrorCard extends StatelessWidget {
  const ErrorCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text(
        '데이터를 불러올 수 없습니다!\n다시 시도해 주세요!',
      ),
    );
  }
}
