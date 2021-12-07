import 'package:momo/app/model/enum/post_type.dart';

class PostRequestArg {
  final PostType postType;
  final int groupId;

  PostRequestArg({
    required this.postType,
    required this.groupId,
  });
}
