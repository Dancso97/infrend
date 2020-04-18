package beke.ire.service;

import beke.ire.dto.UsersDTO;
import beke.ire.entity.UsersEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class UserService {

    private UsersDTO usersDto = new UsersDTO();

    public UserService() {
        System.out.println("UserService constructed");
    }

    public ArrayList<UsersEntity> getAllUsers() {
        return usersDto.getAllUsers();
    }

    public void createUser(UsersEntity entity) {
        usersDto.createNewUser(entity);
    }

    public void updateUser(UsersEntity entity) {
        usersDto.updateUser(entity);
    }

    public void deleteUser(UsersEntity entity) {
        usersDto.deleteUser(entity);
    }

    public UsersEntity getUserById(int id) {
        return usersDto.getUserById(id);
    }

}