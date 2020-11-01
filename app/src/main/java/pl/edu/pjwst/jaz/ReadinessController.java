package pl.edu.pjwst.jaz;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class ReadinessController {
    private final EntityManager em;
    public ReadinessController(EntityManager em) {
        this.em = em;
    }
    @Transactional
    @GetMapping("/is-ready")
    public void isReady() {
        var entity = new Test1Entity();
        entity.setName("sdavsda");
        em.persist(entity);
    }
  /*  @GetMapping(value="/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {

        return "Hello there!";
    }*/
}
