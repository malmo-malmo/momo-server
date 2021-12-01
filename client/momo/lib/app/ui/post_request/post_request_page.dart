import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/post/post_request_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/components/input_box/content_input_box.dart';
import 'package:momo/app/ui/components/input_box/name_input_box.dart';
import 'package:momo/app/ui/post_request/widget/img_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class PostRequestPage extends ConsumerWidget {
  const PostRequestPage({
    Key? key,
    required this.title,
  }) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final postRequest = ref.watch(postRequestProvider);
    final check = ref.watch(postRequestCheckProvider);

    return SafeArea(
      child: Scaffold(
        body: Padding(
          padding: const EdgeInsets.only(right: 16, left: 16, top: 18),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Expanded(
                child: CustomScrollView(
                  slivers: [
                    SliverToBoxAdapter(
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Row(
                            children: [
                              InkWell(
                                onTap: () {
                                  ref.read(navigatorProvider).pop();
                                },
                                child: Icon(
                                  CupertinoIcons.xmark,
                                  size: 28.w,
                                ),
                              ),
                              const SizedBox(width: 8),
                              Text(
                                '$title 작성',
                                style: MomoTextStyle.defaultStyle,
                              ),
                            ],
                          ),
                          InkWell(
                            onTap: () {
                              ref.read(navigatorProvider).pop();
                            },
                            child: Container(
                              padding: const EdgeInsets.symmetric(vertical: 8),
                              height: 29,
                              width: 58,
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(20),
                                color: check
                                    ? MomoColor.main
                                    : const Color(0xfff0f0f0),
                              ),
                              child: Center(
                                  child: Text(
                                '완료',
                                style: MomoTextStyle.small.copyWith(
                                  color: check
                                      ? MomoColor.white
                                      : MomoColor.unSelIcon,
                                ),
                              )),
                            ),
                          ),
                        ],
                      ),
                    ),
                    const SliverToBoxAdapter(child: SizedBox(height: 24)),
                    SliverToBoxAdapter(
                      child: nameInputBox(
                        onTextChanged: ref
                            .read(postRequestStateProvider.notifier)
                            .setTitle,
                        hintText: '제목',
                      ),
                    ),
                    const SliverToBoxAdapter(child: SizedBox(height: 24)),
                    SliverToBoxAdapter(
                      child: contentInputBox(
                        onTextChanged: ref
                            .read(postRequestStateProvider.notifier)
                            .setContents,
                        maxLines: 20,
                        height: 400,
                        hintText: '내용을 입력하세요!',
                      ),
                    ),
                    const SliverToBoxAdapter(child: SizedBox(height: 24)),
                    SliverToBoxAdapter(
                      child: Wrap(
                        spacing: 8,
                        runSpacing: 8,
                        children: [
                          imgCard(img: postRequest.imageUrls.first),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              FloatingCameraBotton(
                // selectImg: ref.read(postRequestStateProvider.notifier).setImage,
                selectImg: (text) {},
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class FloatingCameraBotton extends StatelessWidget {
  const FloatingCameraBotton({
    Key? key,
    required this.selectImg,
  }) : super(key: key);

  final Function(String img) selectImg;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      bottom: true,
      top: false,
      child: Consumer(builder: (context, ref, _) {
        return Container(
          color: const Color(0xffffffff),
          height: 56,
          child: InkWell(
            onTap: () async {
              final imgPath = await ref
                  .read(navigatorProvider)
                  .navigateTo(routeName: AppRoutes.gallery);
              selectImg(imgPath);
            },
            child: SvgPicture.asset(
              'assets/icon/btn_camera_32.svg',
            ),
          ),
        );
      }),
    );
  }
}
