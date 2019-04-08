import './connectServer.dart';

import './../Constants/queries.dart' as queries;

class Model {

  static Model _instance;

  static Model get instance {
    if (_instance == null) {
      _instance = new Model();
    }
    return _instance;
  }

  Future<bool> updateAvatar(String username, String image) {
    return MySqlConnection.instance.executeNoneQuery(
      queries.UPDATE_ACC_AVATAR,
      parameter: [username, image]
    );
  }

  Future<bool> updateInfo(String username, String displayName, int sex, DateTime birthday, String idCard, String address, String phone) {
    return MySqlConnection.instance.executeNoneQuery(
      queries.UPDATE_ACC_INFO,
      parameter: [username, displayName, sex, birthday, idCard, address, phone]
    );
  }

  Future<bool> updatePassword(String username, String newPass) {
    return MySqlConnection.instance.executeNoneQuery(
      queries.UPDATE_ACC_PASS,
      parameter: [username, newPass]
    );
  }
  
}