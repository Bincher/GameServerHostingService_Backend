package com.bincher.game_hosting_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bincher.game_hosting_service.dto.request.support.SendEmailRequestDto;
import com.bincher.game_hosting_service.dto.response.support.SendEmailResponseDto;
import com.bincher.game_hosting_service.service.SupportService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/support")
@RequiredArgsConstructor
public class SupportController {

    private final SupportService supportService;

    @PostMapping("/email")
    public ResponseEntity<? super SendEmailResponseDto> sendSupportEmail(
        @RequestBody @Valid SendEmailRequestDto requestBody, @AuthenticationPrincipal String userId
        ) {
            ResponseEntity<? super SendEmailResponseDto> response = supportService.sendEmail(requestBody);
            return response;
    }

}
