package com.bincher.game_hosting_service.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchEmailRequestDto {
    private String email;
}
