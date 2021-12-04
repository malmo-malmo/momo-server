import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/district_result_provider.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget districtResultDialog({
  required Function(String university) onSelect,
  required String cityCode,
}) {
  return Consumer(
    builder: (context, ref, _) {
      final districtList = ref.watch(districtResultProvider(cityCode));

      return Dialog(
        insetPadding: const EdgeInsets.all(1),
        child: Container(
          padding: const EdgeInsets.all(24),
          height: 300,
          width: 250,
          child: Column(
            children: [
              Text(
                '지역을 선택하세요',
                style: MomoTextStyle.subTitle,
              ),
              const SizedBox(height: 48),
              Expanded(
                child: districtList.when(
                  loading: () => loadingCard(),
                  error: (error, stack) =>
                      Center(child: Text(error.toString())),
                  data: (data) => data.isEmpty
                      ? const Center(
                          child: Text(
                            '다시 시도해주세요!',
                          ),
                        )
                      : ListView.separated(
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
