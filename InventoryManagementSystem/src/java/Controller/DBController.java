package Controller;

import DBModel.InventoryTB;
import DBModel.StatusTB;
import DBModel.StorageTB;
import DBModel.UserTB;
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
        TypedQuery<InventoryTB> query = em.createQuery(
                "SELECT i FROM InventoryTB i"
                        + " WHERE i.book.title LIKE :keyword"
                        + " OR i.book.author LIKE :keyword",
                InventoryTB.class
        );
        return query
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
    
    public List<InventoryTB> getIncompleteInventoryList() {
        TypedQuery<InventoryTB> query = em.createQuery(
                "SELECT i FROM InventoryTB i "
                        + "WHERE i.status.statusId = :status"
                        + " OR i.storage.storageId = :storage"
                        + " OR i.price = :price",
                InventoryTB.class
        );
        return query
                .setParameter("status", 1)
                .setParameter("storage", 1)
                .setParameter("price", null)
                .getResultList();
    }
    
    public void update(InventoryTB inventory) {
        em.merge(inventory);
    }
}
