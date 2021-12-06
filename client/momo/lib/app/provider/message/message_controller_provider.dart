import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final messageTextControllerProvider =
    Provider.autoDispose<TextEditingController>((ref) {
  final _controller = TextEditingController();

  ref.onDispose(() => _controller.dispose());

  return _controller;
});
