package Controller;

import DBModel.StatusTB;
import DBModel.StorageTB;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    public List<StatusTB> getStatusList() {
        return em.createQuery("SELECT s FROM StatusTB s")
                .getResultList();
    }
    
    public List<StorageTB> getStorageList() {
        return em.createQuery("SELECT s FROM StorageTB s")
                .getResultList();
    }
}
