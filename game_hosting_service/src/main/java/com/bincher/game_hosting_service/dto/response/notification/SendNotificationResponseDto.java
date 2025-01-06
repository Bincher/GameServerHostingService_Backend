package com.bincher.game_hosting_service.dto.response.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendNotificationResponseDto extends ResponseDto{
    private SendNotificationResponseDto(){
        super();
    }

    public static ResponseEntity<SendNotificationResponseDto> success(){
        SendNotificationResponseDto responseBody = new SendNotificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notificationSendFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOTIFICATION_FAIL, ResponseMessage.NOTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
