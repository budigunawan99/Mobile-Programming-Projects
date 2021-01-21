import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Demo'),
          backgroundColor: Colors.blueGrey[800],
        ),
        backgroundColor: Colors.white,
        body: Center(
          child: Image(
            image: AssetImage('images/bromo-01-small.jpg'),
          ),
        ),
      ),
    ),
  );
}
