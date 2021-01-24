package pl.edu.pjwst.jaz.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pjwst.jaz.model.AuctionEntity;
import pl.edu.pjwst.jaz.model.SubCategoryEntity;
import pl.edu.pjwst.jaz.requestBody.AuctionRequest;
import pl.edu.pjwst.jaz.requestBody.AuctionUpdateRequest;

import javax.persistence.EntityManager;

@Repository
public class AuctionEntityService {
    private final EntityManager em;
    private final SubCategoryEntityService subCategoryEntityService;
    private final UserEntityService userEntityService;

    public AuctionEntityService(EntityManager em, SubCategoryEntityService subCategoryEntityService, UserEntityService userEntityService) {
        this.em = em;
        this.subCategoryEntityService = subCategoryEntityService;
        this.userEntityService = userEntityService;
    }

    public AuctionEntity addAuction(AuctionRequest auctionRequest,String user) {
        var auctionEntity = new AuctionEntity();
        auctionEntity.setTitle(auctionRequest.getAuctionTitle());
        auctionEntity.setDescription(auctionRequest.getAuctionDescription());
        auctionEntity.setPrice(auctionRequest.getAuctionPrice());
        auctionEntity.setSubcategory(subCategoryEntityService.findSubCategoryBySubCategoryName(auctionRequest.getSubCategoryName()));
        auctionEntity.setUserEntity(userEntityService.findUserByUserName(user));
        em.persist(auctionEntity);
        return auctionEntity;
    }

    public AuctionEntity updateAuction(AuctionUpdateRequest auctionUpdateRequest, String user) {
        int subCategoryId = subCategoryEntityService.findSubCategoryBySubCategoryName(auctionUpdateRequest.getSubCategoryName()).getId();
        Long createById = userEntityService.findUserByUserName(user).getId();
        AuctionEntity oldAuctionEntity = findAuction(auctionUpdateRequest.getOldAuctionTitle(), auctionUpdateRequest.getOldAuctionPrice(),
                subCategoryId,createById);
        oldAuctionEntity.setTitle(auctionUpdateRequest.getNewAuctionTitle());
        oldAuctionEntity.setPrice(auctionUpdateRequest.getNewAuctionPrice());
        oldAuctionEntity.setDescription(auctionUpdateRequest.getNewAuctionDescription());
        em.merge(oldAuctionEntity);
        return oldAuctionEntity;
    }

    public AuctionEntity findAuction(String title, int price, int subCategoryId, Long createdById) {
        return em.createQuery("select au from AuctionEntity au where au.title = :title and  au.price = :price " +
                "and au.subcategory.id = :subCategoryId and au.userEntity.id = :createdById", AuctionEntity.class)
                .setParameter("title", title)
                .setParameter("price", price)
                .setParameter("subCategoryId", subCategoryId)
                .setParameter("createdById", createdById)
                .getSingleResult();
    }
}
