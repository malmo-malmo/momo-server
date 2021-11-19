import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/gallery/widget/asset_thumbnail.dart';
import 'package:momo/app/util/theme.dart';
import 'package:photo_manager/photo_manager.dart';

class GalleryPage extends StatefulWidget {
  const GalleryPage({Key? key}) : super(key: key);

  @override
  State<GalleryPage> createState() => _GalleryPageState();
}

class _GalleryPageState extends State<GalleryPage> {
  List<AssetEntity> assets = [];

  @override
  void initState() {
    _fetchAssets();
    super.initState();
  }

  _fetchAssets() async {
    final albums = await PhotoManager.getAssetPathList(onlyAll: true);
    final recentAlbum = albums.first;

    final recentAssets = await recentAlbum.getAssetListRange(
      start: 0,
      end: 1000000,
    );
    setState(
      () => assets = recentAssets
          .where((element) => element.type == AssetType.image)
          .toList(),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 36),
            child: SizedBox(
              height: 40,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  InkWell(
                    onTap: () {},
                    child: const Icon(
                      Icons.arrow_back,
                      color: MomoColor.black,
                    ),
                  ),
                  Text(
                    'Gallery',
                    style: TextStyle(
                      color: MomoColor.black,
                      fontSize: 16.sp,
                    ),
                  ),
                  Container(
                    padding: const EdgeInsets.symmetric(vertical: 8),
                    height: 29,
                    width: 58,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(20),
                      color: MomoColor.main,
                    ),
                    child: const Center(
                        child: Text(
                      '확인',
                      style: TextStyle(
                        color: MomoColor.black,
                      ),
                    )),
                  ),
                ],
              ),
            ),
          ),
          Expanded(
            child: GridView.builder(
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 3,
              ),
              itemCount: assets.length,
              itemBuilder: (_, index) {
                return AssetThumbnail(
                  asset: assets[index],
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
