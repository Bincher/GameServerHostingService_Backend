package com.bincher.game_hosting_service.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.bincher.game_hosting_service.dto.request.payment.PostPaymentRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="payment")
@Table(name="payment")
public class PaymentEntity {
    
    @Id
    @Column(name = "payment_id")
    private int paymentId;

    @NotNull
    private int serverId;

    @NotBlank
    private String amount;

    private String paymentDate;
    private String status;

    public PaymentEntity(PostPaymentRequestDto dto){
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String paymentDatetime = simpleDateFormat.format(now);
        
        this.serverId = dto.getServerId();
        this.amount = dto.getAmount();
        this.paymentDate = paymentDatetime;
        this.status = "결제 완료";
    }
}
