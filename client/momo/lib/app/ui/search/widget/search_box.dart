import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/search/search_result_provider.dart';
import 'package:momo/app/ui/search/widget/filter_bottom_sheet.dart';

class SearchBox extends ConsumerWidget {
  const SearchBox({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Material(
      elevation: 5,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        height: 44,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          boxShadow: const [
            BoxShadow(
              blurRadius: 6,
              offset: Offset(0, 0),
              color: Color(0x1aa59ad0),
              blurStyle: BlurStyle.outer,
              spreadRadius: 0,
            )
          ],
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            InkWell(
              onTap: () {
                showModalBottomSheet(
                  context: context,
                  isScrollControlled: true,
                  builder: (context) => const FilterBottomSheet(),
                );
              },
              child: SvgPicture.asset(
                'assets/icon/search/icon_filter_28.svg',
              ),
            ),
            SizedBox(
              height: 44,
              width: 240.w,
              child: const Padding(
                padding: EdgeInsets.symmetric(horizontal: 14),
                child: TextField(
                  decoration: InputDecoration(
                    hintText: '검색어를 입력하세요',
                  ),
                ),
              ),
            ),
            InkWell(
              onTap: () =>
                  ref.read(isShowResultStateProvider.state).state = true,
              child: SvgPicture.asset('assets/icon/search/icon_search_28.svg'),
            ),
          ],
        ),
      ),
    );
  }
}
