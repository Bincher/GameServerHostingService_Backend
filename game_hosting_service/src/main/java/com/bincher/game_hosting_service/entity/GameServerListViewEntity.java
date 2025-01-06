package com.bincher.game_hosting_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.jcip.annotations.Immutable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="game_server_list_view")
@Table(name="game_server_list_view")
@Immutable
public class GameServerListViewEntity {
    
    @Id
    @Column(name = "server_id")
    private int serverId;

    private String name;
    private String content;
    private String location;
    private String performance;
    private String disk;
    private Boolean backup;
    private String billingAmount;
    private String requestDetails;
    private int modeCount;
    private String gameTitle;
    private String gameImage;
    private String status;
    private String serverAddress;
    private String serverUserId;
    private String userId;
    private String amountLevel;
    private boolean paymentStatus;
}
