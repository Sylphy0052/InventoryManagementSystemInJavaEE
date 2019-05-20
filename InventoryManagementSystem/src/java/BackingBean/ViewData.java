package BackingBean;

import DBModel.InventoryTB;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class ViewData implements Serializable {
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