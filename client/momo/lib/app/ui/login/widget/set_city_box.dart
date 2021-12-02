import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

Widget cityInputBox() {
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 16),
    height: 44,
    width: 132,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(16),
      color: const Color(0xffffffff),
    ),
    child: const Center(
      child: Text('서울'),
    ),
  );
}
