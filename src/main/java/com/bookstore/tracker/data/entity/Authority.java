package com.bookstore.tracker.data.entity;

import com.bookstore.tracker.helper.Auditable;

import javax.persistence.*;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Entity
@Table(name = "AUTHORITY")
public class Authority extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "AUTH_GROUP")
    private String authGroup;

    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User assignedUser;

    public void setId(long id) {
        this.id = id;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }
}
