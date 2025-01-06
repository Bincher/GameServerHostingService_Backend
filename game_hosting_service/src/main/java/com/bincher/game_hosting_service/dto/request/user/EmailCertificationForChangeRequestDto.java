package com.bincher.game_hosting_service.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailCertificationForChangeRequestDto {
    @Email
    @NotBlank
    private String email;
}
