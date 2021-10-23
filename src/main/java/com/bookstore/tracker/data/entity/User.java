package com.bookstore.tracker.data.entity;

import com.bookstore.tracker.helper.Auditable;

import javax.persistence.*;
import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Entity
@Table(name = "USER")
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "assignedUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> roles;

    public User() {
    }

    public User(long id, String userName, String firstName, String lastName, String password, List<Authority> roles) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }

    public User(String userName, String firstName, String lastName, String password, List<Authority> roles) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Authority> getRoles() {
        return roles;
    }

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
