import 'package:flutter/material.dart';

class ScheduleDetailPage extends StatelessWidget {
  const ScheduleDetailPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Center(
          child: Text('일정 상세 페이지'),
        ),
      ),
    );
  }
}
