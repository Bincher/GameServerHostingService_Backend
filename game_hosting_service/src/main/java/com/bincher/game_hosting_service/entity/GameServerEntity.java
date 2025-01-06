package com.bincher.game_hosting_service.entity;

import com.bincher.game_hosting_service.dto.request.game.PatchAdminGameServerRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PatchGameServerRequestDto;
import com.bincher.game_hosting_service.dto.request.game.PostGameServerRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="game_server")
@Table(name="game_server")
public class GameServerEntity {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private String content;
    private String location;
    private String performance;
    private String disk;
    private boolean backup;
    private String billingAmount;
    private String requestDetails;
    private int modeCount;
    private String status;
    private String serverAddress;
    private int gameId;
    private String userId;
    private boolean paymentStatus;

    public GameServerEntity(PostGameServerRequestDto dto, String userId, int gameId){
        this.name = dto.getName();
        this.content = dto.getContent();
        this.location = dto.getLocation();
        this.performance = dto.getPerformance();
        this.disk = dto.getDisk();
        this.backup = dto.isBackup();
        this.billingAmount = dto.getBillingAmount();
        this.requestDetails = dto.getRequestDetails();
        this.modeCount = dto.getModeCount();
        this.status = "확인중";
        this.serverAddress = "제작중";
        this.gameId = gameId;
        this.userId = userId;
        this.paymentStatus = false;
    }
    
    public void patchGameServer(PatchGameServerRequestDto dto){
        this.name = dto.getName();
        this.content = dto.getContent();
        this.location = dto.getLocation();
        this.performance = dto.getPerformance();
        this.disk = dto.getDisk();
        this.backup = dto.isBackup();
        this.billingAmount = dto.getBillingAmount();
        this.requestDetails = dto.getRequestDetails();
        this.modeCount = dto.getModeCount();
        this.status = "확인중";
        this.serverAddress = "제작중";
    }

    public void patchAdminGameServer(PatchAdminGameServerRequestDto dto){
        this.name = dto.getName();
        this.billingAmount = dto.getBillingAmount();
        this.requestDetails = dto.getRequestDetails();
        this.modeCount = dto.getModeCount();
        this.status = dto.getStatus();
        this.serverAddress = dto.getServerAddress();
    }

    public void patchPaymentStatus(){
        if(this.paymentStatus == false) this.paymentStatus = true;
        else this.paymentStatus = false;
    }
}
