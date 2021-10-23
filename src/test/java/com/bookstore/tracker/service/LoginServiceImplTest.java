package com.bookstore.tracker.service;

import com.bookstore.tracker.service.login.LoginService;
import com.bookstore.tracker.service.login.LoginServiceImpl;
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
public class LoginServiceImplTest {
    private static final String ALLOWED_EMAIL = "beatrice@gmail.de";
    private static final String UNVALID_EMAIL = "beatrice.de";
    private static final String UNALLOWED_EMAIL = "dawa@gmail.COM";
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginServiceImpl();
    }

    @Test
    void whenIsNotBockedDomain_WithAllowedEmail_thenReturnFalse() {
        boolean isDomainBlocked = loginService.isNotBockedDomain(ALLOWED_EMAIL);

        assertThat(isDomainBlocked, is(false));
    }

    @Test
    void whenIsNotBockedDomain_WithUnvalidEmail_thenReturnTrue() {
        boolean isDomainBlocked = loginService.isNotBockedDomain(UNVALID_EMAIL);

        assertThat(isDomainBlocked, is(true));
    }

    @Test
    void whenIsNotBockedDomain_WithUnallowedEmail_thenReturnTrue() {
        boolean isDomainBlocked = loginService.isNotBockedDomain(UNALLOWED_EMAIL);

        assertThat(isDomainBlocked, is(true));
    }
}
