package beke.ire.view;

import beke.ire.entity.LoanableStatusEntity;
import beke.ire.entity.LoanablesEntity;
import beke.ire.entity.LoaningsEntity;
import beke.ire.entity.UsersEntity;
import beke.ire.service.LoanablesService;
import beke.ire.service.LoaningService;
import beke.ire.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("loaningsView")
@ViewScoped
public class LoaningsView implements Serializable {

    @Inject
    private LoaningService service;

    @Inject
    private UserService userService;

    @Inject
    private LoanablesService loanablesService;

    private ArrayList<LoaningsEntity> loaningsEntities;
    private ArrayList<LoaningsEntity> FilteredLoaningsEntities;
    ArrayList<UsersEntity> users = new ArrayList<>();
    ArrayList<LoanablesEntity> loanables = new ArrayList<>();

    private LoaningsEntity entity = new LoaningsEntity();

    public LoaningsView() {
        System.out.println("LoaningsView constructed");
    }

    @PostConstruct
    public void init() {
        System.out.println("LoaningService injected : " + service);
        entity.setWho_borrowed(new UsersEntity());//Populating it to avoid target unreachable
        entity.setWhat_borrowed(new LoanablesEntity());

        loaningsEntities = service.getAllLoanings();
        users = userService.getAllUsers();
        loanables = loanablesService.getAllLoanables();
    }

    public String createBorrowing() {
        System.out.println("Creating : \n " + entity.toString());
        entity.setWho_borrowed(userService.getUserById(entity.getWho_borrowed().getId()));
        entity.setWhat_borrowed(loanablesService.getLoanableById(entity.getWhat_borrowed().getId()));
        service.createLoaning(entity);
        entity = new LoaningsEntity();
        return "loanings?faces-redirect=true\"";
    }

    public String closeBorrowing(int id) {
        System.out.println("ID is : " + id);
        service.deleteLoaning(id);
        return "loanings?faces-redirect=true\"";
    }

    public ArrayList<UsersEntity> getUsersWhoCanBorrow() {
        ArrayList<UsersEntity> usersWhoCanborrow = new ArrayList<>();
        for (UsersEntity e : users) {
            if (service.getNumberOfLoans(e.getId()) < 7) {
                usersWhoCanborrow.add(e);  //getting only those who can borrow something.
            }
        }
        return usersWhoCanborrow;
    }

    public ArrayList<LoanablesEntity> getWhatUsersCanBorrow() {
        ArrayList<LoanablesEntity> loanablesThatCanBeBorrowed = new ArrayList<>();
        for (LoanablesEntity e : loanables) {
            if (e.getStatus() == LoanableStatusEntity.loanable) {
                loanablesThatCanBeBorrowed.add(e);
            }
        }
        return loanablesThatCanBeBorrowed;
    }
    //Getter-Setters


    public ArrayList<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UsersEntity> users) {
        this.users = users;
    }

    public ArrayList<LoanablesEntity> getLoanables() {
        return loanables;
    }

    public void setLoanables(ArrayList<LoanablesEntity> loanables) {
        this.loanables = loanables;
    }

    public LoaningsEntity getEntity() {
        return entity;
    }

    public void setEntity(LoaningsEntity entity) {
        this.entity = entity;
    }

    public ArrayList<LoaningsEntity> getLoaningsEntities() {
        if (loaningsEntities == null) {
            loaningsEntities = service.getAllLoanings();
        }
        return loaningsEntities;
    }

    public ArrayList<LoaningsEntity> getFilteredLoaningsEntities() {
        return FilteredLoaningsEntities;
    }

    public void setFilteredLoaningsEntities(ArrayList<LoaningsEntity> filteredLoaningsEntities) {
        FilteredLoaningsEntities = filteredLoaningsEntities;
    }

    public void setLoaningsEntities(ArrayList<LoaningsEntity> loaningsEntities) {
        this.loaningsEntities = loaningsEntities;
    }

    public void setService(LoaningService service) {
        this.service = service;
    }
}
