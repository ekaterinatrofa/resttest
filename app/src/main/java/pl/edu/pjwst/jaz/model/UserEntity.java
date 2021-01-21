package pl.edu.pjwst.jaz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;


    @OneToMany(mappedBy = "user")
    private List<CategoryEntity> categories = new ArrayList<>();

    public List<CategoryEntity> getCategories() {

        return categories;
    }

    public void addCategory(CategoryEntity category) {
        categories.add(category);
        category.setUserEntity(this);
    }


    @OneToMany(mappedBy = "user")
    private List<AuctionEntity> auctions = new ArrayList<>();

    public List<AuctionEntity> getAuctions() {
        return auctions;
    }


    public void addAuction(AuctionEntity auction) {
        auctions.add(auction);
        auction.setUserEntity(this);
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public void setAuctions(List<AuctionEntity> auctions) {
        this.auctions = auctions;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}