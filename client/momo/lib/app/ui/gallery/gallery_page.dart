import 'dart:io';
import 'dart:typed_data';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/gallery/gallery_provider.dart';
import 'package:momo/app/provider/gallery/photo_provider.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class GalleryPage extends ConsumerWidget {
  GalleryPage({Key? key}) : super(key: key);

  File? file;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final photoState = ref.watch(photoListProvider);
    return SafeArea(
      child: Scaffold(
        body: photoState.when(
            loading: () => const Center(
                  child: CircularProgressIndicator(),
                ),
            error: (error, stack) => const Center(
                  child: Text(
                    '사진을 불러 올 수 없습니다...',
                  ),
                ),
            data: (photoList) {
              final checks = ref.watch(galleryProvider);
              final checkPhoto = ref.watch(isSelectPhoto);
              return Column(
                children: [
                  Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 16),
                    child: SizedBox(
                      height: 54,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Row(
                            children: [
                              Consumer(builder: (context, ref, _) {
                                return InkWell(
                                  onTap: () {
                                    ref.read(navigatorProvider).pop();
                                  },
                                  child: const Icon(
                                    CupertinoIcons.back,
                                    color: MomoColor.black,
                                  ),
                                );
                              }),
                              const SizedBox(width: 14),
                              Text(
                                '사진첩',
                                style: MomoTextStyle.defaultStyle,
                              ),
                            ],
                          ),
                          InkWell(
                            onTap: () {
                              ref
                                  .read(navigatorProvider)
                                  .pop(result: file!.path);
                            },
                            child: Container(
                              padding: const EdgeInsets.symmetric(vertical: 8),
                              height: 29,
                              width: 58,
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(20),
                                color: checkPhoto
                                    ? MomoColor.main
                                    : const Color(0xfff0f0f0),
                              ),
                              child: Center(
                                  child: Text(
                                '추가',
                                style: MomoTextStyle.small.copyWith(
                                  color: checkPhoto
                                      ? MomoColor.white
                                      : MomoColor.unSelIcon,
                                ),
                              )),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                  Expanded(
                    child: GridView.builder(
                      gridDelegate:
                          const SliverGridDelegateWithFixedCrossAxisCount(
                        crossAxisCount: 3,
                      ),
                      itemCount: photoList.length,
                      itemBuilder: (_, index) {
                        return FutureBuilder<Uint8List?>(
                          future: photoList[index].thumbDataWithSize(100, 100),
                          builder: (_, snapshot) {
                            final bytes = snapshot.data;
                            if (bytes == null) {
                              return const Center(
                                child: CircularProgressIndicator(),
                              );
                            }
                            return Consumer(builder: (context, ref, _) {
                              return InkWell(
                                onTap: () async {
                                  file = await photoList[index].file;
                                  ref
                                      .read(galleryStateProvider.notifier)
                                      .checkPhoto(index);
                                },
                                child: Stack(
                                  children: [
                                    Positioned.fill(
                                      child: Image.memory(
                                        bytes,
                                        fit: BoxFit.cover,
                                        gaplessPlayback: true,
                                      ),
                                    ),
                                    checks[index]
                                        ? Opacity(
                                            opacity: 0.4,
                                            child: Container(
                                              height: double.infinity,
                                              width: double.infinity,
                                              color: MomoColor.black,
                                            ),
                                          )
                                        : const SizedBox(),
                                    checks[index]
                                        ? const Center(
                                            child: Icon(
                                              Icons.check,
                                              color: MomoColor.white,
                                            ),
                                          )
                                        : const SizedBox(),
                                  ],
                                ),
                              );
                            });
                          },
                        );
                      },
                    ),
                  ),
                ],
              );
            }),
      ),
    );
  }
}
