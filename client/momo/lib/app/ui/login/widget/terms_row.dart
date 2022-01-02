import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class TermsRow extends StatelessWidget {
  const TermsRow({
    Key? key,
    required this.check,
    required this.title,
    required this.onCheck,
  }) : super(key: key);

  final bool check;
  final String title;
  final Function onCheck;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              InkWell(
                borderRadius: BorderRadius.circular(24),
                onTap: () {
                  onCheck();
                },
                child: CircleAvatar(
                  radius: 15,
                  backgroundColor:
                      check ? const Color(0xffbca9f7) : const Color(0xffdedede),
                  child: const Icon(
                    Icons.check,
                    size: 20,
                    color: Color(0xfffdfdfd),
                  ),
                ),
              ),
              const SizedBox(width: 10),
              Text(
                title,
                style: MomoTextStyle.normal,
              ),
            ],
          ),
          Text(
            '보기',
            style: MomoTextStyle.small
                .copyWith(decoration: TextDecoration.underline),
          )
        ],
      ),
    );
  }
}
