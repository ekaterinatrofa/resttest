package pl.edu.pjwst.jaz.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuctionParameterKey implements Serializable {

    @Column(name = "auction_id")
    private Long auctionId;

    @Column(name = "parameter_id")
    private Long parameterId;

    public AuctionParameterKey() {
    }

    public AuctionParameterKey(Long auctionId, Long parameterId) {
        super();
        this.auctionId = auctionId;
        this.parameterId = parameterId;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuctionParameterKey)) return false;
        AuctionParameterKey that = (AuctionParameterKey) o;
        return Objects.equals(getAuctionId(), that.getAuctionId()) &&
                Objects.equals(getParameterId(), that.getParameterId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auctionId == null) ? 0 : auctionId.hashCode());
        result = prime * result + ((parameterId == null) ? 0 : parameterId.hashCode());
        return result;
    }
}
