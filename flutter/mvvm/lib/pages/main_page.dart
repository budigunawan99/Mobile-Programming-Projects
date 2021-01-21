import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:mvvm/bloc/user_bloc.dart';
import 'package:mvvm/model/user.dart';
import 'package:mvvm/ui/user_card.dart';

class MainPage extends StatelessWidget {
  // final User user;
  // MainPage(this.user);
  final Random number = Random();

  @override
  Widget build(BuildContext context) {
    UserBloc userBloc = BlocProvider.of<UserBloc>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text("MVVM Project"),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          RaisedButton(
            onPressed: () {
              userBloc.dispatch(number.nextInt(50) + 1);
            },
            child: Text("Pick User"),
          ),
          BlocBuilder<UserBloc, User>(
              builder: (context, currentUser) =>
                  (currentUser is UninitializedUser)
                      ? Container()
                      : UserCard(user: currentUser)),
        ],
      ),
    );
  }
}
