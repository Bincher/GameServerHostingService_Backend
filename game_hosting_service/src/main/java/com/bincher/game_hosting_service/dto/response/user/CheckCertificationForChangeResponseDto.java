package com.bincher.game_hosting_service.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class CheckCertificationForChangeResponseDto extends ResponseDto{
    private CheckCertificationForChangeResponseDto(){
        super();
    }

    public static ResponseEntity<CheckCertificationForChangeResponseDto> success(){
        CheckCertificationForChangeResponseDto responseBody = new CheckCertificationForChangeResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> certificationFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
