import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/card/group_card.dart';

class WishGroupPage extends StatefulWidget {
  const WishGroupPage({Key? key}) : super(key: key);

  @override
  State<WishGroupPage> createState() => _WishGroupPageState();
}

class _WishGroupPageState extends State<WishGroupPage> {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: const CustomAppBar(
          leadingIcon: CupertinoIcons.back,
          isAction: false,
          title: '찜한 모임',
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: GridView.builder(
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 2,
              mainAxisExtent: 200.h,
              mainAxisSpacing: 14,
              crossAxisSpacing: 14,
              childAspectRatio: 100 / 150,
            ),
            itemBuilder: (context, index) {
              return GroupCard(
                group: GroupInfo(
                  id: 1,
                  name: '내가 찜한 그룹',
                  offline: index % 2 == 1,
                  participantCnt: index,
                  startDate: '2022-01-01',
                  favoriteGroup: index % 2 == 1,
                ),
                width: double.infinity,
                setLike: () {},
              );
            },
          ),
        ),
      ),
    );
  }
}
