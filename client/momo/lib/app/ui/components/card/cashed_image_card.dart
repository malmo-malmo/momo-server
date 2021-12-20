import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';

class CachedImageCard extends StatelessWidget {
  const CachedImageCard({
    Key? key,
    required this.imageUrl,
    required this.height,
    required this.width,
    this.fit,
  }) : super(key: key);

  final String imageUrl;
  final double height;
  final double width;
  final BoxFit? fit;

  @override
  Widget build(BuildContext context) {
    return CachedNetworkImage(
      imageUrl: imageUrl,
      placeholder: (context, url) => const LoadingCard(),
      errorWidget: (context, url, error) => const ErrorCard(),
      fit: fit ?? BoxFit.fill,
      width: width,
      height: height,
    );
  }
}
