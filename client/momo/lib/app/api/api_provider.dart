import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/comment_client/comment_client.dart';
import 'package:momo/app/api/dio_provider.dart';
import 'package:momo/app/api/district_client/district_client.dart';
import 'package:momo/app/api/group_client/group_client.dart';
import 'package:momo/app/api/management_client/management_client.dart';
import 'package:momo/app/api/post_client/post_client.dart';
import 'package:momo/app/api/schedule_client/schedule_client.dart';
import 'package:momo/app/api/user_client/user_client.dart';

final userClientProvider = Provider<UserClient>((ref) {
  final dio = ref.watch(dioProvider);
  return UserClient(dio);
});

final groupClientProvider = Provider<GroupClient>((ref) {
  final dio = ref.watch(dioProvider);
  return GroupClient(dio);
});

final postClientProvider = Provider<PostClient>((ref) {
  final dio = ref.watch(dioProvider);
  return PostClient(dio);
});

final scheduleClientProvider = Provider<ScheduleClient>((ref) {
  final dio = ref.watch(dioProvider);
  return ScheduleClient(dio);
});

final commnetClientProvider = Provider<CommentClient>((ref) {
  final dio = ref.watch(dioProvider);
  return CommentClient(dio);
});

final districtClientProvider = Provider<DistrictClient>((ref) {
  final dio = ref.watch(dioProvider);
  return DistrictClient(dio);
});

final managementClientProvider = Provider<ManagementClient>((ref) {
  final dio = ref.watch(dioProvider);
  return ManagementClient(dio);
});
