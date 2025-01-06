package com.bincher.game_hosting_service.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private String code;
    private String message;

    public ResponseDto(){
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }

    public static ResponseEntity<ResponseDto> databaseError(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> validationFailed(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAILED, ResponseMessage.VALIDATION_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedGame(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_GAME, ResponseMessage.NOT_EXISTED_GAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedGameServer(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_GAME_SERVER, ResponseMessage.NOT_EXISTED_GAME_SERVER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedChat(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CHAT, ResponseMessage.NOT_EXISTED_CHAT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
