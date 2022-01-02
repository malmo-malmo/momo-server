import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/school_result_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class UniversityResultDialog extends StatelessWidget {
  const UniversityResultDialog({
    Key? key,
    required this.universityName,
  }) : super(key: key);

  final String universityName;

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
              final universityResult =
                  ref.watch(universityResultProvider(universityName));
              return Expanded(
                child: universityResult.when(
                  loading: () => const LoadingCard(),
                  error: (error, stack) =>
                      Center(child: Text(error.toString())),
                  data: (data) => data.isEmpty
                      ? const Center(child: Text('검색한 대학교가 존재하지 않습니다!'))
                      : ListView.separated(
                          itemBuilder: (context, index) {
                            return InkWell(
                              onTap: () {
                                ref
                                    .read(navigatorProvider)
                                    .pop(result: data[index]);
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
