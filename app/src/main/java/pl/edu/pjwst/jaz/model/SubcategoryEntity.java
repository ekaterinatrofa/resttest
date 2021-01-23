//package pl.edu.pjwst.jaz.model;
//
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "sub_category")
//public class SubcategoryEntity {
//
//    @Id
//    @GeneratedValue
//    private int id;
//
//    @Column(name = "category_id")
//    private int categoryId;
//
//    private String name;
//
//    @Column(name = "created_at")
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
//    private CategoryEntity categoryEntity;
//
//    @OneToMany(mappedBy = "sub_category")
//    private List<AuctionEntity> auctions = new ArrayList<>();
//
//    public List<AuctionEntity> getAuctions() {
//        return auctions;
//    }
//
//    public void setAuctions(List<AuctionEntity> auctions) {
//        this.auctions = auctions;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public CategoryEntity getCategoryEntity() {
//        return categoryEntity;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public void setCategoryEntity(CategoryEntity categoryEntity) {
//        this.categoryEntity = categoryEntity;
//    }
//}
