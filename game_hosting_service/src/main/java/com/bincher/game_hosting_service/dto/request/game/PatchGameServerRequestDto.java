package com.bincher.game_hosting_service.dto.request.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchGameServerRequestDto {
    
    @NotNull
    private int id;

    @NotBlank
    private String name;

    private String content;

    @NotBlank
    private String location;

    @NotBlank
    private String performance;

    @NotBlank
    private String disk;

    private boolean backup;

    @NotBlank
    private String billingAmount;

    private String requestDetails;

    @NotNull
    private int modeCount;

    @NotNull
    private String gameTitle;
}
