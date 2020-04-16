package beke.ire.view;

import beke.ire.entity.UsersEntity;
import beke.ire.service.UserService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "userView")
@ViewScoped
public class UserView implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService service;

    private ArrayList<UsersEntity> users;
    private ArrayList<UsersEntity> filteredUsers;

    private UsersEntity newUser = new UsersEntity();
    private UsersEntity updateUser = new UsersEntity();

    public UserView() {
        System.out.println("UserView constructed");
    }

    @PostConstruct
    public void init() {
        System.out.println("userService injected : " + service);
        users = service.getAllUsers();
    }

    public String addNewUser() {
        newUser.setDeleted_user(false);
        System.out.println(newUser.toString());
        service.createUser(newUser);
        addMessage("Kliens elmentve");
        newUser = new UsersEntity();
        return "clients?faces-redirect=true\"";
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowEdit(RowEditEvent<UsersEntity> event) {
        FacesMessage msg = new FacesMessage("User Edited", event.getObject().getFull_name());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(event.getObject().toString());
        service.updateUser(event.getObject());
    }

    public void onRowCancel(RowEditEvent<UsersEntity> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getFull_name());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Getters setters


    public ArrayList<UsersEntity> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(ArrayList<UsersEntity> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public UsersEntity getNewUser() {
        return newUser;
    }

    public void setNewUser(UsersEntity newUser) {
        this.newUser = newUser;
    }

    public ArrayList<UsersEntity> getUsers() {
        if (users == null) {
            users = service.getAllUsers();
        }
        return users;
    }

    public void setUsers(ArrayList<UsersEntity> users) {
        this.users = users;
    }

    public void setService(UserService service) {
        this.service = service;
    }//it has no getter tho
}
