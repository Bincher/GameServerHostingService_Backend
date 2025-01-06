package com.bincher.game_hosting_service.dto.response.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.common.ResponseCode;
import com.bincher.game_hosting_service.common.ResponseMessage;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.entity.PaymentEntity;

import lombok.Getter;

@Getter
public class GetPaymentResponseDto extends ResponseDto{

    private int paymentId;
    private int serverId;
    private String amount;
    private String paymentDate;
    private String status;

    private GetPaymentResponseDto(PaymentEntity paymentEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.paymentId = paymentEntity.getPaymentId();
        this.serverId = paymentEntity.getServerId();
        this.amount = paymentEntity.getAmount();
        this.paymentDate = paymentEntity.getPaymentDate();
        this.status = paymentEntity.getStatus();
    }
    
    public static ResponseEntity<GetPaymentResponseDto> success(PaymentEntity paymentEntity){
        GetPaymentResponseDto result = new GetPaymentResponseDto(paymentEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
