package BackingBean;

import Controller.DBController;
import DBModel.InventoryTB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class ShipInventory implements Serializable {
    private String keyword;
    
    private List<ViewData> viewList;
    private List<ViewData> allViewList;
    private List<ViewData> checkedViewList;
    
    public ShipInventory() {};
    
    @EJB
    private DBController dbc;
    
    @PostConstruct
    public void init() {
        keyword = "";
        allViewList = new ArrayList<>();
        List<InventoryTB> inventoryList = dbc.getInventoryList();
        inventoryList.forEach((i) -> {
           allViewList.add(new ViewData(i)); 
        });
        listDeepCopy();
        checkedViewList = new ArrayList<>();
    }
    
    private void listDeepCopy() {
        viewList = new ArrayList<>();
        allViewList.forEach((a) -> {
            viewList.add(a);
        });
    }
    
    public String search() {
        createViewList();
        keyword = "";
        return null;
    }
    
    private void createViewList() {
        if(keyword.isEmpty()) {
            listDeepCopy();
        } else {
            viewList = new ArrayList<>();
            allViewList.forEach((a) -> {
                if(a.getTitle().contains(keyword) || a.getAuthor().contains(keyword)) viewList.add(a);
            });
        }
    }
    
    private void createCheckedViewList() {
        allViewList.forEach(((v) -> {
            if(v.isChecked()) checkedViewList.add(v);
        }));
        checkedViewList.forEach((c) -> {
            if(!c.isChecked()) checkedViewList.remove(c);
        });
    }
    
    public String back() {
        return "ship_inventory.xhtml";
    }
    
    public String toConfirm() {
        createCheckedViewList();
        return "ship_confirm.xhtml";
    }
    
    public String ship() {
        checkedViewList.forEach((c) -> {
            dbc.update(c.getUpdateInventory());
        });
        init();
        return "main.xhtml";
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

    public List<ViewData> getCheckedViewList() {
        return checkedViewList;
    }

    public void setCheckedViewList(List<ViewData> checkedViewList) {
        this.checkedViewList = checkedViewList;
    }
}