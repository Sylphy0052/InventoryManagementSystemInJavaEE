package BackingBean;

import Controller.DBController;
import DBModel.BookTB;
import DBModel.InventoryTB;
import DBModel.StatusTB;
import DBModel.StorageTB;
import DBModel.UserTB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class TopPage implements Serializable {

    private String message = "";
    private boolean flg = true;

    @EJB
    private DBController dbc;

    public TopPage() {
    }

    @PostConstruct
    public void init() {
        addStatus();
        addStorage();
        addUser();
        if (message.equals("Add user table")) {
            message = "Add Table Initialize";
        } else {
            message = "";
        }
    }

    public String addStatus() {
        try {
            List<StatusTB> statusList = dbc.getStatusList();
            if (!statusList.isEmpty()) {
                message = "Status table is already added";
            } else {
                String[] rank = {"-Not Select-", "A", "B", "C", "D", "E"};
                String[] desc = {"None", "Best", "Good", "Normal", "Not good", "Bad"};
                for (int i = 0; i < rank.length; i++) {
                    StatusTB status = new StatusTB(rank[i], desc[i]);
                    dbc.add(status);
                }
                message = "Add status table";
            }
        } catch (EJBException e) {
            message = "EJBException while adding status table";
        }
        return null;
    }

    public String addStorage() {
        try {
            List<StorageTB> storageList = dbc.getStorageList();
            if (!storageList.isEmpty()) {
                message = "Storage table is already added";
            } else {
                String[] storageNames = {"-Not Select-", "StorageA", "StorageB"};
                for (String name : storageNames) {
                    StorageTB storage = new StorageTB(name);
                    dbc.add(storage);
                }
                message = "Add storage table";
            }
        } catch (EJBException e) {
            message = "EJBException while adding storage table";
        }
        return null;
    }

    public String addUser() {
        try {
            List<UserTB> userList = dbc.getUserList();
            if (!userList.isEmpty()) {
                message = "User table is already added";
            } else {
                dbc.add(new UserTB("test", "test"));
                message = "Add user table";
            }
        } catch (EJBException e) {
            message = "EJBException while adding user table";
        }
        return null;
    }

    public String addBook() {
        if (flg) {
            List<StatusTB> statusList = dbc.getStatusList();
            List<StorageTB> storageList = dbc.getStorageList();
            BookTB book1 = new BookTB("PythonによるTCP/IPソケットプログラミング", "小高知宏");
            InventoryTB inventory1 = new InventoryTB(book1, statusList.get(0), storageList.get(0), "2376", "オーム社", "", "4");
            BookTB book2 = new BookTB("ゼロから作るDeep Learning", "斎藤康毅");
            InventoryTB inventory2 = new InventoryTB(book2, statusList.get(0), storageList.get(2), "3672", "オライリー・ジャパン", "持ってる", "8");
            BookTB book3 = new BookTB("Pythonで体験してわかるアルゴリズムとデータ構造", "西澤弘毅, 森田光");
            InventoryTB inventory3 = new InventoryTB(book3, statusList.get(3), storageList.get(1), "2592", "近代科学社", "ほしい", "3");
            BookTB book4 = new BookTB("リーダブルコード", "ダスティン ボズウェル, トレバー フォシェ");
            InventoryTB inventory4 = new InventoryTB(book4, statusList.get(1), storageList.get(2), "2592", "オライリー・ジャパン", "おすすめ", "120");
            BookTB book5 = new BookTB("Pythonで動かして学ぶ！あたらしい機械学習の教科書", "伊藤真");
            InventoryTB inventory5 = new InventoryTB(book5, statusList.get(2), storageList.get(0), "2894", "翔泳社", "", "1");
            dbc.add(inventory1);
            dbc.add(inventory2);
            dbc.add(inventory3);
            dbc.add(inventory4);
            dbc.add(inventory5);
            message = "Add Inventory Table";
            flg = false;
        } else {
            message = "Already Add Inventory Table";
        }
        return null;
    }

    public String clear() {
        message = "";
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
