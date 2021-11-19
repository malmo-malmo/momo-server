import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/post/post_request_provider.dart';
import 'package:momo/app/util/theme.dart';

Widget imgCard({required String img}) {
  return img.isEmpty
      ? const SizedBox()
      : Consumer(
          builder: (context, ref, _) {
            return SizedBox(
              height: 100,
              width: 100,
              child: Stack(
                children: [
                  SizedBox(
                    height: 100,
                    width: 100,
                    child: Image.file(
                      File(img),
                      fit: BoxFit.fill,
                    ),
                  ),
                  Align(
                    alignment: Alignment.topRight,
                    child: InkWell(
                      onTap: () {
                        ref
                            .read(postRequestStateProvider.notifier)
                            .setImage('');
                      },
                      child: const Icon(
                        CupertinoIcons.xmark,
                        color: MomoColor.white,
                      ),
                    ),
                  ),
                ],
              ),
            );
          },
        );
}
