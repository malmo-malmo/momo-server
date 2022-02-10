class GroupListArg {
  final String name;
  final void Function({
    required int groupId,
    required bool favorite,
  }) likeCallback;

  GroupListArg({
    required this.name,
    required this.likeCallback,
  });
}
