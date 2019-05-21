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
    private String keyword = "";
    
    private List<ViewData> viewList;
    private List<ViewData> checkedViewList;
    
    public ShipInventory() {};
    
    @EJB
    private DBController dbc;
    
    @PostConstruct
    public void init() {
        createViewList();
        checkedViewList = new ArrayList<>();
    }
    
    public String search() {
        createViewList();
        createCheckedViewList();
        return null;
    }
    
    private void createViewList() {
        List<InventoryTB> inventoryList;
        if(keyword.isEmpty()) {
            inventoryList = dbc.getInventoryList();
        } else {
            inventoryList = dbc.getInventoryList(keyword);
        }
        viewList = new ArrayList<>();
        inventoryList.forEach((i) -> {
            viewList.add(new ViewData(i));
        });
    }
    
    private void createCheckedViewList() {
        viewList.forEach(((v) -> {
            if(v.isChecked()) checkedViewList.add(v);
        }));
        checkedViewList.forEach((c) -> {
            if(!c.isChecked()) checkedViewList.remove(c);
        });
    }
    
    public String toConfirm() {
        createCheckedViewList();
        return "ship_confirm.xhtml";
    }
    
    public String ship() {
        checkedViewList.forEach((c) -> {
            dbc.update(c.getUpdateInventory());
        });
        createViewList();
        createCheckedViewList();
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