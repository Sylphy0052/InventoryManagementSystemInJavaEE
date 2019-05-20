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
}
