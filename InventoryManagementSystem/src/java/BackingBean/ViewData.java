package BackingBean;

import DBModel.InventoryTB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@SessionScoped
@Named
public class ViewData implements Serializable {
    private boolean checked;
    private String title;
    private String author;
    private String publisher;
    private String status;
    private String price;
    private String storage;
    private String quantity;
    private String note;
    private String number;
    private InventoryTB inventory;
    
    private List<SelectItem> numberList;

    public ViewData() {
    }

    public ViewData(InventoryTB inventory) {
        this.checked = false;
        this.title = inventory.getBook().getTitle();
        this.author = inventory.getBook().getAuthor();
        this.publisher = inventory.getPublisher();
        this.status = inventory.getStatus().getRank();
        if (this.status.contains("Not Select")) {
            this.status = "";
        }
        if(inventory.getPrice() == null) {
            this.price = "";
        } else {
            this.price = String.valueOf(inventory.getPrice());
        }
        this.storage = inventory.getStorage().getName();
        if (this.storage.contains("Not Select")) {
            this.storage = "";
        }
        this.quantity = String.valueOf(inventory.getQuantity());
        this.note = inventory.getNote();
        this.number = "1";
        this.inventory = inventory;
        numberList = new ArrayList<>();
        for(int i = 1; i <= Integer.valueOf(this.quantity); i++) {
            final SelectItem item = new SelectItem();
            item.setLabel(String.valueOf(i));
            item.setValue(String.valueOf(i));
            numberList.add(item);
        }
    }
    
    public InventoryTB getUpdateInventory() {
        inventory.setQuantity(Integer.valueOf(this.quantity) - Integer.valueOf(this.number));
        return inventory;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public InventoryTB getInventory() {
        return inventory;
    }

    public void setInventory(InventoryTB inventory) {
        this.inventory = inventory;
    }

    public List<SelectItem> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<SelectItem> numberList) {
        this.numberList = numberList;
    }
}
