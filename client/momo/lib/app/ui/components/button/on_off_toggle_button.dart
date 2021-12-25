import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

class OnOffToggleButton extends StatefulWidget {
  const OnOffToggleButton({Key? key, required this.tabButton})
      : super(key: key);

  final Function(bool onOff) tabButton;

  @override
  _OnOffToggleButtonState createState() => _OnOffToggleButtonState();
}

class _OnOffToggleButtonState extends State<OnOffToggleButton> {
  List<bool> checks = [true, false];

  @override
  Widget build(BuildContext context) {
    return Row(children: [
      InkWell(
          borderRadius: BorderRadius.circular(22),
          onTap: () {
            widget.tabButton(true);
            checks = [true, false];
            setState(() {});
          },
          child: Container(
              height: 44,
              width: 91,
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(22),
                  color:
                      checks[0] ? MomoColor.main : MomoColor.backgroundColor),
              child: Center(
                  child: Text('온라인',
                      style: MomoTextStyle.small.copyWith(
                          color: checks[0]
                              ? MomoColor.white
                              : MomoColor.unSelIcon))))),
      const SizedBox(width: 16),
      InkWell(
          borderRadius: BorderRadius.circular(22),
          onTap: () {
            widget.tabButton(false);
            checks = [false, true];
            setState(() {});
          },
          child: Container(
              height: 44,
              width: 106,
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(22),
                  color:
                      checks[1] ? MomoColor.main : MomoColor.backgroundColor),
              child: Center(
                  child: Text('오프라인',
                      style: MomoTextStyle.small.copyWith(
                          color: checks[1]
                              ? MomoColor.white
                              : MomoColor.unSelIcon)))))
    ]);
  }
}
