package service;

import entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class UserService {

    @PersistenceContext(unitName = "usersPU")
    private EntityManager em;

    public long createUser(User user) {
        em.persist(user);
        em.flush();
        return user.getId();
    }

    public User findUserById(long id) {
        return em.find(User.class, id);
    }

    public User updateUser(User user) {
        return em.merge(user);
    }

    public boolean deleteUserById(long id) {
        User u = em.find(User.class, id);
        if (u == null) return false;
        em.remove(u);
        return true;
    }

    public List<User> findAllUser() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}
