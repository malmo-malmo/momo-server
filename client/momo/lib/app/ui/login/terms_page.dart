import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/terms_check_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/login/widget/terms_all_check_box.dart';
import 'package:momo/app/ui/login/widget/terms_row.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/navigation_service.dart';

class TermsPage extends ConsumerWidget {
  const TermsPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final isAgree = ref.watch(isAgreeTermsProvider);
    final termsCheck = ref.watch(termsCheckProvider);
    final termsAllCheck = ref.watch(termsAllCheckProvider);

    return SafeArea(
      child: Scaffold(
        body: Padding(
          padding:
              const EdgeInsets.only(top: 91, bottom: 36, right: 20, left: 20),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  titleText('약관동의  1/3'),
                  const SizedBox(height: 20),
                  subTitleText('간단한 약관동의 후 편리한 모임 서비스를\n이용하실 수 있어요 :)'),
                  const SizedBox(height: 44),
                  TermsAllCheckBox(
                    check: termsAllCheck,
                    onCheck: () {
                      ref.read(termsAllCheckStateProvider.state).state =
                          !ref.read(termsAllCheckStateProvider.state).state;
                      ref
                          .read(termsCheckProvider.notifier)
                          .checkAll(ref.read(termsAllCheckStateProvider));
                    },
                  ),
                  const SizedBox(height: 32),
                  TermsRow(
                    check: termsCheck[0],
                    onCheck: () {
                      ref.read(termsCheckProvider.notifier).toggleTerms(0);
                      final _checks =
                          ref.read(termsCheckProvider.notifier).isCheckAll();
                      if (_checks) {
                        ref.read(termsAllCheckStateProvider.state).state = true;
                      } else {
                        ref.read(termsAllCheckStateProvider.state).state =
                            false;
                      }
                    },
                    title: '개인정보 수집 제공 동의 (필수)',
                  ),
                  const SizedBox(height: 14),
                  TermsRow(
                    check: termsCheck[1],
                    onCheck: () {
                      ref.read(termsCheckProvider.notifier).toggleTerms(1);
                      final _checks =
                          ref.read(termsCheckProvider.notifier).isCheckAll();
                      if (_checks) {
                        ref.read(termsAllCheckStateProvider.state).state = true;
                      } else {
                        ref.read(termsAllCheckStateProvider.state).state =
                            false;
                      }
                    },
                    title: '제 3자 정보제공 동의 (필수)',
                  ),
                  const SizedBox(height: 14),
                  TermsRow(
                    check: termsCheck[2],
                    onCheck: () {
                      ref.read(termsCheckProvider.notifier).toggleTerms(2);
                      final _checks =
                          ref.read(termsCheckProvider.notifier).isCheckAll();
                      if (_checks) {
                        ref.read(termsAllCheckStateProvider.state).state = true;
                      } else {
                        ref.read(termsAllCheckStateProvider.state).state =
                            false;
                      }
                    },
                    title: '이벤트 수신 동의 (선택)',
                  ),
                ],
              ),
              ConfirmButton(
                check: isAgree,
                onPressButton: () {
                  ref
                      .read(navigatorProvider)
                      .navigateTo(routeName: AppRoutes.category);
                },
                buttonText: '다음',
              ),
            ],
          ),
        ),
      ),
    );
  }
}
