package pl.edu.pjwst.jaz.requestBody;

import java.math.BigInteger;
import java.util.List;

public class AuctionUpdateRequest {

    private String oldAuctionTitle;
    private String oldAuctionDescription;
    private String subCategoryName;
    private BigInteger oldAuctionPrice;
    private List<String> photos;



    private String newAuctionTitle;
    private String newAuctionDescription;
    private BigInteger newAuctionPrice;


    public String getOldAuctionTitle() {
        return oldAuctionTitle;
    }

    public void setOldAuctionTitle(String oldAuctionTitle) {
        this.oldAuctionTitle = oldAuctionTitle;
    }

    public String getOldAuctionDescription() {
        return oldAuctionDescription;
    }

    public void setOldAuctionDescription(String oldAuctionDescription) {
        this.oldAuctionDescription = oldAuctionDescription;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public BigInteger getOldAuctionPrice() {
        return oldAuctionPrice;
    }

    public void setOldAuctionPrice(BigInteger oldAuctionPrice) {
        this.oldAuctionPrice = oldAuctionPrice;
    }

    public String getNewAuctionTitle() {
        return newAuctionTitle;
    }

    public void setNewAuctionTitle(String newAuctionTitle) {
        this.newAuctionTitle = newAuctionTitle;
    }

    public String getNewAuctionDescription() {
        return newAuctionDescription;
    }

    public void setNewAuctionDescription(String newAuctionDescription) {
        this.newAuctionDescription = newAuctionDescription;
    }

    public BigInteger getNewAuctionPrice() {
        return newAuctionPrice;
    }

    public void setNewAuctionPrice(BigInteger newAuctionPrice) {
        this.newAuctionPrice = newAuctionPrice;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
