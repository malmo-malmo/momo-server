import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/dialog/university_result_dialog.dart';

class UniversityInputBox extends StatefulWidget {
  const UniversityInputBox({
    Key? key,
    required this.setUniversity,
    this.backgroundColor,
  }) : super(key: key);

  final Function(String school) setUniversity;
  final Color? backgroundColor;

  @override
  State<UniversityInputBox> createState() => _UniversityInputBoxState();
}

class _UniversityInputBoxState extends State<UniversityInputBox> {
  final _controller = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(16),
        color: widget.backgroundColor ?? MomoColor.flutterWhite,
      ),
      height: 44,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(
            width: 200.w,
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 14),
              child: TextFormField(
                controller: _controller,
                decoration: const InputDecoration(
                  hintText: '학교를 입력해주세요',
                  border: InputBorder.none,
                ),
              ),
            ),
          ),
          InkWell(
            child: SvgPicture.asset('assets/icon/search/icon_search_28.svg'),
            onTap: () async {
              final university = await showDialog(
                context: context,
                builder: (context) => UniversityResultDialog(
                  universityName: _controller.text,
                ),
              );
              _controller.text = university;
              widget.setUniversity(university);
              FocusScope.of(context).unfocus();
            },
          ),
        ],
      ),
    );
  }
}
