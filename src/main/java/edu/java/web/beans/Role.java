package edu.java.web.beans;

import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;

import javax.persistence.*;
import java.util.List;

/**
 * Created by edwin on 22/01/17.
 */

/*

create table Role
(
roleId bigint primary key auto_increment,
authority varchar(100) not null unique) auto_increment=1000000

create table user_roles
(
rolePtrId bigint,
userPtrId bigint)


 */

@Entity
@Table(name = "Role")
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long roleId;

    @Column(name = "authority")
    public String authority;




    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",
    joinColumns = {@JoinColumn(name="rolePtrId",referencedColumnName = "roleId")},
    inverseJoinColumns = {@JoinColumn(name="userPtrId", referencedColumnName = "userId")})
    public List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


}
