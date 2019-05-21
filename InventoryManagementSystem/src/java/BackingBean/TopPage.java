package BackingBean;

import Controller.DBController;
import DBModel.BookTB;
import DBModel.InventoryTB;
import DBModel.StatusTB;
import DBModel.StorageTB;
import DBModel.UserTB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    
    @PostConstruct
    public void init() {
        addStatus();
        addStorage();
        addUser();
        if(message.equals("Add user table")) {
            message = "Add Table Initialize";
        } else {
            message = "";
        }
    }

    public String addStatus() {
        try {
            List<StatusTB> statusList = dbc.getStatusList();
            if (!statusList.isEmpty()) {
                message = "Status table is already added";
            } else {
                String[] rank = {"-Not Select-", "A", "B", "C", "D", "E"};
                String[] desc = {"None", "Best", "Good", "Normal", "Not good", "Bad"};
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
                String[] storageNames = {"-Not Select-", "StorageA", "StorageB"};
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
    
    public String addUser() {
        try {
            List<UserTB> userList = dbc.getUserList();
            if (!userList.isEmpty()) {
                message = "User table is already added";
            } else {
                dbc.add(new UserTB("test", "test"));
                message = "Add user table";
            }
        } catch (EJBException e) {
            message = "EJBException while adding user table";
        }
        return null;
    }
    
    public String clear() {
        message = "";
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
