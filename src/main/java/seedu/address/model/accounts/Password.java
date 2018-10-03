package seedu.address.model.accounts;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Represents an account password. Guarantees: immutable; is valid as declared in {@link #isValidPassword(String)}
 */
public class Password {

    public static final String MESSAGE_PASSWORD_CONSTRAINT =
            "Password should only contain alphanumeric characters and without spaces, and it should not be blank";

    /*
     * The first character of the password must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String PASSWORD_VALIDATION_REGEX = "[\\p{ASCII}&&[\\S]][\\p{ASCII}&&[\\S]]{1,20}";

    private final String password;

    /**
     * Constructs a {@code Password}.
     *
     * @param password A valid password.
     */
    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_PASSWORD_CONSTRAINT);
        //
        char[] bcryptHashBytes = BCrypt.withDefaults().hashToChar(6, password.toCharArray());
        this.password = Arrays.toString(bcryptHashBytes);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return password;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Password // instanceof handles nulls
                && password.equals(((Password) other).password)); // state check
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }
}
