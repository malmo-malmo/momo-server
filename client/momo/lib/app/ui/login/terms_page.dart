import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/login/terms_check_provider.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
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
              const EdgeInsets.only(top: 91, left: 16, right: 16, bottom: 24),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Text(
                '약관동의  1/3',
                style: TextStyle(
                  fontSize: 28.sp,
                  color: MomoColor.black,
                ),
              ),
              Text(
                '간단한 약관동의 후 편리한 모임 서비스를\n이용하실 수 있어요 :)',
                style: TextStyle(
                  fontSize: 14.sp,
                  color: MomoColor.black,
                ),
              ),
              _termsRow('개인정보 수집 제공 동의 (필수)', 0, termsCheck[0]),
              _termsRow('제 3자 정보제공 동의 (필수)', 1, termsCheck[1]),
              _termsRow('이벤트 수신 동의 (선택)', 2, termsCheck[2]),
              _termsRow('모두 확인 및 동의합니다', 3, termsAllCheck),
              SizedBox(
                height: 57,
                width: 317.w,
                child: ElevatedButton(
                  style: ButtonStyle(
                    shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                      RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(32),
                      ),
                    ),
                    backgroundColor: MaterialStateProperty.resolveWith(
                      (states) {
                        if (states.contains(MaterialState.disabled)) {
                          return const Color(0xfff2f2f2);
                        }
                        return const Color(0xff000000);
                      },
                    ),
                  ),
                  onPressed: isAgree
                      ? () {
                          ref
                              .read(navigatorProvider)
                              .navigateTo(routeName: AppRoutes.category);
                        }
                      : null,
                  child: Text(
                    '모두 동의하고 계속',
                    style: TextStyle(
                      fontSize: 16.sp,
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _termsRow(
    String title,
    int index,
    bool check,
  ) {
    return Consumer(builder: (context, ref, _) {
      return Row(
        children: [
          InkWell(
            onTap: () {
              if (index == 3) {
                ref.read(termsAllCheckStateProvider.state).state =
                    !ref.read(termsAllCheckStateProvider.state).state;
                ref
                    .read(termsCheckStateProvider.notifier)
                    .checkAll(ref.read(termsAllCheckStateProvider.state).state);
              } else {
                ref.read(termsCheckStateProvider.notifier).toggleTerms(index);
              }
            },
            child: CircleAvatar(
              radius: 15,
              backgroundColor: check ? MomoColor.black : MomoColor.unSelIcon,
            ),
          ),
          const SizedBox(width: 16),
          Text(title),
        ],
      );
    });
  }
}
