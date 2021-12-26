import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/theme/theme.dart';

Widget imgCard({
  required String img,
  required void Function(String img) deleteImg,
}) {
  return img.isEmpty
      ? const SizedBox(
          height: 100,
          width: 100,
          child: Center(
            child: Text(
              'No Image',
            ),
          ),
        )
      : Consumer(
          builder: (context, ref, _) {
            return SizedBox(
              height: 100,
              width: 100,
              child: Stack(
                children: [
                  ClipRRect(
                    borderRadius: BorderRadius.circular(16),
                    child: SizedBox(
                      height: 100,
                      width: 100,
                      child: Image.file(
                        File(img),
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                  Align(
                    alignment: Alignment.topRight,
                    child: Padding(
                      padding: const EdgeInsets.all(4),
                      child: InkWell(
                        onTap: () => deleteImg(img),
                        child: const Icon(
                          CupertinoIcons.xmark,
                          color: MomoColor.white,
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            );
          },
        );
}
