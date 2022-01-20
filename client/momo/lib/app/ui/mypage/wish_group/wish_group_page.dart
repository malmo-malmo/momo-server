import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/group/wish_group_state_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/card/group_card.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';

class WishGroupPage extends ConsumerStatefulWidget {
  const WishGroupPage({Key? key}) : super(key: key);

  @override
  ConsumerState<WishGroupPage> createState() => _WishGroupPageState();
}

class _WishGroupPageState extends ConsumerState<WishGroupPage> {
  @override
  void initState() {
    super.initState();
    Future.delayed(Duration.zero, () {
      ref.read(wishGroupListProvider.notifier).getFavoriteGroups();
    });
  }

  @override
  Widget build(BuildContext context) {
    final wishGroupState = ref.watch(wishGroupListProvider);
    return SafeArea(
      child: Scaffold(
        appBar: const CustomAppBar(
          leadingIcon: CupertinoIcons.back,
          isAction: false,
          title: '찜한 모임',
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Consumer(
            builder: (context, ref, _) {
              if (wishGroupState.isLoading) {
                return const LoadingCard();
              }
              if (wishGroupState.error != null) {
                return const ErrorCard();
              }

              if (wishGroupState.groups.isEmpty) {
                return const Center(
                  child: Text('찜한 모임이 없습니다!'),
                );
              }

              return GridView.builder(
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2,
                  mainAxisExtent: 200.h,
                  mainAxisSpacing: 14,
                  crossAxisSpacing: 14,
                  childAspectRatio: 100 / 150,
                ),
                itemCount: wishGroupState.groups.length,
                itemBuilder: (context, index) {
                  return GroupCard(
                    group: wishGroupState.groups[index],
                    width: double.infinity,
                    setLike: () {
                      if (wishGroupState.groups[index].favoriteGroup) {
                        ref
                            .read(wishGroupListProvider.notifier)
                            .createLike(wishGroupState.groups[index].id);
                      } else {
                        ref
                            .read(wishGroupListProvider.notifier)
                            .deleteLike(wishGroupState.groups[index].id);
                      }
                    },
                  );
                },
              );
            },
          ),
        ),
      ),
    );
  }
}
