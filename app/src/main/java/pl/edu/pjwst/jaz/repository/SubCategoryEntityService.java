package pl.edu.pjwst.jaz.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pjwst.jaz.model.CategoryEntity;
import pl.edu.pjwst.jaz.model.SubCategoryEntity;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;
import pl.edu.pjwst.jaz.requestBody.SubCategoryRequest;
import pl.edu.pjwst.jaz.requestBody.SubCategoryUpdateRequest;

import javax.persistence.EntityManager;

@Repository
public class SubCategoryEntityService {
    private EntityManager em;
    private final UserEntityService userEntityService;
    private final CategoryEntityService categoryEntityService;

    public SubCategoryEntityService(EntityManager em, UserEntityService userEntityService, CategoryEntityService categoryEntityService) {
        this.em = em;
        this.userEntityService = userEntityService;
        this.categoryEntityService = categoryEntityService;
    }

    public String addSubCategory(SubCategoryRequest subCategoryRequest) {
        var subCategory = new SubCategoryEntity();
        subCategory.setName(subCategoryRequest.getSubCategoryName());
        subCategory.setCategoryEntity(categoryEntityService.findCategoryByCategoryName(subCategoryRequest.getCategoryName()));
        em.persist(subCategory);
        return subCategory.getName();
    }
    public String updateSubCategory(SubCategoryUpdateRequest subCategoryUpdateRequest) {
        SubCategoryEntity oldSubCategoryEntity = findSubCategoryBySubCategoryName(subCategoryUpdateRequest.getSubCategoryOldName());
        oldSubCategoryEntity.setName(subCategoryUpdateRequest.getSubCategoryNewName());
        em.persist(oldSubCategoryEntity);
        return oldSubCategoryEntity.getName();
    }


    public SubCategoryEntity findSubCategoryBySubCategoryName(String name) {
        return em.createQuery("select se from SubCategoryEntity se where se.name = :name", SubCategoryEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }


}
