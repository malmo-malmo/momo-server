import 'package:flutter/material.dart';

Widget schoolResultDialog() {
  return Dialog(
    insetPadding: const EdgeInsets.all(1),
    child: Container(
      padding: const EdgeInsets.all(16),
      height: 300,
      width: 250,
      child: ListView.separated(
        itemBuilder: (context, index) {
          return Text('학교');
        },
        itemCount: 20,
        separatorBuilder: (context, index) => const SizedBox(height: 8),
      ),
    ),
  );
}
