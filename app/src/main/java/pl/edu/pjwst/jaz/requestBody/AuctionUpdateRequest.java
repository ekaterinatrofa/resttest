package pl.edu.pjwst.jaz.requestBody;

public class AuctionUpdateRequest {

    private String oldAuctionTitle;
    private String oldAuctionDescription;
    private String subCategoryName;
    private int oldAuctionPrice;


    private String newAuctionTitle;
    private String newAuctionDescription;
    private int newAuctionPrice;


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

    public int getOldAuctionPrice() {
        return oldAuctionPrice;
    }

    public void setOldAuctionPrice(int oldAuctionPrice) {
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

    public int getNewAuctionPrice() {
        return newAuctionPrice;
    }

    public void setNewAuctionPrice(int newAuctionPrice) {
        this.newAuctionPrice = newAuctionPrice;
    }
}
