import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class FullImagePage extends StatelessWidget {
  const FullImagePage({
    Key? key,
    required this.img,
  }) : super(key: key);

  final String img;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.black,
        appBar: AppBar(
          elevation: 0,
          backgroundColor: Colors.transparent,
          leading: Consumer(builder: (context, ref, _) {
            return InkWell(
              onTap: () {
                ref.read(navigatorProvider).pop();
              },
              child: const Icon(
                CupertinoIcons.back,
                color: MomoColor.white,
              ),
            );
          }),
        ),
        extendBodyBehindAppBar: true,
        body: Padding(
          padding: const EdgeInsets.only(top: 204, bottom: 234),
          child: Image.network(
            img,
            height: double.infinity,
            width: double.infinity,
            fit: BoxFit.fill,
          ),
        ),
      ),
    );
  }
}
