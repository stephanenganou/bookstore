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
 */
@Service
@Lazy
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String POINT_COM_STRING = ".com";

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isNotBockedDomain(String userName) {
        boolean isBlocked = true;

        if (!userName.isEmpty() && isEmailValid(userName)) {
            log.info(userName);
            isBlocked = userName.toLowerCase().contains(POINT_COM_STRING);
        }

        return isBlocked;
    }

    @Override
    public User getLoggedUser() {
        if (null != SecurityContextHolder.getContext().getAuthentication()
                && !("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal()))) {
            BookstoreUserPrincipal principal = (BookstoreUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return principal.getUser();
        } else {
            return null;
        }
    }

    private boolean isEmailValid(final String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
