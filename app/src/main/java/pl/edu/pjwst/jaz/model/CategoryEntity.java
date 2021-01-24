package pl.edu.pjwst.jaz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by",referencedColumnName = "id")
    @JsonIgnore
    private UserEntity userEntity;

    public UserEntity getUserEntity() {

        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {

        this.userEntity = userEntity;
    }

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SubCategoryEntity> subcategories = new ArrayList<>();

    public List<SubCategoryEntity> getSubcategories() {

        return subcategories;
    }

    public void setSubcategories(List<SubCategoryEntity> subcategories) {

        this.subcategories = subcategories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
