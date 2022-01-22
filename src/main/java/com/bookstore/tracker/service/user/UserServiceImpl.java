package com.bookstore.tracker.service.user;

import com.bookstore.tracker.data.entity.User;
import com.bookstore.tracker.security.BookstoreUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This Class implements method call related only to User.
 */
@Service
@Lazy
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String DOT_COM_STRING = ".com";

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    /**
     * @see UserService#isNotBockedDomain(String)
     */
    @Override
    public boolean isNotBockedDomain(final String userName) {
        boolean isBlocked = true;

        if (!userName.isEmpty() && isEmailValid(userName)) {
            log.info("The inserted userName is: {}", userName);
            isBlocked = userName.toLowerCase().contains(DOT_COM_STRING);
        }

        return isBlocked;
    }

    /**
     * @see UserService#getLoggedUser()
     */
    @Override
    public User getLoggedUser() {
        if (null != SecurityContextHolder.getContext().getAuthentication()
                && !("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal()))) {
            final BookstoreUserPrincipal principal = (BookstoreUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return principal.getUser();
        } else {
            return null;
        }
    }

    private boolean isEmailValid(final String email) {
        final Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
