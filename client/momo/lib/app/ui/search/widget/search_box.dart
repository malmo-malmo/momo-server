import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/ui/search/widget/filter_bottom_sheet.dart';

class SearchBox extends ConsumerWidget {
  const SearchBox({
    Key? key,
    required this.onSearch,
  }) : super(key: key);

  final Function() onSearch;

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
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            InkWell(
              onTap: () {
                showModalBottomSheet(
                  context: context,
                  isScrollControlled: true,
                  builder: (context) {
                    return filterBottomSheet();
                  },
                );
              },
              child: SvgPicture.asset(
                'assets/icon/search/icon_filter_28.svg',
              ),
            ),
            SizedBox(
              height: 18,
              width: 240.w,
              child: const Padding(
                padding: EdgeInsets.symmetric(horizontal: 14),
                child: TextField(),
              ),
            ),
            InkWell(
              onTap: () {
                onSearch();
              },
              child: SvgPicture.asset(
                'assets/icon/search/icon_search_28.svg',
              ),
            ),
          ],
        ),
      ),
    );
  }
}
