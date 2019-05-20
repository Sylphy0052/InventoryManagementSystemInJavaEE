package BackingBean;

import Controller.DBController;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class Login implements Serializable {
    private String name;
    private String pass;
    
    private String message = "";
    
    @EJB
    private DBController dbc;
    
    public Login() {}
    
    public String login() {
        try {
            if(userExists()) {
                clear();
            }
            return "main.xhtml";
        }catch(EJBException e) {
            clear();
            message = "Invalid name or password";
            return null;
        }
    }
    
    private boolean userExists() {
        return dbc.getUser(name, pass) != null;
    }
    
    public void clear() {
        name = "";
        pass = "";
        message = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
