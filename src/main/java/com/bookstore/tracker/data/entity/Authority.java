package com.bookstore.tracker.data.entity;

import com.bookstore.tracker.helper.Auditable;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Entity
@Table(name = "AUTHORITY")
@Data
public class Authority extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "AUTH_GROUP")
    private String authGroup;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User assignedUser;

    public Authority() {
    }

    public Authority(long id, String authGroup, User assignedUser) {
        this.id = id;
        this.authGroup = authGroup;
        this.assignedUser = assignedUser;
    }

    public Authority(String authGroup, User assignedUser) {
        this.authGroup = authGroup;
        this.assignedUser = assignedUser;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authGroup='" + authGroup + '\'' +
                ", assignedUser=" + assignedUser +
                '}';
    }
}
