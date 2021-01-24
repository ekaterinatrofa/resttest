package pl.edu.pjwst.jaz.requestBody;

import java.math.BigInteger;
import java.util.List;

public class AuctionRequest {

    private String auctionTitle;
    private String auctionDescription;
    private String subCategoryName;
    private BigInteger auctionPrice;
    private List<String> photos;
    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public String getAuctionDescription() {
        return auctionDescription;
    }

    public void setAuctionDescription(String auctionDescription) {
        this.auctionDescription = auctionDescription;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public BigInteger getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(BigInteger auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
