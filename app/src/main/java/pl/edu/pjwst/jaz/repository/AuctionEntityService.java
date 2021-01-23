//package pl.edu.pjwst.jaz.repository;
//
//import org.springframework.stereotype.Repository;
//import pl.edu.pjwst.jaz.model.AuctionEntity;
//
//import javax.persistence.EntityManager;
//
//@Repository
//public class AuctionEntityService {
//    private final EntityManager em;
//
//    public AuctionEntityService(EntityManager em) {
//        this.em = em;
//    }
//
//    public AuctionEntity saveAuction(AuctionEntity auction) {
//        em.getTransaction().begin();
//        em.persist(auction);
//        em.getTransaction().commit();
//        return auction;
//    }
//}
