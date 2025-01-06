package com.bincher.game_hosting_service.service;

import org.springframework.http.ResponseEntity;

import com.bincher.game_hosting_service.dto.request.support.SendEmailRequestDto;
import com.bincher.game_hosting_service.dto.response.support.SendEmailResponseDto;

public interface SupportService {
    ResponseEntity<? super SendEmailResponseDto> sendEmail(SendEmailRequestDto dto);
}
