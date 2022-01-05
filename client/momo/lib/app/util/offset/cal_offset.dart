import 'package:flutter/material.dart';

Offset getParentOffset(GlobalKey key) {
  RenderBox renderBox = key.currentContext!.findRenderObject()! as RenderBox;
  return Offset(
    renderBox.localToGlobal(Offset.zero).dx,
    renderBox.localToGlobal(Offset.zero).dy + renderBox.size.height,
  );
}
