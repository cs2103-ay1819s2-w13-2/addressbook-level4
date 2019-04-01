package seedu.address.model.activity;

import java.util.Calendar;
import java.util.Locale;

/**
 * Tests that the DateTime of an {@code Activity} is within x number of days before to now.
 */
public class ActivityDateTimeBeforePredicate extends ActivityDateTimePredicate {
    private final Calendar toCompare;
    private final Calendar now;

    public ActivityDateTimeBeforePredicate(int days) {
        Calendar now = Calendar.getInstance(new Locale("en", "SG"));
        now.add(Calendar.DAY_OF_YEAR, 0 - days);
        this.toCompare = now;
        this.now = Calendar.getInstance(new Locale("en", "SG"));
    }

    /**
     * Returns true if the date of the activity is within the range from toCompare to now.
     */
    @Override
    public boolean test(Activity activity) {
        return (activity.getDateTime().isAfter(toCompare) && activity.getDateTime().isBefore(now))
                || (activity.getDateTime().isSameDay(now) && activity.getCurrentStatus().isCompleted())
                || activity.getDateTime().isSameDay(toCompare);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityDateTimeBeforePredicate
                && now.equals(((ActivityDateTimeBeforePredicate) other).now)
                && toCompare.equals(((ActivityDateTimeBeforePredicate) other).toCompare));
    }
}
