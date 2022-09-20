package com.zavrsnirad.digitalhealth.util;

import com.zavrsnirad.digitalhealth.model.User;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static boolean validEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean isInUse(Optional<User> user) {
        return user.isPresent() && user.get().getEmail() != null && !user.get().getEmail().isEmpty();
    }
}
