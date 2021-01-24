package pl.edu.pjwst.jaz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    private String title;

    private String description;

    private BigInteger price;

    @Version
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @JsonIgnore
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    @JsonIgnore
    private SubCategoryEntity subcategory;

    public void setSubcategory(SubCategoryEntity subcategory) {
        this.subcategory = subcategory;
    }

    public SubCategoryEntity getSubcategory() {
        return subcategory;
    }


    @OneToMany(mappedBy = "auctionEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn(name = "index", nullable = false)
    private List<AuctionPhotoEntity> auctionPhotos = new ArrayList<>();

    public List<AuctionPhotoEntity> getAuctionPhotos() {

        return auctionPhotos;
    }

    public void setAuctionPhotos(List<AuctionPhotoEntity> auctionPhotos) {

        this.auctionPhotos = auctionPhotos;
    }

    public Set<AuctionParameter> getValues() {
        return values;
    }

    public void setValues(Set<AuctionParameter> values) {
        this.values = values;
    }

    @OneToMany(mappedBy = "parameterEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AuctionParameter> values = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigInteger getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
