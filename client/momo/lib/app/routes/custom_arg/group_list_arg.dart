import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';

class GroupListArg {
  final String name;
  final PagingController<int, GroupInfo> pagingController;

  GroupListArg({
    required this.name,
    required this.pagingController,
  });
}
