package beke.ire.service;

import beke.ire.dto.UsersDTO;
import beke.ire.entity.UsersEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean(eager = true)
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

}