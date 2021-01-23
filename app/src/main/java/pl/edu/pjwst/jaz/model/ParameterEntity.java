//package pl.edu.pjwst.jaz.model;
//
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.math.BigInteger;
//import java.time.LocalDateTime;
//import java.util.Set;
//
//@Entity
//@Table(name = "parameter")
//public class ParameterEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private BigInteger id;
//
//    private String key;
//
//    @Column(name = "created_at")
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//
//    @OneToMany(mappedBy = "parameter")
//    private Set<AuctionParameter> values;
//
//    public BigInteger getId() {
//        return id;
//    }
//
//    public void setId(BigInteger id) {
//        this.id = id;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Set<AuctionParameter> getValues() {
//        return values;
//    }
//
//    public void setValues(Set<AuctionParameter> values) {
//        this.values = values;
//    }
//}
