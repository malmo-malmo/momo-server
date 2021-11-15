import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/meeting_list/widget/category_list.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class MeetingListPage extends StatelessWidget {
  const MeetingListPage({
    Key? key,
    required this.name,
  }) : super(key: key);

  final String name;

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
            children: [
              SizedBox(
                height: 80,
                child: Text(
                  name,
                  style: TextStyle(
                    fontSize: 24.sp,
                    color: MomoColor.black,
                  ),
                ),
              ),
              name == '추천' ? const CategoryList() : const SizedBox(),
              Expanded(
                child: GridView.builder(
                  gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 2,
                    mainAxisExtent: 150,
                    mainAxisSpacing: 5,
                    crossAxisSpacing: 5,
                  ),
                  itemBuilder: (context, index) {
                    return Card(
                      child: Center(
                        child: Text(
                          '$index',
                        ),
                      ),
                      color: Colors.teal[100 * (index % 9)],
                    );
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
