package DBModel;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class InventoryTB implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;
    
    private Integer price;
    private String publisher;
    private String note;
    private Integer quantity;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.DETACH})
    private BookTB book;
    
    @ManyToOne(cascade={CascadeType.DETACH})
    private StatusTB status;
    
    @ManyToOne(cascade={CascadeType.DETACH})
    private StorageTB storage;
    
    public InventoryTB() {}
    
    public InventoryTB(BookTB book) {
        this.book = book;
    }
    
    public InventoryTB(BookTB book, StatusTB status, StorageTB storage, String price, String publisher, String note, String quantity) {
        this.book = book;
        this.status = status;
        this.storage = storage;
        this.price = price.isEmpty()? null: Integer.valueOf(price);
        this.publisher = publisher;
        this.note = note;
        this.quantity = quantity.isEmpty()? 1: Integer.valueOf(quantity);
    }
    
    public void setAll(BookTB book, StatusTB status, StorageTB storage, String price, String publisher, String note, String quantity) {
        this.book = book;
        this.status = status;
        this.storage = storage;
        this.price = price.isEmpty()? null: Integer.valueOf(price);
        this.publisher = publisher;
        this.note = note;
        this.quantity = quantity.isEmpty()? 1: Integer.valueOf(quantity);
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getQuantity() {
        return quantity == null? "": String.valueOf(quantity);
    }

    public void setQuantity(String quantity) {
        this.quantity = Integer.valueOf(quantity);
    }
    
    public BookTB getBook() {
        return book;
    }

    public void setBook(BookTB book) {
        this.book = book;
    }

    public StatusTB getStatus() {
        return status;
    }

    public void setStatus(StatusTB status) {
        this.status = status;
    }

    public StorageTB getStorage() {
        return storage;
    }

    public void setStorage(StorageTB storage) {
        this.storage = storage;
    }
}
