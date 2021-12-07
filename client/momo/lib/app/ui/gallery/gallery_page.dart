import 'dart:typed_data';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:momo/app/model/enum/photo_request_type.dart';
import 'package:momo/app/provider/gallery/gallery_provider.dart';
import 'package:momo/app/provider/gallery/photo_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/confirm_action_icon.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

/// 모임 생성 시에는 1개 제한
/// 공지사항, 게시글은 7개?
class GalleryPage extends ConsumerStatefulWidget {
  const GalleryPage({
    Key? key,
    required this.requestType,
  }) : super(key: key);

  final PhotoRequestType requestType;

  @override
  ConsumerState<GalleryPage> createState() => _GalleryPageState();
}

class _GalleryPageState extends ConsumerState<GalleryPage> {
  List<String> images = [];

  late FToast fToast;

  @override
  void initState() {
    super.initState();
    fToast = FToast();
    fToast.init(context);
  }

  @override
  Widget build(BuildContext context) {
    final photoState = ref.watch(photoListProvider);
    return SafeArea(
      child: photoState.when(
          loading: () => Scaffold(body: loadingCard()),
          error: (error, stack) =>
              const Scaffold(body: Center(child: Text('사진을 불러 올 수 없습니다...'))),
          data: (photoList) {
            final checks = ref.watch(galleryProvider);
            final checkPhoto = ref.watch(isSelectPhoto);

            final isMax = ref.watch(checkMaxPhoto);

            return Scaffold(
              appBar: customAppBar(
                leadingIcon: CupertinoIcons.back,
                isAction: true,
                title: '사진첩',
                actionWidget: confirmActionIcon(
                  check: checkPhoto,
                  title: '추가',
                  onTapIcon: () {},
                  result: widget.requestType == PhotoRequestType.one
                      ? images.last
                      : images,
                  isShowDialog: false,
                ),
              ),
              body: Column(
                children: [
                  Expanded(
                    child: GridView.builder(
                      gridDelegate:
                          const SliverGridDelegateWithFixedCrossAxisCount(
                              crossAxisCount: 3),
                      itemCount: photoList.length,
                      itemBuilder: (_, index) {
                        return FutureBuilder<Uint8List?>(
                          future: photoList[index].thumbData,
                          builder: (_, snapshot) {
                            final bytes = snapshot.data;
                            if (bytes == null) {
                              return loadingCard();
                            }
                            return Consumer(builder: (context, ref, _) {
                              return InkWell(
                                onTap: widget.requestType ==
                                        PhotoRequestType.one
                                    ? () {
                                        ref
                                            .read(galleryStateProvider.notifier)
                                            .checkOne(index);
                                      }
                                    : (isMax && !checks[index]
                                        ? _showToast
                                        : () async {
                                            await photoList[index].file.then(
                                                (value) =>
                                                    images.add(value!.path));

                                            ref
                                                .read(galleryStateProvider
                                                    .notifier)
                                                .toggle(index);
                                          }),
                                child: _imageThumbnail(
                                  check: checks[index],
                                  imageData: bytes,
                                ),
                              );
                            });
                          },
                        );
                      },
                    ),
                  ),
                ],
              ),
            );
          }),
    );
  }

  Widget _imageThumbnail({required bool check, required Uint8List imageData}) {
    return Stack(
      children: [
        Positioned.fill(
            child: Image.memory(imageData,
                fit: BoxFit.cover, gaplessPlayback: true)),
        check
            ? Opacity(
                opacity: 0.4,
                child: Container(
                    height: double.infinity,
                    width: double.infinity,
                    color: MomoColor.black))
            : const SizedBox(),
        check
            ? const Center(child: Icon(Icons.check, color: MomoColor.white))
            : const SizedBox(),
      ],
    );
  }

  void _showToast() {
    fToast.showToast(
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 24.0, vertical: 12.0),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(25.0),
          color: MomoColor.main,
        ),
        child: Text(
          '최대 7장만 선택 할 수 있습니다!',
          style: MomoTextStyle.defaultStyle.copyWith(
            color: MomoColor.white,
          ),
        ),
      ),
      gravity: ToastGravity.BOTTOM,
      toastDuration: const Duration(seconds: 1),
    );
  }
}
