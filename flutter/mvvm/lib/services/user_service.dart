import 'dart:convert';
import 'package:http/http.dart' as http;

import 'package:mvvm/model/user.dart';

class UserService extends User{
  static Future<User> connectToAPI(String id) async {
    String url = "https://reqres.in/api/users/" + id;

    var apiResult = await http.get(url);
    var jsonObject = json.decode(apiResult.body);
    return User.createGetInstance(jsonObject['data']);
  }
}
