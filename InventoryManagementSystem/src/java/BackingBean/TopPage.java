package BackingBean;

import Controller.DBController;
import DBModel.StatusTB;
import DBModel.StorageTB;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class TopPage implements Serializable {

    private String message = "";

    @EJB
    private DBController dbc;

    public TopPage() {}

    public String addStatus() {
        try {
            List<StatusTB> statusList = dbc.getStatusList();
            if (!statusList.isEmpty()) {
                message = "Status table is already added";
            } else {
                String[] rank = {"A", "B", "C", "D", "E"};
                String[] desc = {"Best", "Good", "Normal", "Not good", "Bad"};
                for (int i = 0; i < rank.length; i++) {
                    StatusTB status = new StatusTB(rank[i], desc[i]);
                    dbc.add(status);
                }
                message = "Add status table";
            }
        } catch (EJBException e) {
            message = "EJBException while adding status table";
        }
        return null;
    }

    public String addStorage() {
        try {
            List<StorageTB> storageList = dbc.getStorageList();
            if (!storageList.isEmpty()) {
                message = "Storage table is already added";
            } else {
                String[] storageNames = {"StorageA", "StorageB"};
                for(String name: storageNames) {
                    StorageTB storage = new StorageTB(name);
                    dbc.add(storage);
                }
                message = "Add storage table";
            }
        } catch (EJBException e) {
            message = "EJBException while adding storage table";
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
