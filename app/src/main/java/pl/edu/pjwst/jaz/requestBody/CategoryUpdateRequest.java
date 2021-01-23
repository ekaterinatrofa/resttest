package pl.edu.pjwst.jaz.requestBody;

public class CategoryUpdateRequest {

    private String oldCategoryName;
    private String newCategoryName;

    public String getOldCategoryName() {
        return oldCategoryName;
    }

    public void setOldCategoryName(String oldCategoryName) {
        this.oldCategoryName = oldCategoryName;
    }

    public String getNewCategoryName() {
        return newCategoryName;
    }

    public void setNewCategoryName(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }
}
