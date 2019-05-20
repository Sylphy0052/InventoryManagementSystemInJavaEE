package Utils;

import Controller.DBController;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@SessionScoped
@Named
public class Selection implements Serializable {
    private ArrayList<SelectItem> statusItems;
    private ArrayList<SelectItem> storageItems;
    
    @EJB
    private DBController dbc;
    
    public Selection() {}
    
    private void createStatusItems() {
        statusItems = new ArrayList<>();
        dbc.getStatusList().stream().map((model) -> {
            final SelectItem item = new SelectItem();
            item.setLabel(model.getRank());
            item.setValue(model.getRank());
            return item;
        }).forEachOrdered((item) -> {
            statusItems.add(item);
        });
    }
    
    private void createStorageItems() {
        storageItems = new ArrayList<>();
        dbc.getStorageList().stream().map((model) -> {
            final SelectItem item = new SelectItem();
            item.setLabel(model.getName());
            item.setValue(model.getName());
            return item;
        }).forEachOrdered((item) -> {
            storageItems.add(item);
        });
    }

    public ArrayList<SelectItem> getStatusItems() {
        createStatusItems();
        return statusItems;
    }

    public void setStatusItems(ArrayList<SelectItem> statusItems) {
        this.statusItems = statusItems;
    }

    public ArrayList<SelectItem> getStorageItems() {
        createStorageItems();
        return storageItems;
    }

    public void setStorageItems(ArrayList<SelectItem> storageItems) {
        this.storageItems = storageItems;
    }
}
