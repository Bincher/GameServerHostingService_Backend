package com.bincher.game_hosting_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bincher.game_hosting_service.dto.request.payment.PostPaymentRequestDto;
import com.bincher.game_hosting_service.dto.response.payment.GetPaymentResponseDto;
import com.bincher.game_hosting_service.dto.response.payment.PostPaymentResponseDto;
import com.bincher.game_hosting_service.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<? super PostPaymentResponseDto> postPayment(
        @RequestBody @Valid PostPaymentRequestDto requestBody
    ){
        ResponseEntity<? super PostPaymentResponseDto> response = paymentService.postPayment(requestBody);
        return response;
    }

    @GetMapping("/{serverId}")
    public ResponseEntity<? super GetPaymentResponseDto> getPayment(
        @PathVariable("serverId") int serverId
    ){
        ResponseEntity<? super GetPaymentResponseDto> response = paymentService.getPayment(serverId);
        return response;
    }

}
