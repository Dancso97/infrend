package beke.ire.dto;

import beke.ire.entity.LoaningsEntity;
import beke.ire.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;

import static beke.ire.entity.LoanableStatusEntity.loanable;

public class LoaningsDTO {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private ArrayList<LoaningsEntity> list = new ArrayList();

    public void createNewLoan(LoaningsEntity entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Crating new loan : \n" + entity.toString());
            session.update(entity.getWhat_borrowed());
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nCreating new loan exception: \n " + e.getMessage());
        }
    }

    public LoaningsEntity getLoanById(int id) {
        if (!list.isEmpty()) {
            list.clear();
        }

        try {
            System.out.println("Getting loan by id : " + id);
            Query query = session.createQuery("Select l from LoaningsEntity l where l.id = :id");
            query.setParameter("id", id);
            list = (ArrayList<LoaningsEntity>) query.getResultList();

        } catch (Exception e) {
            System.out.println("\n getLoanById exception: \n " + e.getMessage());
        }
        return list.get(0);
    }

    public void deleteLoan(LoaningsEntity entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Deleting loan : \n" + entity.toString());
            entity.getWhat_borrowed().setStatus(loanable);
            session.update(entity.getWhat_borrowed()); //Update loanable status to loanable from borrowed.
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nDeleting loan exception: \n " + e.getMessage());
        }

    }

    public ArrayList<LoaningsEntity> getAllLoanings() {
        if (!list.isEmpty()) {
            list.clear();
        }

        try {
            Query query = session.createQuery("Select l from LoaningsEntity l");
            list = (ArrayList<LoaningsEntity>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\ngetAllLoanings exception: \n " + e.getMessage());
        }

        return list;
    }

    public int getNumberOfLoans(int id) {
        int count = -1;
        try {
            Query query = session.createQuery("Select COUNT(who_borrowed)from LoaningsEntity l where l.id =: id");
            query.setParameter("id", id);
            count = query.getFirstResult();
        } catch (Exception e) {
            System.out.println("\ngetNumberOfLoans exception: \n " + e.getMessage());
        }
        return count;
    }
}

