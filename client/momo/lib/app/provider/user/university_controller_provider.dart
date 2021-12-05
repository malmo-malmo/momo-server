import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final universityTextController = Provider.autoDispose<TextEditingController>(
    (ref) => TextEditingController());
