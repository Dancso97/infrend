package beke.ire.dto;

import beke.ire.entity.LoanableStatusEntity;
import beke.ire.entity.LoanablesEntity;
import beke.ire.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;

public class LoanablesDTO {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private ArrayList<LoanablesEntity> list = new ArrayList();

    public void createNewLoanable(LoanablesEntity entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Crating new loanable : \n" + entity.toString());
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nCreating new loanable exception: \n " + e.getMessage());
        }
    }

    public LoanablesEntity getLoanableById(int id) {
        if (!list.isEmpty()) {
            list.clear();
        }

        try {
            Query query = session.createQuery("Select l from LoanablesEntity l where l.id =: id");
            query.setParameter("id", id);
            list = (ArrayList<LoanablesEntity>) query.getResultList();
        } catch (Exception e) {
            System.out.println("getLoanableById exception : " + e.getMessage());
        }
        return list.get(0);
    }

    public ArrayList<LoanablesEntity> getAllLoanable() {
        if (!list.isEmpty()) {
            list.clear();
        }
        try {
            Query query = session.createQuery("Select l from LoanablesEntity l");
            list = (ArrayList<LoanablesEntity>) query.getResultList();
        } catch (Exception e) {
            System.out.println("getAllLoanable exception : " + e.getMessage());
        }
        return list;
    }

    public void updateLoanable(LoanablesEntity entity) {
        try {
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

    public void deleteLoanable(LoanablesEntity entity) {
        System.out.println("Deleting loanable : " + entity.getId() + " " + entity.getTitle());
        LoanablesEntity updateEntity = entity;
        updateEntity.setStatus(LoanableStatusEntity.scrapped);
        updateLoanable(updateEntity);
    }
}
