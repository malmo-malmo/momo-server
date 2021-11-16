import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

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
            Icon(
              CupertinoIcons.color_filter,
              size: 28.w,
            ),
            SizedBox(
              height: 18,
              width: 240.w,
              child: const Padding(
                padding: EdgeInsets.symmetric(horizontal: 14),
                child: TextField(),
              ),
            ),
            Icon(
              CupertinoIcons.search,
              size: 28.w,
            ),
          ],
        ),
      ),
    );
  }
}
