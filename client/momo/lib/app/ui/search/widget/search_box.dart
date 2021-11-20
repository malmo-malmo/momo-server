import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';

class SearchBox extends StatelessWidget {
  const SearchBox({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
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
            SvgPicture.asset(
              'assets/icon/search/icon_filter_28.svg',
            ),
            SizedBox(
              height: 18,
              width: 240.w,
              child: const Padding(
                padding: EdgeInsets.symmetric(horizontal: 14),
                child: TextField(),
              ),
            ),
            SvgPicture.asset(
              'assets/icon/search/icon_search_28.svg',
            ),
          ],
        ),
      ),
    );
  }
}
