package com.bincher.game_hosting_service.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

public class PatchPasswordResponseDto extends ResponseDto{
    
    private PatchPasswordResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchPasswordResponseDto> success(){
        PatchPasswordResponseDto result = new PatchPasswordResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
