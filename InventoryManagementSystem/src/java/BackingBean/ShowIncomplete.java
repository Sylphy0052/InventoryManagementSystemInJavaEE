package BackingBean;

import Controller.DBController;
import DBModel.InventoryTB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class ShowIncomplete implements Serializable {
    private List<ViewData> viewList;
    
    @EJB
    private DBController dbc;
    
    public ShowIncomplete() {}
    
    private void createInventoryList() {
        viewList = new ArrayList<>();
        List<InventoryTB> inventoryList;
        inventoryList = dbc.getIncompleteInventoryList();
        inventoryList.forEach((i) -> {
            viewList.add(new ViewData(
                    i.getBook().getTitle(), 
                    i.getBook().getAuthor(),
                    i.getStatus().getRank() + ":" + i.getStatus().getDescription(),
                    i.getPrice(),
                    i.getStorage().getName(),
                    i
            ));
        });
    }
    
    public String search() {
        createInventoryList();
        return null;
    }
    
    public String goToUpdate(InventoryTB inventory) {
        return "search_inventory.xhtml";
    }
    
    public String goToUpdateQuantity(InventoryTB inventory) {
        return "search_inventory.xhtml";
    }

    public List<ViewData> getViewList() {
        createInventoryList();
        return viewList;
    }

    public void setViewList(List<ViewData> viewList) {
        this.viewList = viewList;
    }
    
    public class ViewData {
        private String title;
        private String author;
        private String rank;
        private String price;
        private String storage;
        private InventoryTB inventory;
        
        public ViewData() {}
        
        public ViewData(String title, String author, String rank, String price, String storage, InventoryTB inventory) {
            this.title = title;
            this.author = author;
            if(rank.contains("Not Select")) {
                this.rank = "";
            } else {
                this.rank = rank;
            }
            this.price = price;
            if(storage.contains("Not Select")) {
                this.storage = "";
            } else {
                this.storage = storage;
            }
            this.inventory = inventory;
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

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
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

        public InventoryTB getInventory() {
            return inventory;
        }

        public void setInventory(InventoryTB inventory) {
            this.inventory = inventory;
        }
    }
}
