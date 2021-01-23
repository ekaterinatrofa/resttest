package pl.edu.pjwst.jaz.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pjwst.jaz.LoginController;
import pl.edu.pjwst.jaz.model.CategoryEntity;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;
import pl.edu.pjwst.jaz.requestBody.CategoryUpdateRequest;

import javax.persistence.EntityManager;

@Repository
public class CategoryEntityService {
    private EntityManager em;
    private final UserEntityService userEntityService;



    public CategoryEntityService(EntityManager em, UserEntityService userEntityService) {
        this.em = em;
        this.userEntityService = userEntityService;
    }

    public String addCategory(CategoryRequest categoryRequest, String user) {
        var categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryRequest.getCategoryName());
        categoryEntity.setUserEntity(categoryEntity.getUserEntity());
        categoryEntity.setUserEntity(userEntityService.findUserByUserName(user));
        em.merge(categoryEntity);
        return categoryEntity.getName();
    }


    public String updateCategory(CategoryUpdateRequest categoryUpdateRequest) {
      //  var categoryEntity = new CategoryEntity();
        CategoryEntity oldCategoryEntity = findCategoryByCategoryName(categoryUpdateRequest.getOldCategoryName());
        oldCategoryEntity.setName(categoryUpdateRequest.getNewCategoryName());
        em.persist(oldCategoryEntity);
        return oldCategoryEntity.getName();
    }

    public CategoryEntity findCategoryByCategoryName(String name) {
        return em.createQuery("select ce from CategoryEntity ce where ce.name = :name", CategoryEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public CategoryEntity findCategoryByCategoryId(int id) {
        return em.createQuery("select ce from CategoryEntity ce where ce.id = :id", CategoryEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }



}
