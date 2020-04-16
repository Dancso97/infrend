package beke.ire.service;

import beke.ire.dto.LoanablesDTO;
import beke.ire.entity.LoanablesEntity;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name="loanablesService")
@ApplicationScoped
public class LoanablesService {

    private LoanablesDTO loanables = new LoanablesDTO();

    public List<LoanablesEntity> getAllLoanables(){ return loanables.getAllLoanable(); }

    public void createLoanable(LoanablesEntity entity){  loanables.createNewLoanable(entity); }

    public void updateLoanable(LoanablesEntity entity) { loanables.updateLoanable(entity);}

    public void deleteLoannable(LoanablesEntity entity) { loanables.deleteLoanable(entity);}
}
