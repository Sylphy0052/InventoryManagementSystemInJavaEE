package Controller;

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
}
