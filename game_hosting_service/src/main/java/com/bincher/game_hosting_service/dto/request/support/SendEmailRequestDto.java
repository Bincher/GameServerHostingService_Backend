package com.bincher.game_hosting_service.dto.request.support;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendEmailRequestDto {
    
    @NotBlank
    private String id;

    @Email
    @NotBlank
    private String email;

    private String serverInformation;

    @NotBlank
    private String content;
}
