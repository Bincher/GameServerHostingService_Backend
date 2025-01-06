package com.bincher.game_hosting_service.dto.response.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

public class PatchPaymentStatusResponseDto extends ResponseDto{
    private PatchPaymentStatusResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    
    public static ResponseEntity<PatchPaymentStatusResponseDto> success(){
        PatchPaymentStatusResponseDto result = new PatchPaymentStatusResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistGameServer(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_GAME_SERVER, ResponseMessage.NOT_EXISTED_GAME_SERVER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
