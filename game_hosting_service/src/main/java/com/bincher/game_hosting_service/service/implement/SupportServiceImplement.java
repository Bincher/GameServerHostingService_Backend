package com.bincher.game_hosting_service.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bincher.game_hosting_service.dto.request.support.SendEmailRequestDto;
import com.bincher.game_hosting_service.dto.response.support.SendEmailResponseDto;
import com.bincher.game_hosting_service.provider.EmailProvider;
import com.bincher.game_hosting_service.service.SupportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportServiceImplement implements SupportService{

    private final EmailProvider emailProvider;
    
    @Override
    public ResponseEntity<? super SendEmailResponseDto> sendEmail(SendEmailRequestDto dto) {
        String userId = dto.getId();
        String email = dto.getEmail();
        String serverInformation = "";
        if(dto.getServerInformation() != "") serverInformation = dto.getServerInformation();
        String content = dto.getContent();

        boolean isSuccessed = emailProvider.sendSupportEmail(userId, email, serverInformation, content);
        if(!isSuccessed) return SendEmailResponseDto.mailSendFail();


        return SendEmailResponseDto.success();
    }


    
}
