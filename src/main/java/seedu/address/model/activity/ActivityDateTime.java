package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Represents an Activity's Date and Time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidActivityDateTime(String)}
 */
public class ActivityDateTime implements Comparable<ActivityDateTime> {


    public static final String MESSAGE_CONSTRAINTS =
            "Dates and time should be in dd/MM/YYYY HHmm format separated by a blank between date and time. \n"
                    + "Date and month should have 2 digits and the year should be four digits. \n"
                    + "Time should be entered in 24 hr clock format. e.g. 1330 represents 1:30 pm. ";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String DATE_PART_REGEX = "^\\d{1,2}" + "/" + "\\d{1,2}" + "/" + "\\d{4}";
    private static final String TIME_PART_REGEX = "\\d{4}";
    private static final String VALIDATION_REGEX = DATE_PART_REGEX + " " + TIME_PART_REGEX;

    public final String fullDateTime;
    public final Calendar calendarDateTime;

    /**
     * Constructs a {@code ActivityDateTime}.
     *
     * @param dateTime A valid datetime.
     */
    public ActivityDateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidActivityDateTime(dateTime), MESSAGE_CONSTRAINTS);
        fullDateTime = dateTime;
        calendarDateTime = getCalendarTime(dateTime);
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidActivityDateTime(String test) {

        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        List<String> dateTimeParts = Arrays.asList(test.split(" "));
        String datePart = dateTimeParts.get(0);
        String timePart = dateTimeParts.get(1);
        return isValidDate(datePart) && isValidTime(timePart);
    }

    /**
     * Returns true if a given date is a valid date.
     */
    private static boolean isValidDate(String date) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        format.setLenient(false);
        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given time is a valid time.
     */
    private static boolean isValidTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        return !(hour > 23 || minute > 59);
    }

    /**
     * Returns the calendar date and time from the input
     */
    private static Calendar getCalendarTime(String datetime) {
        Calendar cal = Calendar.getInstance(new Locale("en", "SG"));
        List<String> dateTimeParts = Arrays.asList(datetime.split(" "));
        String datePart = dateTimeParts.get(0);
        String timePart = dateTimeParts.get(1);
        int day = Integer.parseInt(datePart.substring(0, 2));
        int month = Integer.parseInt(datePart.substring(3, 5));
        int year = Integer.parseInt(datePart.substring(6, 10));
        int hour = Integer.parseInt(timePart.substring(0, 2));
        int minute = Integer.parseInt(timePart.substring(2));
        cal.set(year, month - 1, day, hour, minute, 00);
        return cal;
    }

    /**
     * Returns true if a given time is before the current date and time
     */
    public static boolean isPast(ActivityDateTime toCompare) {
        Calendar activityTime = toCompare.calendarDateTime;
        Calendar now = Calendar.getInstance(new Locale("en", "SG"));
        return activityTime.before(now);
    }


    @Override
    public String toString() {
        return fullDateTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityDateTime // instanceof handles nulls
                && fullDateTime.equals(((ActivityDateTime) other).fullDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return fullDateTime.hashCode();
    }

    @Override
    public int compareTo(ActivityDateTime other) {
        return this.calendarDateTime.compareTo(other.calendarDateTime);
    }
}
