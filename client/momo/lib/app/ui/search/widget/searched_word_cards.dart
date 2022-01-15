import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
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
      elevation: 3,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 17),
        width: 14.0 * word.length + 56,
        height: 40,
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
        child: Center(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                word,
                style: MomoTextStyle.normalR,
              ),
              const Icon(
                CupertinoIcons.xmark,
                color: MomoColor.unSelIcon,
                size: 16,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
