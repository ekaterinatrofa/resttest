package pl.edu.pjwst.jaz.requestBody;

public class CategoryRequest {

    private String categoryName;
    private Long categoryCreatedBy;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryCreatedBy() {
        return categoryCreatedBy;
    }

    public void setCategoryCreatedBy(Long categoryCreatedBy) {
        this.categoryCreatedBy = categoryCreatedBy;
    }
}
