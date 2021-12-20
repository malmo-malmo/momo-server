import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

class LoadingCard extends StatelessWidget {
  const LoadingCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: SizedBox(
        height: 36,
        width: 36,
        child: Center(
          child: CircularProgressIndicator(
            color: MomoColor.main,
            strokeWidth: 2,
          ),
        ),
      ),
    );
  }
}
