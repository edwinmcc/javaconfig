package edu.java.web.beans;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*

create table User
(
userId bigint primary key auto_increment,
username varchar(100),
password varchar(100),
enabled  tinyint(1) default 1,
creationDate bigint default -1,
userExpiryDate bigint default -1,
passwordExpiryDate bigint default -1,
userLocked tinyint(1) default 0
) auto_increment=1000000;

 */

/**
 * Created by edwin on 18/01/17.
 */
@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name="username",nullable = false, unique = true)
    private String username;
    @Column(name="password")
    private String password;

    @Column(name="enabled", columnDefinition = "BIT", length = 1)
    private boolean enabled;
    @Column(name="creationDate")
    @Type(type = "org.hibernate.type.LongType")
    private long    creationDate;
    @Column(name="userExpiryDate")
    @Type(type="org.hibernate.type.LongType")
    private long    userExpiryDate;
    @Column(name="passwordExpiryDate")
    @Type(type="org.hibernate.type.LongType")
    private long    passwordExpiryDate;
    @Column(name="userLocked",columnDefinition = "BIT", length = 1)
    private boolean userLocked=false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_roles",
    joinColumns = {@JoinColumn(name="userPtrId", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name="rolePtrId", referencedColumnName = "roleId")})
    public List<Role> rolesList = new ArrayList<Role>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Batch> batches = new ArrayList<Batch>();

    public User()
    {

    }

    public User(String username) {
        this.setUsername(username);
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getUserExpiryDate() {
        return userExpiryDate;
    }

    public void setUserExpiryDate(long userExpiryDate) {
        this.userExpiryDate = userExpiryDate;
    }

    public long getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(long passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    public boolean isUserLocked() {
        return userLocked;
    }

    public void setUserLocked(boolean userLocked) {
        this.userLocked = userLocked;
    }


    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList=rolesList;
    }


    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

}
