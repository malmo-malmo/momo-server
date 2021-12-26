import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/school_result_provider.dart';
import 'package:momo/app/provider/user/university_controller_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class UniversityResultDialog extends StatelessWidget {
  const UniversityResultDialog({Key? key, required this.onSelect})
      : super(key: key);

  final Function(String school) onSelect;

  @override
  Widget build(BuildContext context) {
    return Dialog(
      insetPadding: const EdgeInsets.all(0),
      child: Container(
        padding: const EdgeInsets.all(24),
        height: 300,
        width: 250,
        child: Column(
          children: [
            const Text('대학교를 선택하세요', style: MomoTextStyle.subTitle),
            const SizedBox(height: 48),
            Consumer(builder: (context, ref, _) {
              final universityName = ref.watch(universityTextController).text;
              final universityResult =
                  ref.watch(universityResultProvider(universityName));
              return Expanded(
                child: universityResult.when(
                  loading: () => const LoadingCard(),
                  error: (error, stack) =>
                      Center(child: Text(error.toString())),
                  data: (data) => data.isEmpty
                      ? const Center(
                          child: Text(
                            '검색한 대학교가 존재하지 않습니다!',
                          ),
                        )
                      : ListView.separated(
                          itemBuilder: (context, index) {
                            return InkWell(
                              onTap: () {
                                onSelect(data[index]);
                                ref.read(universityTextController).text =
                                    data[index];
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
