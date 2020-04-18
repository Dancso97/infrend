package beke.ire.service;

import beke.ire.dto.LoaningsDTO;
import beke.ire.dto.UsersDTO;
import beke.ire.entity.LoaningsEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static beke.ire.entity.LoanableStatusEntity.borrowed;
import static beke.ire.entity.LoanableStatusEntity.loanable;

@Named
@ApplicationScoped
public class LoaningService {

    private final int lateDayLimit = 30;
    private LoaningsDTO loanings = new LoaningsDTO();

    public LoaningService() {
        System.out.println("LoaningService constructed");
    }

    public ArrayList<LoaningsEntity> getAllLoanings() {
        ArrayList<LoaningsEntity> temp = loanings.getAllLoanings();

        for (LoaningsEntity temp1 : temp) {
            temp1.setLoaned_days(calculateDateDiff(temp1.getWhen_borrowed(), temp1.getWhen_got_back()));
            if (temp1.getLoaned_days() > lateDayLimit) {
                temp1.setLate(true);
                temp1.setLate_borrowing_days(temp1.getLoaned_days() - lateDayLimit);
            }
        }

        return temp;
    }

    public void createLoaning(LoaningsEntity entity) {
        Date date = new Date();
        entity.getWhat_borrowed().setStatus(borrowed);
        entity.setWhen_borrowed(new Timestamp(date.getTime()));
        loanings.createNewLoan(entity);
    }

    public void updateLoaning(LoaningsEntity entity) {
        loanings.updateLoan(entity);
    }

    public void deleteLoaning(int id) {
        Date date = new Date();
        LoaningsEntity loaning = loanings.getLoanById(id);
        loaning.setWhen_got_back(new Timestamp(date.getTime()));
        loaning.getWhat_borrowed().setStatus(loanable);
        loanings.deleteLoan(loaning);
    }

    public int calculateDateDiff(Timestamp date1, Timestamp date2) {
        if (date2 == null) {
            Date date = new Date();
            Timestamp timestamp1 = new Timestamp(date.getTime());
            date2 = timestamp1;
        }
        long difference = date2.getTime() - date1.getTime();
        int seconds = (int) difference / 1000;
        int days = (seconds / 3600) / 24;
        return days;
    }

    public int getNumberOfLoans(int id) {
        return loanings.getNumberOfLoans(id);
    }

}
