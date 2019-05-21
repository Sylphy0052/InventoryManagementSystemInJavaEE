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
public class UpdateInventory implements Serializable {
    private String title;
    private String author;
    private String publisher;
    private String status;
    private String price;
    private String storage;
    private String note;
    private String quantity;
    
    private InventoryTB inventory;
    
    @EJB
    private DBController dbc;
    
    public UpdateInventory() {}
    
    public String toUpdate(InventoryTB inventory) {
        this.title = inventory.getBook().getTitle();
        this.author = inventory.getBook().getAuthor();
        this.publisher = inventory.getPublisher();
        this.status = inventory.getStatus().getRank();
        if(inventory.getPrice() == null) {
            this.price = "";
        } else {
            this.price = String.valueOf(inventory.getPrice());
        }
        this.storage = inventory.getStorage().getName();
        this.note = inventory.getNote();
        this.quantity = String.valueOf(inventory.getQuantity());
        this.inventory = inventory;
        return "update_inventory.xhtml";
    }
    
    public String toConfirm() {
        return "update_confirm.xhtml";
    }
    
    public String back() {
        return "update_inventory.xhtml";
    }
    
    public String updateInventory() {
        BookTB bookTB = new BookTB(title, author);
        StatusTB statusTB = dbc.getStatus(status);
        StorageTB storageTB = dbc.getStorage(storage);
        inventory.setAll(bookTB, statusTB, storageTB, price, publisher, note, quantity);
        dbc.update(inventory);
        clear();
        return "main.xhtml";
    }
    
    private void clear() {
        title = "";
        author = "";
        publisher = "";
        status = "";
        price = "";
        storage = "";
        note = "";
        quantity = "";
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public InventoryTB getInventory() {
        return inventory;
    }

    public void setInventory(InventoryTB inventory) {
        this.inventory = inventory;
    }
}
