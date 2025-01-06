package com.bincher.game_hosting_service.dto.response.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

public class PostPaymentResponseDto extends ResponseDto{
    private PostPaymentResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostPaymentResponseDto> success(){
        PostPaymentResponseDto result = new PostPaymentResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
