package beke.ire.service;

import beke.ire.dto.LoaningsDTO;
import beke.ire.entity.LoaningsEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name="loaningService")
@ApplicationScoped
public class LoaningService {

    private LoaningsDTO loanings = new LoaningsDTO();

    public List<LoaningsEntity> getAllLoanings() {
        return loanings.getAllLoanings();
    }

    public void createLoaning(LoaningsEntity entity){
        loanings.createNewLoan(entity);
    }

    public void updateLoaning(LoaningsEntity entity){
        loanings.updateLoan(entity);
    }

    public void deleteLoaning(LoaningsEntity entity){
        loanings.deleteLoan(entity);
    }

}
