package com.bincher.game_hosting_service.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    
    @NotBlank
    private String id;

    @NotBlank @Size(min=8, max=20)
    @Pattern(regexp = "^(?=.*[a-xA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,13}$")
    private String password;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String certificationNumber;
}
