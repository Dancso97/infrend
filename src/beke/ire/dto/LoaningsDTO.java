package beke.ire.dto;

import beke.ire.entity.LoaningsEntity;
import beke.ire.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LoaningsDTO {

    public void createNewLoan(LoaningsEntity entity) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println("\n Crating new Loan : \n"+ entity.toString());
            session.save(entity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("\nCreating new Loan exception: \n " + e.getMessage());
        }
    }

    public LoaningsEntity getLoanById(LoaningsEntity entity) {
        LoaningsEntity loan = new LoaningsEntity();
        List<LoaningsEntity> list = new ArrayList();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Query query = session.createQuery("Select l from LoaningsEntity l where l.id = :id");
            query.setParameter("id",entity.getId());

            list = query.getResultList();

            loan.setWhat_borrowed(list.get(0).getWhat_borrowed());
            loan.setWho_borrowed(list.get(0).getWho_borrowed());
            loan.setWhen_borrowed(list.get(0).getWhen_borrowed());
            loan.setWhen_got_back(list.get(0).getWhen_got_back());

            list.clear();

        }catch (Exception e){
            System.out.println("\n getLoanById exception: \n "+e.getMessage());
        }
        return loan;
    }

    public void updateLoan(LoaningsEntity entity) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println("\n Updating loanable : \n"+entity.toString());
            session.update(entity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("\nUpdating loanable exception: \n " + e.getMessage());
        }
    }

    public void deleteLoan(LoaningsEntity entity) {
        System.out.println("Deleting loan : " + entity.getId() +" " + entity.getWhen_borrowed());
        LoaningsEntity updateEntity = entity;
       //updateEntity.set

    }

    public List<LoaningsEntity> getAllLoanings() {
        List<LoaningsEntity> list = new ArrayList();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            list = session.createQuery("Select l from LoaningsEntity l",LoaningsEntity.class).list();
        }catch (Exception e){
            System.out.println("\n getAllLoanings exception: \n "+e.getMessage());
        }
        return list;
    }
}

