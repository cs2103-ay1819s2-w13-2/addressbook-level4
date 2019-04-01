package seedu.address.model.activity;

import java.util.function.Predicate;

/**
 * Tests that the DateTime of an {@code Activity} matches the condition given.
 */
public abstract class ActivityDateTimePredicate implements Predicate<Activity> {

    @Override
    public abstract boolean test(Activity activity);

}
