import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class SearchedWordCards extends StatelessWidget {
  const SearchedWordCards({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Wrap(
      spacing: 8,
      runSpacing: 8,
      children: [
        _searchedWordCard(word: '국내여행'),
        _searchedWordCard(word: '영어'),
        _searchedWordCard(word: '산책로'),
        _searchedWordCard(word: '회화'),
        _searchedWordCard(word: '패러글라이딩'),
      ],
    );
  }

  Widget _searchedWordCard({required String word}) {
    return Material(
      elevation: 5,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 14),
        width: 14.0 * word.length + 56.w,
        height: 40,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
        ),
        child: Center(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                word,
                style: TextStyle(
                  color: MomoColor.black,
                  fontSize: 14.sp,
                ),
              ),
              Icon(
                Icons.cancel,
                color: MomoColor.unSelIcon,
                size: 10.w,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
