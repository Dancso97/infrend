package beke.ire.dto;

import beke.ire.entity.LoaningsEntity;
import beke.ire.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static beke.ire.entity.LoanableStatusEntity.loanable;

public class LoaningsDTO {

    public void createNewLoan(LoaningsEntity entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("\n Crating new Loan : \n" + entity.toString());
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nCreating new Loan exception: \n " + e.getMessage());
        }
    }

    public LoaningsEntity getLoanById(int id) {
        List<LoaningsEntity> list = new ArrayList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query query = session.createQuery("Select l from LoaningsEntity l where l.id = :id");
            query.setParameter("id", id);

            list = query.getResultList();

        } catch (Exception e) {
            System.out.println("\n getLoanById exception: \n " + e.getMessage());
        }
        return list.get(0);
    }

    public void updateLoan(LoaningsEntity entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("\n Updating loanable : \n" + entity.toString());
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nUpdating loanable exception: \n " + e.getMessage());
        }
    }

    public void deleteLoan(LoaningsEntity entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("\n Deleting loanable : \n" + entity.toString());
            entity.getWhat_borrowed().setStatus(loanable);
            session.update(entity.getWhat_borrowed()); //Update loanable status to loanable from borrowed.
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nDeleting loanable exception: \n " + e.getMessage());
        }

    }

    public ArrayList<LoaningsEntity> getAllLoanings() {
        ArrayList<LoaningsEntity> list = new ArrayList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("Select l from LoaningsEntity l");
            list = (ArrayList<LoaningsEntity>) query.getResultList();
        }

        return list;
    }

    public int getNumberOfLoans(int id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("Select COUNT(who_borrowed)from LoaningsEntity l where l.id =: id");
            query.setParameter("id",id);
            int count = query.getFirstResult();

            return count;
        }
    }
}

