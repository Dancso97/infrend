package beke.ire.dto;

import beke.ire.entity.LoanableStatusEntity;
import beke.ire.entity.LoanablesEntity;
import beke.ire.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LoanablesDTO {

    public void createNewLoanable(LoanablesEntity entity) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println("\n Crating new Loanable : \n"+ entity.toString());
            session.save(entity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("\nCreating new Loanable exception: \n " + e.getMessage());
        }
    }

    public LoanablesEntity getLoanableByTitle(LoanablesEntity entity) {
        LoanablesEntity loanable = new LoanablesEntity();
        List<LoanablesEntity> list = new ArrayList();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Query query = session.createQuery("Select l from LoanablesEntity l where l.title = :title");
            query.setParameter("title",entity.getTitle());

            list = query.getResultList();

            loanable.setAuthor(list.get(0).getAuthor());
            loanable.setTitle(list.get(0).getTitle());
            loanable.setSupplied_date(list.get(0).getSupplied_date());
            loanable.setType(list.get(0).getType());
            loanable.setStatus(list.get(0).getStatus());

            list.clear();

        }catch (Exception e){
            System.out.println("\n getLoanableByTitle exception: \n "+e.getMessage());
        }
        return loanable;
    }

    public LoanablesEntity getLoanableByAuthor(LoanablesEntity entity) {
        LoanablesEntity loanable = new LoanablesEntity();
        List<LoanablesEntity> list = new ArrayList();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Query query = session.createQuery("Select l from LoanablesEntity l where l.author = :author");
            query.setParameter("author",entity.getAuthor());

            list = query.getResultList();

            loanable.setAuthor(list.get(0).getAuthor());
            loanable.setTitle(list.get(0).getTitle());
            loanable.setSupplied_date(list.get(0).getSupplied_date());
            loanable.setType(list.get(0).getType());
            loanable.setStatus(list.get(0).getStatus());

            list.clear();

        }catch (Exception e){
            System.out.println("\n getLoanableByAuthor exception: \n "+e.getMessage());
        }
        return loanable;
    }

    public List<LoanablesEntity> getAllLoanable() {
        List<LoanablesEntity> list = new ArrayList();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            list = session.createQuery("Select l from LoanablesEntity l",LoanablesEntity.class).list();
        }catch (Exception e){
            System.out.println("\n getAllLoanable exception: \n "+e.getMessage());
        }
        return list;
    }

    public void updateLoanable(LoanablesEntity entity) {
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

    public void deleteLoanable(LoanablesEntity entity) {
        System.out.println("Deleting loanable : "+ entity.getId() +" "+ entity.getTitle());
        LoanablesEntity updateEntity = entity;
        updateEntity.setStatus(LoanableStatusEntity.scrapped);
        updateLoanable(updateEntity);
    }
}
