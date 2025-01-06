package com.bincher.game_hosting_service.service;

import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.dto.request.payment.PostPaymentRequestDto;
import com.bincher.game_hosting_service.dto.response.payment.GetPaymentResponseDto;
import com.bincher.game_hosting_service.dto.response.payment.PostPaymentResponseDto;

public interface PaymentService {
    ResponseEntity<? super PostPaymentResponseDto> postPayment(PostPaymentRequestDto dto);
    ResponseEntity<? super GetPaymentResponseDto> getPayment(int serverId);
}
