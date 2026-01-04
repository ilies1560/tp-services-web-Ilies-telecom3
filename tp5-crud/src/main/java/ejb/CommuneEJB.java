package ejb;

import entities.Commune;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CommuneEJB {

    @PersistenceContext(unitName = "communesPU")
    private EntityManager em;

    public long createCommune(Commune commune) {
        em.persist(commune);
        em.flush();
        return commune.getId();
    }

    public Commune findCommuneById(long id) {
        return em.find(Commune.class, id);
    }

    public Commune updateCommune(Commune commune) {
        return em.merge(commune);
    }

    public boolean deleteCommuneById(long id) {
        Commune c = em.find(Commune.class, id);
        if (c == null) return false;
        em.remove(c);
        return true;
    }

    public List<Commune> findAllCommune() {
        return em.createQuery("select c from Commune c", Commune.class).getResultList();
    }
}
