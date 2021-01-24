package pl.edu.pjwst.jaz.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pjwst.jaz.AuthenticationService;
import pl.edu.pjwst.jaz.model.*;
import pl.edu.pjwst.jaz.requestBody.AuctionRequest;
import pl.edu.pjwst.jaz.requestBody.AuctionUpdateRequest;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class AuctionEntityService {
    private final EntityManager em;
    private final SubCategoryEntityService subCategoryEntityService;
    private final UserEntityService userEntityService;

    private static final Logger logger = LoggerFactory.getLogger(AuctionEntityService.class);

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

        for (String ph :  auctionRequest.getPhotos()) {
            AuctionPhotoEntity auctionPhotoEntity = new AuctionPhotoEntity();
            auctionPhotoEntity.setPhoto(ph);
            auctionPhotoEntity.setAuctionEntity(auctionEntity);
            auctionEntity.getAuctionPhotos().add(auctionPhotoEntity);
        }
        for (Map.Entry<String, String> value : auctionRequest.getParameters().entrySet()) {
            ParameterEntity parameterEntity =  new ParameterEntity(); // parameter
            parameterEntity.setKey(value.getKey());
            System.out.println("get key  " + parameterEntity.getKey());
            System.out.println("KEY " + value.getKey());
       //     em.persist(parameterEntity);

            AuctionParameter auctionParameter = new AuctionParameter(); // linking
            auctionParameter.setValue(value.getValue());
            auctionParameter.setAuctionEntity(auctionEntity);
            auctionParameter.setParameterEntity(parameterEntity);

            auctionEntity.getValues().add(auctionParameter);
            parameterEntity.getValues().add(auctionParameter);
            //System.out.println("KEY " + value.getKey());
           // System.out.println("VALUE " + value.getValue());

        }



        em.merge(auctionEntity);
        return auctionEntity;
    }



    public AuctionEntity updateAuction(AuctionUpdateRequest auctionUpdateRequest, String user) {
        try {
            int subCategoryId = subCategoryEntityService.findSubCategoryBySubCategoryName(auctionUpdateRequest.getSubCategoryName()).getId();
            Long createById = userEntityService.findUserByUserName(user).getId();
            AuctionEntity oldAuctionEntity = findAuction(auctionUpdateRequest.getOldAuctionTitle(), auctionUpdateRequest.getOldAuctionPrice(),
                    subCategoryId,createById);
            if (auctionUpdateRequest.getNewAuctionTitle() != null) {
                oldAuctionEntity.setTitle(auctionUpdateRequest.getNewAuctionTitle());
            }
            if (auctionUpdateRequest.getNewAuctionDescription() != null) {
                oldAuctionEntity.setDescription(auctionUpdateRequest.getNewAuctionDescription());
            }
            if (auctionUpdateRequest.getNewAuctionPrice()!= null) {
                oldAuctionEntity.setPrice(auctionUpdateRequest.getNewAuctionPrice());
            }
            if (auctionUpdateRequest.getPhotos() != null) {
                for (String ph : auctionUpdateRequest.getPhotos()) {
                    AuctionPhotoEntity auctionPhotoEntity = new AuctionPhotoEntity();
                    auctionPhotoEntity.setPhoto(ph);
                    auctionPhotoEntity.setAuctionEntity(oldAuctionEntity);
                    oldAuctionEntity.getAuctionPhotos().add(auctionPhotoEntity);
                }
            }
            em.merge(oldAuctionEntity);
            return oldAuctionEntity;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You are not allowed to modify this auction", e);
        }
    }

    public AuctionEntity updateAuctionPut(AuctionUpdateRequest auctionUpdateRequest, String user, Long auctionId) {
        try{
            Long createById = userEntityService.findUserByUserName(user).getId();
            AuctionEntity oldAuctionEntity = findAuctionById(createById, auctionId);
            if (auctionUpdateRequest.getNewAuctionTitle() != null) {
                oldAuctionEntity.setTitle(auctionUpdateRequest.getNewAuctionTitle());
            }
            if (auctionUpdateRequest.getNewAuctionDescription() != null) {
                oldAuctionEntity.setDescription(auctionUpdateRequest.getNewAuctionDescription());
           }
            if (auctionUpdateRequest.getNewAuctionPrice()!= null) {
                oldAuctionEntity.setPrice(auctionUpdateRequest.getNewAuctionPrice());
            }
            if (auctionUpdateRequest.getPhotos() != null) {
                for (String ph : auctionUpdateRequest.getPhotos()) {
                    AuctionPhotoEntity auctionPhotoEntity = new AuctionPhotoEntity();
                    auctionPhotoEntity.setPhoto(ph);
                    auctionPhotoEntity.setAuctionEntity(oldAuctionEntity);
                    oldAuctionEntity.getAuctionPhotos().add(auctionPhotoEntity);
                }
            }
            em.merge(oldAuctionEntity);
            return oldAuctionEntity;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You are not allowed to modify this auction", e);
       }
    }

    public List<AuctionEntity> listOfAllAuctions() {
        return em.createQuery("select ap.photo,au.title,au.price, au.description from AuctionEntity au, AuctionPhotoEntity ap where " +
                "ap.auctionEntity.id = au.id and ap.index = 0")
                .getResultList();

    }

    public AuctionEntity findAuction(String title, BigInteger price, int subCategoryId, Long createdById) {
        return em.createQuery("select au from AuctionEntity au where au.title = :title and  au.price = :price " +
                "and au.subcategory.id = :subCategoryId and au.userEntity.id = :createdById", AuctionEntity.class)
                .setParameter("title", title)
                .setParameter("price", price)
                .setParameter("subCategoryId", subCategoryId)
                .setParameter("createdById", createdById)
                .getSingleResult();
    }
    public AuctionEntity findAuctionById(Long createdById, Long auctionId) {
        return em.createQuery("select au from AuctionEntity au where au.userEntity.id = :createdById " +
                "and au.id = :auctionId", AuctionEntity.class)
                .setParameter("createdById", createdById)
                .setParameter("auctionId", auctionId)
                .getSingleResult();
    }
}
