package pl.edu.pjwst.jaz.requestBody;

public class SubCategoryUpdateRequest {
    private String subCategoryOldName;
    private String subCategoryNewName;

    public String getSubCategoryOldName() {
        return subCategoryOldName;
    }

    public void setSubCategoryOldName(String subCategoryOldName) {
        this.subCategoryOldName = subCategoryOldName;
    }

    public String getSubCategoryNewName() {
        return subCategoryNewName;
    }

    public void setSubCategoryNewName(String subCategoryNewName) {
        this.subCategoryNewName = subCategoryNewName;
    }
}
