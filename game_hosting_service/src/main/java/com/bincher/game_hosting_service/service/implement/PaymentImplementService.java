package com.bincher.game_hosting_service.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bincher.game_hosting_service.dto.request.payment.PostPaymentRequestDto;
import com.bincher.game_hosting_service.dto.response.ResponseDto;
import com.bincher.game_hosting_service.dto.response.payment.GetPaymentResponseDto;
import com.bincher.game_hosting_service.dto.response.payment.PostPaymentResponseDto;
import com.bincher.game_hosting_service.dto.response.user.GetUserResponseDto;
import com.bincher.game_hosting_service.entity.PaymentEntity;
import com.bincher.game_hosting_service.repository.PaymentRepository;
import com.bincher.game_hosting_service.service.PaymentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentImplementService implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<? super PostPaymentResponseDto> postPayment(PostPaymentRequestDto dto) {
        try {
            PaymentEntity paymentEntity = new PaymentEntity(dto);
            paymentRepository.save(paymentEntity);
        } catch (EntityNotFoundException e) {
            return ResponseDto.notExistedGameServer();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        } 

        return PostPaymentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetPaymentResponseDto> getPayment(int serverId) {

        PaymentEntity paymentEntity = null;

        try {

            paymentEntity = paymentRepository.findByServerId(serverId);
            if(paymentEntity == null) return GetUserResponseDto.notExistedGameServer();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPaymentResponseDto.success(paymentEntity);
    }
    

}
