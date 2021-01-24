package pl.edu.pjwst.jaz.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parameter")
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String key;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "parameterEntity", fetch = FetchType.EAGER)
    private Set<AuctionParameter> values = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<AuctionParameter> getValues() {
        return values;
    }

    public void setValues(Set<AuctionParameter> values) {
        this.values = values;
    }
}
