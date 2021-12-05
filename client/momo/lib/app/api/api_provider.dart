import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/comment_client/comment_client.dart';
import 'package:momo/app/api/dio_provider.dart';
import 'package:momo/app/api/district_client/district_client.dart';
import 'package:momo/app/api/group_client/group_client.dart';
import 'package:momo/app/api/post_client/post_client.dart';
import 'package:momo/app/api/schedule_client/schedule_client.dart';
import 'package:momo/app/api/user_client/user_client.dart';
import 'package:momo/main.dart';

final userClientProvider = Provider<UserClient>((ref) {
  final dio = ref.watch(dioProvider);
  return UserClient(dio, baseUrl: baseUrl!);
});

final groupClientProvider = Provider<GroupClient>((ref) {
  final dio = ref.watch(dioProvider);
  return GroupClient(dio, baseUrl: baseUrl!);
});

final postClientProvider = Provider<PostClient>((ref) {
  final dio = ref.watch(dioProvider);
  return PostClient(dio, baseUrl: baseUrl!);
});

final scheduleClientProvider = Provider<ScheduleClient>((ref) {
  final dio = ref.watch(dioProvider);
  return ScheduleClient(dio, baseUrl: baseUrl!);
});

final commnetClientProvider = Provider<CommentClient>((ref) {
  final dio = ref.watch(dioProvider);
  return CommentClient(dio, baseUrl: baseUrl!);
});

final districtClientProvider = Provider<DistrictClient>((ref) {
  final dio = ref.watch(dioProvider);
  return DistrictClient(dio, baseUrl: baseUrl!);
});
