import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/util/theme.dart';

class FullImagePage extends StatelessWidget {
  const FullImagePage({Key? key, required this.img}) : super(key: key);

  final String img;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.black,
        appBar: const CustomAppBar(
          leadingIcon: CupertinoIcons.back,
          leadingIconColor: MomoColor.white,
          isAction: false,
          backgroundColor: Colors.transparent,
        ),
        extendBodyBehindAppBar: true,
        body: Padding(
          padding: const EdgeInsets.only(top: 120, bottom: 160),
          child: Image.network(
            img,
            height: double.infinity,
            width: double.infinity,
            fit: BoxFit.contain,
          ),
        ),
      ),
    );
  }
}
