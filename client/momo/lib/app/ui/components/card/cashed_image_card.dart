import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';

Widget cachedImageCard({
  required String imageUrl,
  required double height,
  required double width,
  BoxFit? fit,
}) {
  return CachedNetworkImage(
    imageUrl: imageUrl,
    placeholder: (context, url) => loadingCard(),
    errorWidget: (context, url, error) => errorCard(),
    fit: fit ?? BoxFit.fill,
    width: width,
    height: height,
  );
}
