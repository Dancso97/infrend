package beke.ire.service;

import beke.ire.dto.LoanablesDTO;
import beke.ire.entity.LoanablesEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class LoanablesService {

    private LoanablesDTO loanables = new LoanablesDTO();

    public LoanablesService() {
        System.out.println("LoanablesService constructed");
    }

    public ArrayList<LoanablesEntity> getAllLoanables() {
        return loanables.getAllLoanable();
    }

    public void createLoanable(LoanablesEntity entity) {
        loanables.createNewLoanable(entity);
    }

    public void updateLoanable(LoanablesEntity entity) {
        loanables.updateLoanable(entity);
    }

    public void deleteLoannable(LoanablesEntity entity) {
        loanables.deleteLoanable(entity);
    }
}
