import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class TermsAllCheckBox extends StatelessWidget {
  const TermsAllCheckBox({
    Key? key,
    required this.check,
    required this.onCheck,
  }) : super(key: key);

  final bool check;
  final Function onCheck;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      height: 56,
      width: double.infinity,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: MomoColor.white,
      ),
      child: Row(
        children: [
          InkWell(
            onTap: () {
              onCheck();
            },
            child: CircleAvatar(
              radius: 15,
              backgroundColor: check ? MomoColor.main : MomoColor.unSelIcon,
              child: const Icon(
                Icons.check,
                size: 20,
                color: MomoColor.white,
              ),
            ),
          ),
          const SizedBox(width: 16),
          Text(
            '전체 동의',
            style: MomoTextStyle.defaultStyle
                .copyWith(color: check ? MomoColor.main : MomoColor.unSelIcon),
          ),
        ],
      ),
    );
  }
}
