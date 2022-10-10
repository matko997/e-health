package com.zavrsnirad.e_zdravlje.util;

import com.zavrsnirad.e_zdravlje.model.User;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  private static final Pattern ONLY_NUMBERS_REGEX = Pattern.compile("^[a-zA-Z0-9]*$");

  public static boolean isAlphaNumeric(String s) {
    return ONLY_NUMBERS_REGEX.matcher(s).find();
  }

  public static boolean isInvalidEmail(String email) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    return !matcher.find();
  }

  public static boolean isInUse(Optional<User> user) {
    return user.isPresent() && user.get().getEmail() != null && !user.get().getEmail().isEmpty();
  }

  public static boolean isInvalidJmbg(String jmbg) {
    return jmbg.length() != 13 || !isAlphaNumeric(jmbg);
  }

  public static boolean isInvalidPassword(String password) {
    return password.length() < 8;
  }
}
