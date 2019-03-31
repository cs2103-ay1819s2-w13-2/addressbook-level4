package seedu.address.testutil;

import seedu.address.model.activity.Activity;
import seedu.address.model.activity.ActivityDateTime;
import seedu.address.model.activity.ActivityDescription;
import seedu.address.model.activity.ActivityLocation;
import seedu.address.model.activity.ActivityName;

/**
 * A utility class to help with building Activity objects.
 */
public class ActivityBuilder {
    public static final String DEFAULT_NAME = "Laser Tag Outing";
    public static final String DEFAULT_DATETIME = "15/04/2019 1200";
    public static final String DEFAULT_LOCATION = "Bukit Gombak";
    public static final String DEFAULT_DESCRIPTION = "2 hour laser tag session at Bukit Gombak Home Team NS, $12/pax";

    private ActivityName name;
    private ActivityDateTime dateTime;
    private ActivityLocation location;
    private ActivityDescription description;

    public ActivityBuilder() {
        name = new ActivityName(DEFAULT_NAME);
        dateTime = new ActivityDateTime(DEFAULT_DATETIME);
        location = new ActivityLocation(DEFAULT_LOCATION);
        description = new ActivityDescription(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the ActivityBuilder with the data of {@code activityToCopy}.
     */
    public ActivityBuilder(Activity activityToCopy) {
        name = activityToCopy.getName();
        dateTime = activityToCopy.getDateTime();
        location = activityToCopy.getLocation();
        description = activityToCopy.getDescription();
    }

    /**
     * Sets the {@code ActivityName} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withActivityName(String name) {
        this.name = new ActivityName(name);
        return this;
    }

    /**
     * Sets the {@code ActivityDateTime} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withActivityDateTime(String dateTime) {
        this.dateTime = new ActivityDateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code ActivityLocation} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withActivityLocation(String location) {
        this.location = new ActivityLocation(location);
        return this;
    }

    /**
     * Sets the {@code ActivityDescription} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withActivityDescription(String description) {
        this.description = new ActivityDescription(description);
        return this;
    }

    /**
     * Sets the {@code ActivityDescription} of the {@code Activity} that we are building to the default value.
     */
    public ActivityBuilder withActivityDescription() {
        this.description = new ActivityDescription();
        return this;
    }

    public Activity build() {
        return new Activity(name, dateTime, location, description);
    }
}
