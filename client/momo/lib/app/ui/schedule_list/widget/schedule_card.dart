import 'package:flutter/material.dart';
import 'package:momo/app/ui/components/profile_avatar.dart';

Widget scheduleCard({
  required String name,
  required String profile,
  required String title,
  required String contents,
  required bool attendance,
}) {
  return Container(
    padding: const EdgeInsets.all(16),
    height: 141,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(16),
      color: Colors.orangeAccent,
    ),
    child: Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Row(
              children: [
                profileAvatar(img: profile, rad: 18),
                const SizedBox(width: 16),
                Text(name),
              ],
            ),
            Container(
              height: 33,
              width: 70,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(16),
                color: Colors.lightGreenAccent,
              ),
              child: const Center(
                child: Text('출석'),
              ),
            ),
          ],
        ),
        Text(title),
        Text(contents),
      ],
    ),
  );
}
