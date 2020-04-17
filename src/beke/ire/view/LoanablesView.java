package beke.ire.view;

import beke.ire.entity.LoanablesEntity;
import beke.ire.service.LoanablesService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("loanablesView")
@ViewScoped
public class LoanablesView implements Serializable {

    @Inject
    private LoanablesService service;

    private ArrayList<LoanablesEntity> loanables;
    private ArrayList<LoanablesEntity> filteredLoanables;

    public LoanablesView() {
        System.out.println("LoanablesView constructed");
    }

    @PostConstruct
    public void init() {
        System.out.println("LoanablesService injected : " + service);
        loanables = service.getAllLoanables();
    }


    //Getter-Setters

    public ArrayList<LoanablesEntity> getLoanables() {
        if(loanables == null){
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

    public void setService(LoanablesService service) {
        this.service = service;
    }
}
