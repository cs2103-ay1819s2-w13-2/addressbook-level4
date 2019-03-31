package seedu.address.model.activity;

/**
 * Represents an Activity's Status in the address book.
 * Guarantees: immutable
 */
public class ActivityStatus {

    /**
     * Represents the possible status of ActivityStatus
     */
    public enum Status { ONGOING, COMPLETED }

    public final Status status;

    /**
     * Constructs a {@code ActivityStatus}.
     *
     * @param isPast a boolean about whether the activity time is in the past.
     */
    public ActivityStatus(boolean isPast) {
        if (isPast) {
            this.status = Status.COMPLETED;
        } else {
            this.status = Status.ONGOING;
        }
    }

    /**
     * Returns true if the status is completed
     */
    public static boolean isCompleted(ActivityStatus toCheck) {
        return (toCheck.status == Status.COMPLETED);
    }

    @Override
    public String toString() {
        return this.status.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityStatus // instanceof handles nulls
                && this.status.equals(((ActivityStatus) other).status)); //state check
    }

}
