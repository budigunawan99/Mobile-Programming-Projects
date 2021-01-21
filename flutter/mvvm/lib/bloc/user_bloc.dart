import 'package:bloc/bloc.dart';
import 'package:mvvm/model/user.dart';
import 'package:mvvm/services/user_service.dart';

class UserBloc extends Bloc<int, User> {
  @override
  User get initialState => UninitializedUser();

  @override
  Stream<User> mapEventToState(int id) async* {
    try {
      User user = await UserService.connectToAPI(id.toString());
      yield user;
    } catch (_) {}
  }
}
