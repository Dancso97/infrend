package beke.ire.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "beke_library")
public class UsersEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic //for not null constraint
    @Column(name = "full_name", length = 50)
    private String full_name;

    @Basic
    @Column(name = "mobile_number", length = 11)
    private String mobile_number;

    @Basic
    @Column(name = "id_card_number", length = 8)
    private String id_card_number;

    @Basic
    @Column(name = "home_address", length = 250)
    private String home_address;

    @Basic
    @Column(name = "deleted_user")
    private boolean deleted_user;

    public UsersEntity() {
    }

    public UsersEntity(String full_name, String mobile_number, String id_card_number, String home_address, boolean deleted_user) {
        this.full_name = full_name;
        this.mobile_number = mobile_number;
        this.id_card_number = id_card_number;
        this.home_address = home_address;
        this.deleted_user = deleted_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public boolean isDeleted_user() {
        return deleted_user;
    }

    public void setDeleted_user(boolean deleted_user) {
        this.deleted_user = deleted_user;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", mobile_number=" + mobile_number +
                ", id_card_number=" + id_card_number +
                ", home_address='" + home_address + '\'' +
                ", is_deleted_user=" + deleted_user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                mobile_number == that.mobile_number &&
                id_card_number == that.id_card_number &&
                deleted_user == that.deleted_user &&
                full_name.equals(that.full_name) &&
                home_address.equals(that.home_address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, full_name, mobile_number, id_card_number, home_address, deleted_user);
    }
}
