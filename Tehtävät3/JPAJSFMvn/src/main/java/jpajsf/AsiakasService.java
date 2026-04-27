package jpajsf;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AsiakasService {

    @PersistenceContext
    EntityManager em;

    public void save(Asiakas asiakas) {
        this.em.persist(asiakas);
    }

    public List<Asiakas> getAllAsiakkaat() {
        @SuppressWarnings("unchecked")
        List<Asiakas> all = this.em.createNamedQuery("Asiakas.findAll").getResultList();
        return all;
    }
}