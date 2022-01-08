import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/navigation_service.dart';

final settingNavigatorProvider =
    Provider<NavigationService>((ref) => NavigationService());
