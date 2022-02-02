import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/model/enum/photo_request_type.dart';
import 'package:momo/app/model/enum/post_type.dart';
import 'package:momo/app/provider/gallery/permission_provider.dart';
import 'package:momo/app/provider/post/post_request_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/routes/custom_arg/post_request_arg.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/confirm_action_icon.dart';
import 'package:momo/app/ui/components/input_box/content_input_box.dart';
import 'package:momo/app/ui/post_request/widget/img_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class PostRequestPage extends ConsumerWidget {
  const PostRequestPage({
    Key? key,
    required this.postRequestArg,
  }) : super(key: key);

  final PostRequestArg postRequestArg;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final postRequest = ref.watch(postRequestStateProvider(postRequestArg));

    final check = ref.watch(postRequestCheckProvider(postRequestArg));

    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xffffffff),
        appBar: CustomAppBar(
          leadingIcon: CupertinoIcons.xmark,
          title: '${postRequestArg.postType.postTypeToName} 작성',
          isAction: true,
          actionWidget: ConfirmActionIcon(
            check: check,
            title: '완료',
            onTapIcon: () async {
              final result = await ref
                  .read(postRequestStateProvider(postRequestArg).notifier)
                  .createPost();
              ref.read(navigatorProvider).pop(result: result);
            },
            isShowDialog: true,
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.only(right: 16, left: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Expanded(
                child: CustomScrollView(
                  slivers: [
                    const SliverToBoxAdapter(child: SizedBox(height: 24)),
                    SliverToBoxAdapter(
                      child: TextInputBox(
                        onTextChanged: ref
                            .read(postRequestStateProvider(postRequestArg)
                                .notifier)
                            .setTitle,
                        hintText: '제목',
                        height: 44,
                      ),
                    ),
                    const SliverToBoxAdapter(child: SizedBox(height: 14)),
                    SliverToBoxAdapter(
                      child: TextInputBox(
                        onTextChanged: ref
                            .read(postRequestStateProvider(postRequestArg)
                                .notifier)
                            .setContents,
                        maxLines: 24,
                        height: 400,
                        hintText: '내용을 작성해주세요',
                      ),
                    ),
                    const SliverToBoxAdapter(child: SizedBox(height: 24)),
                    SliverToBoxAdapter(
                      child: postRequest.images.isEmpty
                          ? const SizedBox(
                              child: Center(
                                child: Text('No Image'),
                              ),
                            )
                          : Wrap(
                              spacing: 8,
                              runSpacing: 8,
                              children: List.generate(
                                postRequest.images.length,
                                (index) => imgCard(
                                  img: postRequest.images[index],
                                  deleteImg: ref
                                      .read(postRequestStateProvider(
                                              postRequestArg)
                                          .notifier)
                                      .deleteImg,
                                ),
                              ),
                            ),
                    ),
                  ],
                ),
              ),
              _FloatingCameraBotton(
                addImages: ref
                    .read(postRequestStateProvider(postRequestArg).notifier)
                    .setImages,
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _FloatingCameraBotton extends StatelessWidget {
  const _FloatingCameraBotton({Key? key, required this.addImages})
      : super(key: key);

  final Function(List<String> images) addImages;

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
              FocusScope.of(context).unfocus();
              final check = await ref.read(photoPermissionProvider.future);
              if (check) {
                List<String>? images =
                    await ref.read(navigatorProvider).navigateTo(
                          routeName: AppRoutes.gallery,
                          arguments: PhotoRequestType.many,
                        );
                if (images != null) {
                  addImages(images);
                }
              }
            },
            child: SvgPicture.asset('assets/icon/btn_camera_32.svg'),
          ),
        );
      }),
    );
  }
}
