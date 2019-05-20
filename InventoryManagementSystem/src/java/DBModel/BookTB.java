package DBModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BookTB implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;
    
    private String title;
    private String author;
    
    @OneToMany(cascade={CascadeType.ALL})
    private List<InventoryTB> inventoryList = new ArrayList<>();
    
    public BookTB() {}
    
    public BookTB(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    public BookTB(String title, String author, InventoryTB inventory) {
        this.title = title;
        this.author = author;
        this.inventoryList.add(inventory);
    }
    
    public void setInventory(InventoryTB inventory) {
        this.inventoryList.add(inventory);
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public List<InventoryTB> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<InventoryTB> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
