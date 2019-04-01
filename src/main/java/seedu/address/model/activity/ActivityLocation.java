package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Activity's Location
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class ActivityLocation {

    public static final String MESSAGE_CONSTRAINTS = "Location can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code ActivityLocation}.
     *
     * @param location A valid location.
     */
    public ActivityLocation(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        value = location;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityLocation // instanceof handles nulls
                && value.equalsIgnoreCase(((ActivityLocation) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
