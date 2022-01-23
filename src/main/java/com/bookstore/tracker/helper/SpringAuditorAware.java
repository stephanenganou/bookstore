package com.bookstore.tracker.helper;

import com.bookstore.tracker.data.entity.User;
import com.bookstore.tracker.security.BookstoreUserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Optional;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * Class playing the role of Entitylistener for Classes extending the class Auditable.
 */
public class SpringAuditorAware implements AuditorAware<User> {

    /**
     * Method returning  an Optional<User> containing information about the logged user.
     */
    @Override
    public Optional<User> getCurrentAuditor() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.of(
                ((BookstoreUserPrincipal) authentication.getPrincipal()).getUser()
        );
    }

    @PrePersist
    public void prePersist(Object o) {

    }

    @PreUpdate
    public void preUpdate(Object o) {

    }

    @PreRemove
    public void preRemove(Object o) {

    }

    @PostLoad
    public void postLoad(Object o) {

    }

    @PostRemove
    public void postRemove(Object o) {

    }

    @PostUpdate
    public void postUpdate(Object o) {

    }

    @PostPersist
    public void postPersist(Object o) {

    }
}
