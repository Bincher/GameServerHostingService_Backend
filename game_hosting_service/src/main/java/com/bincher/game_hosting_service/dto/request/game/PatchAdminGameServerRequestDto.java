package com.bincher.game_hosting_service.dto.request.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchAdminGameServerRequestDto {
    
    @NotNull
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String billingAmount;

    private String requestDetails;

    @NotNull
    private int modeCount;

    @NotBlank
    private String status;

    @NotBlank
    private String serverAddress;
}
