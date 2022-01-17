import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:photo_manager/photo_manager.dart';

final photoPermissionProvider = FutureProvider<bool>((ref) async {
  PermissionState result = await PhotoManager.requestPermissionExtend();
  if (result.isAuth) {
    return true;
  }
  return false;
});
