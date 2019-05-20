package Controller;

import DBModel.InventoryTB;
import DBModel.StatusTB;
import DBModel.StorageTB;
import DBModel.UserTB;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class DBController {
    @PersistenceContext
    private EntityManager em;
    
    public DBController() {}
    
    public void add(StatusTB status) {
        em.persist(status);
    }
    
    public void add(StorageTB storage) {
        em.persist(storage);
    }
    
    public void add(UserTB user) {
        em.persist(user);
    }
    
    public void add(InventoryTB inventory) {
        em.persist(inventory);
    }
    
    public UserTB getUser(String name, String pass) {
        TypedQuery<UserTB> query = em.createQuery(
                "SELECT u FROM UserTB u WHERE u.name = :name AND u.pass = :pass",
                UserTB.class
        );
        return query
                .setParameter("name", name)
                .setParameter("pass", pass)
                .getSingleResult();
    }
    
    public StorageTB getStorage(String name) {
        TypedQuery<StorageTB> query = em.createQuery(
                "SELECT s FROM StorageTB s WHERE s.name = :name",
                StorageTB.class
        );
        return query
                .setParameter("name", name)
                .getSingleResult();
    }
    
    public StatusTB getStatus(String rank) {
        TypedQuery<StatusTB> query = em.createQuery(
                "SELECT s FROM StatusTB s WHERE s.rank = :rank",
                StatusTB.class
        );
        return query
                .setParameter("rank", rank)
                .getSingleResult();
    }
    
    public List<StatusTB> getStatusList() {
        return em.createQuery("SELECT s FROM StatusTB s")
                .getResultList();
    }
    
    public List<StorageTB> getStorageList() {
        return em.createQuery("SELECT s FROM StorageTB s")
                .getResultList();
    }
    
    public List<UserTB> getUserList() {
        return em.createQuery("SELECT u FROM UserTB u")
                .getResultList();
    }
    
    public List<InventoryTB> getInventoryList() {
        return em.createQuery("SELECT i FROM InventoryTB i")
                .getResultList();
    }
    
    public List<InventoryTB> getInventoryList(String keyword) {
        TypedQuery<InventoryTB> titleQuery = em.createQuery(
                "SELECT i FROM InventoryTB i WHERE i.book.title = :title",
                InventoryTB.class
        );
        List<InventoryTB> titleList = titleQuery
                .setParameter("title", keyword)
                .getResultList();
        
        TypedQuery<InventoryTB> authorQuery = em.createQuery(
                "SELECT i FROM InventoryTB i WHERE i.book.author = :author",
                InventoryTB.class
        );
        List<InventoryTB> authorList = authorQuery
                .setParameter("author", keyword)
                .getResultList();
        
        List<InventoryTB> resultList = titleList;
        resultList = new ArrayList<>(new HashSet<>(authorList));
        return resultList;
    }
}
