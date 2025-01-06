package com.bincher.game_hosting_service.dto.request.payment;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPaymentRequestDto {

    @NotNull
    private int serverId;

    @NotBlank
    private String amount;
}
