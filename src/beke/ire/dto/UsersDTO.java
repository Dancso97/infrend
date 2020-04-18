package beke.ire.dto;

import beke.ire.entity.UsersEntity;
import beke.ire.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;

public class UsersDTO {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private ArrayList<UsersEntity> list = new ArrayList();

    public void createNewUser(UsersEntity entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Crating new user : \n" + entity.toString());
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nCreating new user exception: \n " + e.getMessage());
        }
    }

    public ArrayList<UsersEntity> getAllUsers() {
        if (!list.isEmpty()) {
            list.clear();
        }

        try {
            Query query = session.createQuery("Select u from UsersEntity u");
            list = (ArrayList<UsersEntity>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\n getAllUsers exception: \n " + e.getMessage());
        }
        return list;
    }

    public UsersEntity getUserById(int id) {
        if (!list.isEmpty()) {
            list.clear();
        }
        try {
            Query query = session.createQuery("Select u from UsersEntity u where u.id =: id");
            query.setParameter("id", id);
            list = (ArrayList<UsersEntity>) query.getResultList();
        } catch (Exception e) {
            System.out.println("\n getUserById exception: \n " + e.getMessage());
        }
        return list.get(0);
    }

    public void updateUser(UsersEntity entity) {
        try {
            transaction = session.beginTransaction();
            System.out.println("\n Updating new user : \n" + entity.toString());
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nUpdating new user exception: \n " + e.getMessage());
        }
    }

    public void deleteUser(UsersEntity entity) {
        System.out.println("Deleting user : " + entity.getId() + " " + entity.getFull_name());
        UsersEntity updateEntity = entity;
        updateEntity.setDeleted_user(true);
        updateUser(updateEntity);
    }
}

