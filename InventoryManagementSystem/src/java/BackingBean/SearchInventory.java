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
public class SearchInventory implements Serializable {
    private String keyword;
    private List<ViewData> viewList;
    
    @EJB
    private DBController dbc;
    
    public SearchInventory() {
//        getInventoryList();
    }
    
    private void createInventoryList() {
        viewList = new ArrayList<>();
        List<InventoryTB> inventoryList;
        if(!keyword.isEmpty()) {
            inventoryList = dbc.getInventoryList(keyword);
        } else {
            inventoryList = dbc.getInventoryList();
        }
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<ViewData> getViewList() {
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
            this.rank = rank;
            this.price = price;
            this.storage = storage;
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
    }
}
