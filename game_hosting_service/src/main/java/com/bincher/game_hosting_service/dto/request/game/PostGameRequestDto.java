package com.bincher.game_hosting_service.dto.request.game;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostGameRequestDto {
    
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String gameImage;

    @NotBlank
    private String amountLevel;
}
