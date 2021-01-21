import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:mvvm/pages/main_page.dart';

import 'bloc/user_bloc.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: BlocProvider<UserBloc>(
        builder: (context) => UserBloc(),
        child: MainPage(),
      ),
    );
  }
}
