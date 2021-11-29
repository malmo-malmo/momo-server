import 'package:flutter/material.dart';

class AttendanceListPage extends StatelessWidget {
  const AttendanceListPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const SafeArea(
      child: Scaffold(
          body: Center(
        child: Text('출석체크화면'),
      )),
    );
  }
}
