package beke.ire.dto;

import beke.ire.entity.UsersEntity;
import beke.ire.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UsersDTO {

    public void createNewUser(UsersEntity entity) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println("\n Crating new user : \n"+ entity.toString());
            session.save(entity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("\nCreating new user exception: \n " + e.getMessage());
        }
    }

    public ArrayList<UsersEntity> getAllUsers() {
        ArrayList<UsersEntity> list = new ArrayList<UsersEntity>();
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("Select u from UsersEntity u");
            list = (ArrayList<UsersEntity>) query.getResultList();
        }
        return list;
    }

    public UsersEntity getUserByName(UsersEntity entity) {
        UsersEntity user = new UsersEntity();
        List<UsersEntity> list = new ArrayList();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Query query = session.createQuery("Select u from UsersEntity u where u.full_name = : full_name");
            query.setParameter("full_name",entity.getFull_name());

            list = query.getResultList();
            user.setFull_name(list.get(0).getFull_name());
            user.setHome_address(list.get(0).getHome_address());
            user.setId_card_number(list.get(0).getId_card_number());
            user.setMobile_number(list.get(0).getMobile_number());
            user.setDeleted_user(list.get(0).isDeleted_user());

        }catch (Exception e){
            System.out.println("\n getUserByName exception: \n "+e.getMessage());
        }
        return user;
    }

    public UsersEntity getUserByIdCardNumber(UsersEntity entity) {
        UsersEntity user = new UsersEntity();
        List<UsersEntity> list = new ArrayList();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Query query = session.createQuery("Select u from UsersEntity u where u.mobile_number = : mobile_number");
            query.setParameter("mobile_number",entity.getMobile_number());

            list = query.getResultList();

            user.setId(list.get(0).getId());
            user.setFull_name(list.get(0).getFull_name());
            user.setHome_address(list.get(0).getHome_address());
            user.setId_card_number(list.get(0).getId_card_number());
            user.setMobile_number(list.get(0).getMobile_number());
            user.setDeleted_user(list.get(0).isDeleted_user());

        }catch (Exception e){
            System.out.println("\n getUserByIdCardNumber exception: \n "+e.getMessage());
        }
        return user;
    }

    public UsersEntity getUserById(UsersEntity entity) {
        UsersEntity user = new UsersEntity();
        List<UsersEntity> list = new ArrayList();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            list = session.createQuery("Select u from UsersEntity u where u.id = : id",UsersEntity.class).list();
            user.setFull_name(list.get(0).getFull_name());
            user.setHome_address(list.get(0).getHome_address());
            user.setId_card_number(list.get(0).getId_card_number());
            user.setMobile_number(list.get(0).getMobile_number());
            user.setDeleted_user(list.get(0).isDeleted_user());

        }catch (Exception e){
            System.out.println("\n getUserById exception: \n "+e.getMessage());
        }
        return user;
    }

    public void updateUser(UsersEntity entity) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println("\n Updating new user : \n"+entity.toString());
            session.update(entity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("\nUpdating new user exception: \n " + e.getMessage());
        }
    }

    public void deleteUser(UsersEntity entity) {
        //Deleting is just updating status in the database.
        System.out.println("Deleting user : "+ entity.getId() +" "+ entity.getFull_name());
        UsersEntity updateEntity = entity;
        updateEntity.setDeleted_user(true);
        updateUser(updateEntity);
        }
    }

