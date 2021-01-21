import 'dart:convert';

class User {
  String id;
  String name;
  String email;
  String avatar;

  User({this.id, this.name, this.email, this.avatar});

  factory User.createGetInstance(Map<String, dynamic> object) {
    return User(
      id: object['id'].toString(),
      name: object['first_name'] + ' ' + object['last_name'],
      email: object['email'],
      avatar: object['avatar'],
    );
  }
}

class UninitializedUser extends User {}
