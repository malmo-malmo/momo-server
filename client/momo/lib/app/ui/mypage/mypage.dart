import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/group_card.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/mypage/widget/info_column.dart';
import 'package:momo/app/ui/mypage/widget/achievemint_card.dart';
import 'package:momo/app/ui/mypage/widget/user_category_column.dart';
import 'package:momo/app/util/navigation_service.dart';

class Mypage extends ConsumerWidget {
  const Mypage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userData = ref.watch(userDataProvider);

    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      height: double.infinity,
      width: double.infinity,
      color: MomoColor.flutterWhite,
      child: SingleChildScrollView(
        child: Column(
          children: [
            const SizedBox(height: 40),
            Align(
              alignment: Alignment.centerRight,
              child: InkWell(
                onTap: () {
                  ref
                      .read(navigatorProvider)
                      .navigateTo(routeName: AppRoutes.settings);
                },
                child: SvgPicture.asset(
                  'assets/icon/mypage/icon_setting_28.svg',
                ),
              ),
            ),
            const SizedBox(height: 16),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(
                  '${userData.nickname}님의\n마이페이지',
                  style: MomoTextStyle.mainTitle.copyWith(height: 1.2),
                ),
                ProfileAvatar(
                  img: userData.image ??
                      'https://file.mk.co.kr/meet/neds/2020/08/image_readtop_2020_864116_15980534304326707.png',
                  rad: 34,
                  backgroundColor: MomoColor.main,
                ),
              ],
            ),
            const SizedBox(height: 30),
            Container(
              height: 100,
              padding: const EdgeInsets.only(top: 16, right: 45, left: 45),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                color: MomoColor.scaffoldBackground,
              ),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  InfoColumn(
                    count: 8,
                    title: '총 모임',
                    onTap: () {},
                  ),
                  InfoColumn(
                    count: 6,
                    title: '찜한 모임',
                    onTap: () {
                      ref
                          .read(navigatorProvider)
                          .navigateTo(routeName: AppRoutes.wishGroup);
                    },
                  ),
                  InfoColumn(count: 10, title: '획득뱃지', onTap: () {}),
                ],
              ),
            ),
            const SizedBox(height: 14),
            const AchievementCard(),
            const SubTitle(
              title: '관심 카테고리',
              icon: 'assets/icon/mypage/icon_interestcategory_28.svg',
            ),
            SizedBox(
              height: 88,
              child: ListView.separated(
                scrollDirection: Axis.horizontal,
                itemBuilder: (context, index) {
                  if (index == 0) {
                    return Padding(
                      padding: const EdgeInsets.only(bottom: 24),
                      child: InkWell(
                        onTap: () {
                          ref
                              .read(navigatorProvider)
                              .navigateTo(routeName: AppRoutes.categoryEdit);
                        },
                        child: const CircleAvatar(
                          radius: 32,
                          backgroundColor: Color(0xffededed),
                          child: Icon(
                            Icons.add,
                            color: MomoColor.unSelIcon,
                          ),
                        ),
                      ),
                    );
                  }
                  return UserCategoryColumn(
                    categoryName: userData.categories[index - 1].name,
                  );
                },
                separatorBuilder: (context, index) => const SizedBox(width: 14),
                itemCount: userData.categories.length + 1,
              ),
            ),
            const SubTitle(
                title: '최근 본 모임',
                icon: 'assets/icon/search/icon_recentsee_28.svg'),
            SizedBox(
              height: 200,
              child: ListView.separated(
                scrollDirection: Axis.horizontal,
                itemBuilder: (context, index) {
                  return GroupCard(
                    group: GroupInfo(
                      id: 1,
                      name: '기초를 위한 영어 회화 모임',
                      offline: index % 2 == 0,
                      participantCnt: 10,
                      startDate: '2021-12-31',
                      favoriteGroup: index % 2 == 1,
                    ),
                    width: 148,
                    height: 200,
                    setLike: () {},
                  );
                },
                separatorBuilder: (context, index) => const SizedBox(width: 14),
                itemCount: 10,
              ),
            ),
            const SizedBox(height: 40),
          ],
        ),
      ),
    );
  }
}
