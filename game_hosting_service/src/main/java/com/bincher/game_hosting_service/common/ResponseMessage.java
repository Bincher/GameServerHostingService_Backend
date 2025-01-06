package com.bincher.game_hosting_service.common;

public interface ResponseMessage {
    // HTTP Status 200
    String SUCCESS = "Success";
    
    // HTTP Status 400
    String VALIDATION_FAILED = "Validation failed";
    String DUPLICATE_EMAIL = "Duplicate email";
    String DUPLICATE_NICKNAME = "Duplicate nickname";
    String DUPLICATE_TEL_NUMBER = "Duplicate tel number";
    String DUPLICATE_ID = "Duplicate Id";
    String NOT_EXISTED_USER = "This user does not exist";
    String NOT_EXISTED_BOARD = "This board does not exist";
    String NOT_EXISTED_GAME_SERVER = "This game server does not exist";
    String NOT_ADMIN = "Only Admin can post new game";
    String NOT_EXISTED_GAME = "This game does not exist";
    String NOT_EXISTED_CHAT = "This chat does not exist";
    String SERVER_EXIST = "Game servers must not exist";
  
    // HTTP Status 401
    String SIGN_IN_FAIL = "Login information mismatch";
    String AUTHORIZATION_FAIL = "Authorization fail";
    String CERTIFICATION_FAIL = "Certification failed";
    String PASSWORD_FAIL = "Password failed";
  
    // HTTP Status 403
    String NO_PERMISSION = "Do not have permission";
  
    // HTTP Status 500
    String MAIL_FAIL = "Mail send failed.";
    String NOTIFICATION_FAIL = "Notification send Failed";
    String DATABASE_ERROR = "Database error";
}