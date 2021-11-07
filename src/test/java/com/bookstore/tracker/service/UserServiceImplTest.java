package com.bookstore.tracker.service;

import com.bookstore.tracker.service.user.UserService;
import com.bookstore.tracker.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private static final String ALLOWED_EMAIL = "beatrice@gmail.de";
    private static final String UNVALID_EMAIL = "beatrice.de";
    private static final String UNALLOWED_EMAIL = "dawa@gmail.COM";

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    void whenIsNotBockedDomain_WithAllowedEmail_thenReturnFalse() {
        boolean isDomainBlocked = userService.isNotBockedDomain(ALLOWED_EMAIL);

        assertThat(isDomainBlocked, is(false));
    }

    @Test
    void whenIsNotBockedDomain_WithUnValidEmail_thenReturnTrue() {
        boolean isDomainBlocked = userService.isNotBockedDomain(UNVALID_EMAIL);

        assertThat(isDomainBlocked, is(true));
    }

    @Test
    void whenIsNotBockedDomain_WithUnallowedEmail_thenReturnTrue() {
        boolean isDomainBlocked = userService.isNotBockedDomain(UNALLOWED_EMAIL);

        assertThat(isDomainBlocked, is(true));
    }
}
