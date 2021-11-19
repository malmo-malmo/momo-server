import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/post/post_request_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/post_request/widget/img_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class PostPage extends ConsumerWidget {
  const PostPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final postRequest = ref.watch(postRequestProvider);
    final check = ref.watch(postRequestCheckProvider);

    return SafeArea(
      child: Scaffold(
          body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  InkWell(
                    onTap: () {
                      ref.read(navigatorProvider).pop();
                    },
                    child: const Icon(
                      CupertinoIcons.back,
                      size: 30,
                    ),
                  ),
                  const SizedBox(width: 8),
                  Text(
                    '게시물 작성',
                    style: TextStyle(
                      fontSize: 16.sp,
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 24),
              Container(
                padding:
                    const EdgeInsets.symmetric(horizontal: 24, vertical: 8),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(22),
                  color: Colors.grey,
                ),
                height: 44,
                width: double.infinity,
                child: TextField(
                  onChanged: (text) {
                    ref.read(postRequestStateProvider.notifier).setTitle(text);
                  },
                ),
              ),
              const SizedBox(height: 24),
              SizedBox(
                height: 400,
                child: TextField(
                  maxLines: 20,
                  decoration: InputDecoration(
                    hintText: '수정 할 내용을 입력해주세요!',
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(25.0),
                    ),
                  ),
                  onChanged: (text) {
                    ref
                        .read(postRequestStateProvider.notifier)
                        .setContents(text);
                  },
                ),
              ),
              Wrap(
                spacing: 8,
                runSpacing: 8,
                children: [
                  InkWell(
                    onTap: () async {
                      final imgPath = await ref
                          .read(navigatorProvider)
                          .navigateTo(routeName: AppRoutes.gallery);
                      ref
                          .read(postRequestStateProvider.notifier)
                          .setImage(imgPath);
                    },
                    child: const Icon(
                      CupertinoIcons.camera,
                      size: 40,
                    ),
                  ),
                  imgCard(img: postRequest.img),
                ],
              ),
              const SizedBox(height: 150),
              SizedBox(
                height: 57,
                width: double.infinity,
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
                  onPressed: check
                      ? () {
                          ref.read(navigatorProvider).pop();
                        }
                      : null,
                  child: Text(
                    '등록하기',
                    style: TextStyle(
                      fontSize: 16.sp,
                    ),
                  ),
                ),
              )
            ],
          ),
        ),
      )),
    );
  }
}
