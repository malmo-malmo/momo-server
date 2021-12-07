import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/constant.dart';
import 'package:photo_manager/photo_manager.dart';

final photoListProvider =
    FutureProvider.autoDispose<List<AssetEntity>>((ref) async {
  final albums = await PhotoManager.getAssetPathList(onlyAll: true);
  final recentAlbum = albums.first;

  final recentAssets = await recentAlbum.getAssetListRange(
    start: 0,
    end: maxPhotoCount,
  );

  return recentAssets
      .where((element) => element.type == AssetType.image)
      .toList();
});
