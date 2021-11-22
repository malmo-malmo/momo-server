import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/on_off_toggle_provider.dart';
import 'package:momo/app/util/theme.dart';

Widget onOffToggleButton({
  required Function(bool onOff) tabButton,
}) {
  return Consumer(builder: (context, ref, _) {
    final checks = ref.watch(onOffToggleProvider);

    return Row(
      children: [
        InkWell(
          onTap: () {
            tabButton(true);
            ref.read(onOffToggleStateProvider.notifier).toggle(0);
          },
          child: Container(
            height: 44,
            width: 91,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: checks[0] ? MomoColor.main : MomoColor.unSelButton,
            ),
            child: Center(
              child: Text(
                '온라인',
                style: MomoTextStyle.small.copyWith(
                  color: checks[0] ? MomoColor.white : MomoColor.unSelText,
                ),
              ),
            ),
          ),
        ),
        const SizedBox(width: 16),
        InkWell(
          onTap: () {
            tabButton(false);
            ref.read(onOffToggleStateProvider.notifier).toggle(1);
          },
          child: Container(
            height: 44,
            width: 106,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: checks[1] ? MomoColor.main : MomoColor.unSelButton,
            ),
            child: Center(
              child: Text(
                '오프라인',
                style: MomoTextStyle.small.copyWith(
                  color: checks[1] ? MomoColor.white : MomoColor.unSelText,
                ),
              ),
            ),
          ),
        ),
      ],
    );
  });
}
