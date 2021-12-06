import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

class UniversityToggleButton extends StatefulWidget {
  const UniversityToggleButton({
    Key? key,
    required this.tabButton,
  }) : super(key: key);

  final void Function(bool onOff) tabButton;

  @override
  _UniversityToggleButtonState createState() => _UniversityToggleButtonState();
}

class _UniversityToggleButtonState extends State<UniversityToggleButton> {
  List<bool> checks = [true, false];

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        InkWell(
          borderRadius: BorderRadius.circular(22),
          onTap: () {
            widget.tabButton(true);
            setState(() => checks = [true, false]);
          },
          child: Container(
            height: 44,
            width: 91,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(22),
              color: checks[0] ? MomoColor.main : MomoColor.backgroundColor,
            ),
            child: Center(
              child: Text(
                '포함',
                style: MomoTextStyle.small.copyWith(
                  color: checks[0] ? MomoColor.white : MomoColor.unSelIcon,
                ),
              ),
            ),
          ),
        ),
        const SizedBox(width: 16),
        InkWell(
          borderRadius: BorderRadius.circular(22),
          onTap: () {
            widget.tabButton(false);
            setState(() => checks = [false, true]);
          },
          child: Container(
            height: 44,
            width: 106,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(22),
              color: checks[1] ? MomoColor.main : MomoColor.backgroundColor,
            ),
            child: Center(
              child: Text(
                '미포함',
                style: MomoTextStyle.small.copyWith(
                  color: checks[1] ? MomoColor.white : MomoColor.unSelIcon,
                ),
              ),
            ),
          ),
        ),
      ],
    );
  }
}
