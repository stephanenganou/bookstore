package com.bookstore.tracker.service.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
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
public class LoginServiceImpl implements LoginService {

    private final static String POINT_COM_STRING = ".com";

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isNotBockedDomain(String userName) {
        boolean isBlocked = true;

        if (!userName.isEmpty() && isEmailValid(userName)) {
            log.info(userName);
            isBlocked = userName.toLowerCase().contains(POINT_COM_STRING);
        }

        return isBlocked;
    }

    private boolean isEmailValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
