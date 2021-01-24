package pl.edu.pjwst.jaz.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuctionParameter {

    @EmbeddedId
    private AuctionParameterKey id = new AuctionParameterKey();

    @ManyToOne
    @MapsId("auctionId")
    @JoinColumn(name = "auction_id")
    private AuctionEntity auctionEntity;


    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("parameterId")
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameterEntity;

    private String value;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public AuctionParameterKey getId() {
        return id;
    }

    public void setId(AuctionParameterKey id) {
        this.id = id;
    }

    public AuctionEntity getAuctionEntity() {
        return auctionEntity;
    }

    public void setAuctionEntity(AuctionEntity auctionEntity) {
        this.auctionEntity = auctionEntity;
    }

    public ParameterEntity getParameterEntity() {
        return parameterEntity;
    }

    public void setParameterEntity(ParameterEntity parameterEntity) {
        this.parameterEntity = parameterEntity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
