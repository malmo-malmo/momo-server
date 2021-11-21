import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/search/search_filter_provider.dart';
import 'package:momo/app/util/theme.dart';

Widget locationCard({
  required String title,
  required bool check,
  required int index,
}) {
  return Consumer(builder: (context, ref, _) {
    return Material(
      elevation: 3,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: InkWell(
        onTap: () {
          ref.read(searchLocationStateProvider.notifier).toggleCategory(index);
        },
        child: Container(
          height: 41,
          width: title.length * 7 + 70,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(20),
            color: check ? MomoColor.main : MomoColor.white,
          ),
          child: Center(
            child: Text(
              title,
              style: MomoTextStyle.defaultStyle.copyWith(
                color: check ? MomoColor.white : MomoColor.black,
              ),
            ),
          ),
        ),
      ),
    );
  });
}
