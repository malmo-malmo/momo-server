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
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        padding: const EdgeInsets.only(top: 32),
        height: 328,
        width: 280,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
        ),
        child: universityResult.when(
          loading: () => const LoadingCard(),
          error: (error, stack) => Center(child: Text(error.toString())),
          data: (data) {
            final _checks = ref.watch(universityCheckProvider(data.length));

            return data.isEmpty
                ? const Center(child: Text('검색한 대학교가 존재하지 않습니다!'))
                : Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 32),
                        child: Text('검색결과(${data.length})',
                            style: MomoTextStyle.subTitle),
                      ),
                      const SizedBox(height: 13),
                      SizedBox(
                        height: 192,
                        child: Scrollbar(
                          isAlwaysShown: true,
                          child: ListView.builder(
                            itemBuilder: (context, index) {
                              return InkWell(
                                onTap: () {
                                  ref
                                      .read(universityCheckProvider(data.length)
                                          .notifier)
                                      .checkOne(index);
                                },
                                child: Container(
                                  height: 48,
                                  padding: const EdgeInsets.only(
                                      left: 32, top: 15, bottom: 15),
                                  color: _checks[index]
                                      ? MomoColor.main
                                      : MomoColor.flutterWhite,
                                  child: Text(
                                    data[index],
                                    style: MomoTextStyle.defaultStyleR.copyWith(
                                      color: _checks[index]
                                          ? MomoColor.white
                                          : null,
                                    ),
                                  ),
                                ),
                              );
                            },
                            itemCount: data.length,
                          ),
                        ),
                      ),
                      const Spacer(),
                      InkWell(
                        onTap: _checks.indexWhere((element) => element) != -1
                            ? () {
                                ref.read(navigatorProvider).pop(
                                    result: data[_checks
                                        .indexWhere((element) => element)]);
                              }
                            : null,
                        child: Container(
                          decoration: BoxDecoration(
                            borderRadius: const BorderRadius.only(
                              bottomLeft: Radius.circular(20),
                              bottomRight: Radius.circular(20),
                            ),
                            color:
                                _checks.indexWhere((element) => element) != -1
                                    ? MomoColor.main
                                    : MomoColor.unSelButton,
                          ),
                          height: 56,
                          width: double.infinity,
                          child: Center(
                            child: Text(
                              '확인',
                              style: MomoTextStyle.defaultStyleR.copyWith(
                                color:
                                    _checks.indexWhere((element) => element) !=
                                            -1
                                        ? MomoColor.white
                                        : MomoColor.unSelText,
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  );
          },
        ),
      ),
    );
  }
}
