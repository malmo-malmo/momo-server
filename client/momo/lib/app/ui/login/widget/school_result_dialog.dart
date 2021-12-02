import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/school_result_provider.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget schoolResultDialog({
  required Function(String school) onSelect,
  required String universityName,
}) {
  return Consumer(
    builder: (context, ref, _) {
      final schoolResult = ref.watch(universityResultProvider(universityName));

      return Dialog(
        insetPadding: const EdgeInsets.all(1),
        child: Container(
          padding: const EdgeInsets.all(24),
          height: 300,
          width: 250,
          child: Column(
            children: [
              Text(
                '대학교를 선택하세요',
                style: MomoTextStyle.subTitle,
              ),
              const SizedBox(height: 24),
              Expanded(
                child: schoolResult.when(
                  loading: () => loadingCard(),
                  error: (error, stack) =>
                      Center(child: Text(error.toString())),
                  data: (data) => ListView.separated(
                    itemBuilder: (context, index) {
                      return InkWell(
                        onTap: () {
                          onSelect(data[index]);
                          ref.read(navigatorProvider).pop();
                        },
                        child: SizedBox(
                          height: 30,
                          child: Text(
                            data[index],
                            style: MomoTextStyle.defaultStyle,
                          ),
                        ),
                      );
                    },
                    itemCount: data.length,
                    separatorBuilder: (context, index) =>
                        const SizedBox(height: 16),
                  ),
                ),
              ),
            ],
          ),
        ),
      );
    },
  );
}
