package beke.ire.view;

import beke.ire.entity.LoanableStatusEntity;
import beke.ire.entity.LoanableTypeEntity;
import beke.ire.entity.LoanablesEntity;
import beke.ire.entity.LoaningsEntity;
import beke.ire.service.LoanablesService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

@Named("loanablesView")
@ViewScoped
public class LoanablesView implements Serializable {

    @Inject
    private LoanablesService service;

    private ArrayList<LoanablesEntity> loanables;
    private ArrayList<LoanablesEntity> filteredLoanables;

    private LoanablesEntity newLoanable = new LoanablesEntity();

    public LoanablesView() {
        System.out.println("LoanablesView constructed");
    }

    @PostConstruct
    public void init() {
        System.out.println("LoanablesService injected : " + service);
        loanables = service.getAllLoanables();
    }


    public String createNewLoanable() {
        System.out.println(newLoanable.toString());
        Date date = new Date();
        newLoanable.setSupplied_date(new Timestamp(date.getTime()));
        service.createLoanable(newLoanable);
        newLoanable = new LoanablesEntity();
        addMessage("Új anyag elmentve");
        return "loanables?faces-redirect=true\"";
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //Getter-Setters
    public ArrayList<LoanablesEntity> getLoanables() {
        if (loanables == null) {
            loanables = service.getAllLoanables();
        }
        return loanables;
    }

    public void setLoanables(ArrayList<LoanablesEntity> loanables) {
        this.loanables = loanables;
    }

    public ArrayList<LoanablesEntity> getFilteredLoanables() {
        return filteredLoanables;
    }

    public void setFilteredLoanables(ArrayList<LoanablesEntity> filteredLoanables) {
        this.filteredLoanables = filteredLoanables;
    }


    public ArrayList<LoanableTypeEntity> getLoanableTypeEntityList() {
        ArrayList<LoanableTypeEntity> values = new ArrayList<LoanableTypeEntity>(EnumSet.allOf(LoanableTypeEntity.class));
        return values;
    }

    public ArrayList<LoanableStatusEntity> getLoanableStatusEntityList() {
        ArrayList<LoanableStatusEntity> values = new ArrayList<LoanableStatusEntity>(EnumSet.allOf(LoanableStatusEntity.class));
        return values;
    }

    public LoanablesEntity getNewLoanable() {
        return newLoanable;
    }

    public void setNewLoanable(LoanablesEntity newLoanable) {
        this.newLoanable = newLoanable;
    }

    public void setService(LoanablesService service) {
        this.service = service;
    }
}
