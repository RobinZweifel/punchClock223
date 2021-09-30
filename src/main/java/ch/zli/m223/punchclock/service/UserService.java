package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    private UserService(){
    }

    @Transactional
    public User createUser(User user){
        entityManager.persist(user);
        return user;
    }



    @Transactional
    public void delUserObject(User user){
        entityManager.remove(user);
    }


    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    public User getUser(Long id){
        return entityManager.find(User.class, id);
    }

    @Transactional
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

}
