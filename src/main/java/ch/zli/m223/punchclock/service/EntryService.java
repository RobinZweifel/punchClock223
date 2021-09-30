package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    public EntryService() {
    }

    @Transactional 
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public void delEntryObject(Entry entry) {
        entityManager.remove(entry);
    }

    @Transactional
    public void delEntryId(Long id) {
        entityManager.remove(getEntry(id));
    }

    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }

    public Entry getEntry(Long id){
        return entityManager.find(Entry.class, id);
    }

    @Transactional
    public Entry updateEntry(Entry entry) {
        return entityManager.merge(entry);
    }
}
