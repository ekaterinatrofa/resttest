//package pl.edu.pjwst.jaz.model;
//
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "auction")
//public class AuctionEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true)
//    private int id;
//
//    private String title;
//
//    private String description;
//
//    @Column(name = "sub_category_id")
//    private int subCategoryId;
//
//    private int price;
//
//    @Column(name = "created_by")
//    private int createdBy;
//
//    @Column(name = "created_at")
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @ManyToOne
//    @JoinColumn(name = "created_by", referencedColumnName = "id")
//    private UserEntity userEntity;
//
//    public UserEntity getUserEntity() {
//        return userEntity;
//    }
//
//    public void setUserEntity(UserEntity userEntity) {
//        this.userEntity = userEntity;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
//    private SubcategoryEntity subcategory;
//
//    public void setSubcategory(SubcategoryEntity subcategory) {
//        this.subcategory = subcategory;
//    }
//
//    public SubcategoryEntity getSubcategory() {
//        return subcategory;
//    }
//
//    @OneToMany(mappedBy = "auction")
//    private List<AuctionPhotoEntity> auctionPhotos = new ArrayList<>();
//
//    public List<AuctionPhotoEntity> getAuctionPhotos() {
//
//        return auctionPhotos;
//    }
//
//    public void setAuctionPhotos(List<AuctionPhotoEntity> auctionPhotos) {
//
//        this.auctionPhotos = auctionPhotos;
//    }
//
//    public Set<AuctionParameter> getValues() {
//        return values;
//    }
//
//    public void setValues(Set<AuctionParameter> values) {
//        this.values = values;
//    }
//
//    @OneToMany(mappedBy = "auction")
//    private Set<AuctionParameter> values;
//
//
//    public int getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getSubCategoryId() {
//        return subCategoryId;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public int getCreatedBy() {
//        return createdBy;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setSubCategoryId(int subCategoryId) {
//        this.subCategoryId = subCategoryId;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public void setCreatedBy(int createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//}
