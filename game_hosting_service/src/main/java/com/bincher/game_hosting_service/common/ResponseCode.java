package com.bincher.game_hosting_service.common;

public interface ResponseCode {

    // HTTP Status 200
    //public static final String SUCCESS = "SU";
    String SUCCESS = "SU";
  
    // HTTP Status 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TEL_NUMBER = "DT";
    String DUPLICATE_ID = "DI";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_GAME_SERVER = "NS";
    String NOT_ADMIN = "NA";
    String NOT_EXISTED_GAME = "NG";
    String NOT_EXISTED_CHAT = "NC";
    String SERVER_EXIST = "SE";
  
    // HTTP Status 401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";
    String CERTIFICATION_FAIL = "CF";
    String PASSWORD_FAIL = "PF";
  
    // HTTP Status 403
    String NO_PERMISSION = "NP";
  
    // HTTP Status 500
    String MAIL_FAIL = "MF";
    String NOTIFICATION_FAIL = "NF";
    String DATABASE_ERROR = "DBE";
  
}