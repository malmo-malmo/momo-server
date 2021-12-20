import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/district_result_provider.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class DistrictResultDialog extends StatelessWidget {
  const DistrictResultDialog({
    Key? key,
    required this.onSelect,
    required this.cityCode,
  }) : super(key: key);

  final Function(String university) onSelect;
  final String cityCode;

  @override
  Widget build(BuildContext context) {
    return Dialog(
      insetPadding: const EdgeInsets.all(1),
      child: Container(
        padding: const EdgeInsets.all(24),
        height: 300,
        width: 250,
        child: Column(
          children: [
            const Text('지역을 선택하세요', style: MomoTextStyle.subTitle),
            const SizedBox(height: 48),
            Consumer(builder: (context, ref, _) {
              final districtList = ref.watch(districtResultProvider(cityCode));
              return Expanded(
                child: districtList.when(
                  loading: () => const LoadingCard(),
                  error: (error, stack) =>
                      Center(child: Text(error.toString())),
                  data: (data) => data.isEmpty
                      ? const Center(child: Text('다시 시도해주세요!'))
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
              );
            }),
          ],
        ),
      ),
    );
  }
}
