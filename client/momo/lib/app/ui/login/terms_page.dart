import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/login/terms_check_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/theme.dart';

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
              const EdgeInsets.only(top: 91, left: 24, right: 24, bottom: 24),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              titleText('약관동의  1/3'),
              subTitleText('간단한 약관동의 후 편리한 모임 서비스를\n이용하실 수 있어요 :)'),
              _termsAllCheckBox(termsAllCheck),
              _termsRow('개인정보 수집 제공 동의 (필수)', 0, termsCheck[0]),
              _termsRow('제 3자 정보제공 동의 (필수)', 1, termsCheck[1]),
              _termsRow('이벤트 수신 동의 (선택)', 2, termsCheck[2]),
              agreeButton(
                check: isAgree,
                nextPage: AppRoutes.category,
                text: '다음',
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _termsAllCheckBox(bool check) {
    return Consumer(builder: (context, ref, _) {
      return Container(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        height: 56,
        width: double.infinity,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: MomoColor.white,
        ),
        child: Row(
          children: [
            InkWell(
              onTap: () {
                ref.read(termsAllCheckStateProvider.state).state =
                    !ref.read(termsAllCheckStateProvider.state).state;
                ref
                    .read(termsCheckStateProvider.notifier)
                    .checkAll(ref.read(termsAllCheckStateProvider.state).state);
              },
              child: CircleAvatar(
                radius: 15,
                backgroundColor:
                    check ? MomoColor.main : const Color(0xff9e9e9e),
                child: const Icon(
                  Icons.check,
                  size: 20,
                  color: Color(0xfffdfdfd),
                ),
              ),
            ),
            const SizedBox(width: 16),
            Text(
              '전체 동의',
              style: MomoTextStyle.defaultStyle,
            ),
          ],
        ),
      );
    });
  }

  Widget _termsRow(
    String title,
    int index,
    bool check,
  ) {
    return Consumer(builder: (context, ref, _) {
      return Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        child: Row(
          children: [
            InkWell(
              onTap: () {
                ref.read(termsCheckStateProvider.notifier).toggleTerms(index);
              },
              child: CircleAvatar(
                radius: 15,
                backgroundColor:
                    check ? const Color(0xffbca9f7) : const Color(0xffdedede),
                child: const Icon(
                  Icons.check,
                  size: 20,
                  color: Color(0xfffdfdfd),
                ),
              ),
            ),
            const SizedBox(width: 16),
            Text(
              title,
              style: MomoTextStyle.normal,
            ),
          ],
        ),
      );
    });
  }
}
