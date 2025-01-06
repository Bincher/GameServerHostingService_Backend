package com.bincher.game_hosting_service.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class EmailCertificationForChangeResponseDto extends ResponseDto{
    private EmailCertificationForChangeResponseDto(){
        super();
    }

    public static ResponseEntity<EmailCertificationForChangeResponseDto> success(){
        EmailCertificationForChangeResponseDto responseBody = new EmailCertificationForChangeResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> mailSendFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.MAIL_FAIL, ResponseMessage.MAIL_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
