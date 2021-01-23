package pl.edu.pjwst.jaz.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pjwst.jaz.model.CategoryEntity;
import pl.edu.pjwst.jaz.model.SubCategoryEntity;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;
import pl.edu.pjwst.jaz.requestBody.SubCategoryRequest;

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
        em.merge(subCategory);
        return subCategory.getName();
    }


//    public String updateCategory(CategoryUpdateRequest categoryUpdateRequest) {
//      //  var categoryEntity = new CategoryEntity();
//        CategoryEntity oldCategoryEntity = findCategoryByCategoryName(categoryUpdateRequest.getOldCategoryName());
//        oldCategoryEntity.setName(categoryUpdateRequest.getNewCategoryName());
//        em.persist(oldCategoryEntity);
//        return oldCategoryEntity.getName();
//    }

//    public CategoryEntity findCategoryByCategoryName(String name) {
//        return em.createQuery("select ce from CategoryEntity ce where ce.name = :name", CategoryEntity.class)
//                .setParameter("name", name)
//                .getSingleResult();
//    }


}
