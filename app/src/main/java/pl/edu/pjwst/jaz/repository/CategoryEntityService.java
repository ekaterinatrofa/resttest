package pl.edu.pjwst.jaz.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pjwst.jaz.model.CategoryEntity;
import pl.edu.pjwst.jaz.model.UserEntity;
import pl.edu.pjwst.jaz.requestBody.CategoryRequest;

import javax.persistence.EntityManager;

@Repository
public class CategoryEntityService {
    private EntityManager em;

    public CategoryEntityService(EntityManager em) {
        this.em = em;
    }

    public CategoryEntity saveCategory(CategoryRequest categoryRequest) {
        var categoryEntity = new CategoryEntity();
//        categoryEntity.setId(categoryRequest.getId());
//        System.out.println("ID    "+categoryRequest.getId());
        categoryEntity.setName(categoryRequest.getName());
    //    categoryEntity.setCreateBy(categoryRequest.getCreatedBy());
        em.merge(categoryEntity);
        return findCategoryByCategoryName(categoryRequest.getName());
    }

    public CategoryEntity findCategoryByCategoryName(String name) {
        return em.createQuery("select ce from CategoryEntity ce where ce.name = :name", CategoryEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }


}
