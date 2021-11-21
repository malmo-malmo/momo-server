import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/member/member.dart';
import 'package:momo/app/ui/member_list/widget/member_card.dart';

class MemberList extends StatefulWidget {
  const MemberList({Key? key}) : super(key: key);

  @override
  State<MemberList> createState() => _MemberListState();
}

class _MemberListState extends State<MemberList> {
  final PagingController<int, Member> _pagingController =
      PagingController(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((pageKey) {
      _fetchPage(pageKey);
    });
    super.initState();
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  Future<void> _fetchPage(int pageKey) async {
    try {
      await Future.delayed(const Duration(seconds: 1));
      final newItems = List.generate(
        10,
        (index) => Member(
          id: index,
          name: '김모모',
          profile: 'https://t1.daumcdn.net/cfile/blog/999B27505C55A61321',
          rate: 90,
        ),
      );
      const isLastPage = false;
      if (isLastPage) {
        _pagingController.appendLastPage(newItems);
      } else {
        final nextPageKey = pageKey + newItems.length;
        _pagingController.appendPage(newItems, nextPageKey);
      }
    } catch (error) {
      _pagingController.error = error;
    }
  }

  @override
  Widget build(BuildContext context) {
    return PagedListView.separated(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<Member>(
        itemBuilder: (context, item, index) => memberCard(
          name: item.name,
          profile: item.profile,
          rate: item.rate,
        ),
      ),
      separatorBuilder: (context, index) => const SizedBox(height: 8),
    );
  }
}
