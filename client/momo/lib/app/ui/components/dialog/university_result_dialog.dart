import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/school_result_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class UniversityResultDialog extends ConsumerWidget {
  const UniversityResultDialog({
    Key? key,
    required this.universityName,
  }) : super(key: key);

  final String universityName;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final universityResult =
        ref.watch(universityResultProvider(universityName));
    return Dialog(
      insetPadding: const EdgeInsets.all(0),
      child: Container(
        padding: const EdgeInsets.all(24),
        height: 272,
        width: 280,
        child: universityResult.when(
          loading: () => const LoadingCard(),
          error: (error, stack) => Center(child: Text(error.toString())),
          data: (data) => data.isEmpty
              ? const Center(
                  child: Text(
                    '검색한 대학교가 존재하지 않습니다!',
                  ),
                )
              : Column(
                  children: [
                    Text('검색결과(${data.length})', style: MomoTextStyle.subTitle),
                    const SizedBox(height: 13),
                    Expanded(
                      child: ListView.builder(
                        itemBuilder: (context, index) {
                          return InkWell(
                            onTap: () {
                              ref
                                  .read(navigatorProvider)
                                  .pop(result: data[index]);
                            },
                            child: Container(
                              height: 48,
                              color: MomoColor.main,
                              child: Text(
                                data[index],
                                style: MomoTextStyle.defaultStyleR,
                              ),
                            ),
                          );
                        },
                        itemCount: data.length,
                      ),
                    ),
                  ],
                ),
        ),
      ),
    );
  }
}
