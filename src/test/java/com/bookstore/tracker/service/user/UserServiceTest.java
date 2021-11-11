package com.bookstore.tracker.service.user;

import com.bookstore.tracker.data.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final String ALLOWED_EMAIL = "beatrice@gmail.de";
    private static final String UNVALID_EMAIL = "beatrice.de";
    private static final String UNALLOWED_EMAIL = "dawa@gmail.COM";

    @Mock
    private UserService userServiceMock;

    @Mock
    private User userMock;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    void whenIsNotBockedDomain_withAllowedEmail_thenReturnFalse() {
        boolean isDomainBlocked = userService.isNotBockedDomain(ALLOWED_EMAIL);

        assertThat(isDomainBlocked, is(false));
    }

    @Test
    void whenIsNotBockedDomain_withUnValidEmail_thenReturnTrue() {
        boolean isDomainBlocked = userService.isNotBockedDomain(UNVALID_EMAIL);

        assertThat(isDomainBlocked, is(true));
    }

    @Test
    void whenIsNotBockedDomain_withUnAllowedEmail_thenReturnTrue() {
        boolean isDomainBlocked = userService.isNotBockedDomain(UNALLOWED_EMAIL);

        assertThat(isDomainBlocked, is(true));
    }

    @Test
    void whenGetLoggedUser_withLoggedUser_thenReturnUser() {
        // Prepare
        when(userServiceMock.getLoggedUser()).thenReturn(userMock);

        // Verify
        assertThat(userServiceMock.getLoggedUser(), is(userMock));

    }

    @Test
    void whenGetLoggedUser_withUnLoggedUser_thenReturnNull() {
        // Prepare
        when(userServiceMock.getLoggedUser()).thenReturn(null);

        // Verify
        assertThat(userServiceMock.getLoggedUser(), is(not(userMock)));
    }
}
