package pl.edu.pjwst.jaz.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "created_by")
    private int createBy;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {

        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {

        this.userEntity = userEntity;
    }

    @OneToMany(mappedBy = "category")
    private List<SubcategoryEntity> subcategories = new ArrayList<>();

    public List<SubcategoryEntity> getSubcategories() {

        return subcategories;
    }

    public void setSubcategories(List<SubcategoryEntity> subcategories) {

        this.subcategories = subcategories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCreateBy() {
        return createBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
