package com.bincher.game_hosting_service.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteUserResponseDto extends ResponseDto{
    
    private DeleteUserResponseDto(){
        super();
    }

    public static ResponseEntity<DeleteUserResponseDto> success(){
        DeleteUserResponseDto responseBody = new DeleteUserResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noPermission(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    public static ResponseEntity<ResponseDto> serverExisted(){
        ResponseDto result = new ResponseDto(ResponseCode.SERVER_EXIST, ResponseMessage.SERVER_EXIST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
