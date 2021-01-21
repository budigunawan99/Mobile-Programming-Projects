import 'package:ar_project/pages/main_page.dart';
import 'package:flutter/material.dart';
// import 'package:camera/camera.dart';

void main() async {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text("ARCore Test"),
        ),
        body: MainPage(),
      ),
    );
  }
}
