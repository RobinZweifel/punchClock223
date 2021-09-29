package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.SickLeave;
import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class SickLeaveService {
    @Inject
    private EntityManager entityManager;

    private SickLeaveService(){

    }

    @Transactional
    public SickLeave createSickLeave(SickLeave sickLeave){
        entityManager.persist(sickLeave);
        return sickLeave;
    }

    @Transactional
    public void delSickLeaveObject(SickLeave sickLeave){
        entityManager.remove(sickLeave);
    }


    @SuppressWarnings("unchecked")
    public List<SickLeave> findAll() {
        var query = entityManager.createQuery("FROM SickLeave");
        return query.getResultList();
    }

    public User getSickLeave(Long id){return entityManager.find(User.class, id);}

    @Transactional
    public SickLeave updateSickLeave(SickLeave sickLeave) {
        return entityManager.merge(sickLeave);
    }


}
