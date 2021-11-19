import 'package:flutter/material.dart';

class ScheduleListPage extends StatelessWidget {
  const ScheduleListPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Center(
          child: Text(
            '일정 목록 페이지',
          ),
        ),
      ),
    );
  }
}
