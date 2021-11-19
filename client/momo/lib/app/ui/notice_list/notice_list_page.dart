import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/ui/notice_list/widget/notice_list_view.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class NoticeListPage extends StatelessWidget {
  const NoticeListPage({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xffffffff),
        appBar: AppBar(
          elevation: 0,
          backgroundColor: const Color(0xffffffff),
          leading: Consumer(builder: (context, ref, _) {
            return InkWell(
              onTap: () {
                ref.read(navigatorProvider).pop();
              },
              child: const Icon(
                Icons.arrow_back,
                color: MomoColor.black,
              ),
            );
          }),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: const [
              NoticeListView(),
            ],
          ),
        ),
      ),
    );
  }
}
