package BackingBean;

import Controller.DBController;
import DBModel.BookTB;
import DBModel.InventoryTB;
import DBModel.StatusTB;
import DBModel.StorageTB;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class RegisterInventory implements Serializable {
    private String title;
    private String author;
    private String status;
    private String price;
    private String storage;
    
    @EJB
    private DBController dbc;
    
    public RegisterInventory() {}
    
    public String toConfirm() {
        return "register_confirm.xhtml";
    }
    
    public String back() {
        return "register_inventory.xhtml";
    }
    
    public String registerInventory() {
        BookTB bookTB = new BookTB(title, author);
        StatusTB statusTB = dbc.getStatus(status);
        StorageTB storageTB = dbc.getStorage(storage);
        InventoryTB inventoryTB;
        if(price.isEmpty()) {
            inventoryTB = new InventoryTB(bookTB, statusTB, storageTB);
        } else {
            inventoryTB = new InventoryTB(bookTB, statusTB, storageTB, price);
        }
        bookTB.setInventory(inventoryTB);
        dbc.add(inventoryTB);
        clear();
        return "main.xhtml";
    }
    
    private void clear() {
        title = "";
        author = "";
        status = "";
        price = "";
        storage = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
    
    
}
