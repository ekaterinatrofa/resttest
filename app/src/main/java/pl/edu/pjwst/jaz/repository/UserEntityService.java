package pl.edu.pjwst.jaz.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwst.jaz.User;
import pl.edu.pjwst.jaz.model.UserEntity;

import javax.persistence.EntityManager;

import java.util.List;


@Repository
public class UserEntityService {
    private final EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserEntityService(EntityManager em) {
        this.em = em;
    }

    public UserEntity saveUser(User user) {
        var userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setRole(user.getRole());
        em.persist(userEntity);
        return findUserByUserName(user.getUsername());
    }


    public UserEntity findUserByUserName(String uname) {
     return em.createQuery("select ue from UserEntity ue where ue.username = :uname", UserEntity.class)
                .setParameter("uname", uname)
                .getSingleResult();
    }

public List<UserEntity> print() {
    return em.createQuery("select ue.id, ue.username, ue.firstName, ue.lastName from UserEntity ue")
    //.getResultStream().collect(toList())
    .getResultList();

}


}
