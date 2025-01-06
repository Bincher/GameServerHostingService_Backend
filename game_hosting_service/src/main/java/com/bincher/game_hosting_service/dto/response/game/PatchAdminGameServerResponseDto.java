package com.bincher.game_hosting_service.dto.response.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

public class PatchAdminGameServerResponseDto extends ResponseDto{
    
    private PatchAdminGameServerResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    
    public static ResponseEntity<PatchAdminGameServerResponseDto> success(){
        PatchAdminGameServerResponseDto result = new PatchAdminGameServerResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    public static ResponseEntity<ResponseDto> noExistGameServer(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_GAME_SERVER, ResponseMessage.NOT_EXISTED_GAME_SERVER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noPermission(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
