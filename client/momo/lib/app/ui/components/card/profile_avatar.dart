import 'dart:io';

import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class ProfileAvatar extends StatelessWidget {
  const ProfileAvatar({
    Key? key,
    required this.img,
    required this.rad,
    this.backgroundColor,
  }) : super(key: key);

  final String img;
  final double rad;
  final Color? backgroundColor;

  @override
  Widget build(BuildContext context) {
    return CircleAvatar(
      radius: rad + 1,
      backgroundColor: backgroundColor ?? MomoColor.white,
      child: CircleAvatar(
        radius: rad,
        backgroundColor: Colors.transparent,
        backgroundImage: NetworkImage(img),
      ),
    );
  }
}

class ProfileAvatarWithFile extends StatelessWidget {
  const ProfileAvatarWithFile({
    Key? key,
    required this.imagePath,
  }) : super(key: key);

  final String imagePath;
  @override
  Widget build(BuildContext context) {
    return CircleAvatar(
      radius: 51,
      backgroundColor: MomoColor.main,
      child: CircleAvatar(
        radius: 50,
        backgroundColor: Colors.transparent,
        backgroundImage: FileImage(File(imagePath)),
      ),
    );
  }
}
