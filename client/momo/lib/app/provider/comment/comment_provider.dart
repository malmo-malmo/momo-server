import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/repository/comment_repository.dart';

class CommentState extends StateNotifier<List<Comment>> {
  CommentState(
    List<Comment> state, {
    required this.commentRepository,
  }) : super(state);

  final CommentRepository commentRepository;
}
