import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:photo_manager/photo_manager.dart';

class AssetThumbnail extends ConsumerWidget {
  const AssetThumbnail({
    Key? key,
    required this.asset,
  }) : super(key: key);

  final AssetEntity asset;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return FutureBuilder<Uint8List?>(
      future: asset.thumbData,
      builder: (_, snapshot) {
        final bytes = snapshot.data;
        if (bytes == null) {
          return const Center(
            child: CircularProgressIndicator(),
          );
        }
        return InkWell(
          onTap: () async {
            final file = await asset.file;
            if (asset.type == AssetType.image) {
              ref.read(navigatorProvider).pop(result: file!.path);
            }
          },
          child: Stack(
            children: [
              Positioned.fill(
                child: Image.memory(bytes, fit: BoxFit.cover),
              ),
              if (asset.type == AssetType.video)
                Center(
                  child: Container(
                    color: Colors.blue,
                    child: const Icon(
                      Icons.play_arrow,
                      color: Colors.white,
                    ),
                  ),
                ),
            ],
          ),
        );
      },
    );
  }
}
