import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/theme/theme.dart';

class SearchedWordCards extends StatelessWidget {
  const SearchedWordCards({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Wrap(
      spacing: 8,
      runSpacing: 8,
      children: const [
        _SearchedWordCard(word: '국내여행'),
        _SearchedWordCard(word: '영어'),
        _SearchedWordCard(word: '산책로'),
        _SearchedWordCard(word: '회화'),
        _SearchedWordCard(word: '패러글라이딩'),
      ],
    );
  }
}

class _SearchedWordCard extends StatelessWidget {
  const _SearchedWordCard({
    Key? key,
    required this.word,
  }) : super(key: key);

  final String word;

  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 1,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 17),
        width: 14.0 * word.length + 56,
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
                style: MomoTextStyle.normalR,
              ),
              Icon(
                CupertinoIcons.xmark,
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
