package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_DATETIME_HTML;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_DATETIME_OUTING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_DESCRIPTION_OUTING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_LOCATION_HTML;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_LOCATION_OUTING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_NAME_HTML;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_NAME_OUTING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.activity.Activity;

/**
 * A utility class containing a list of {@code Activity} objects to be used in tests.
 */
public class TypicalActivities {

    public static final Activity AI = new ActivityBuilder().withActivityName("AI Announcement")
            .withActivityDateTime("05/05/2019 1600").withActivityLocation("LT 19")
            .withActivityDescription("Guest speakers invited to share recent development").build();
    public static final Activity BEGINNER = new ActivityBuilder().withActivityName("Beginner Briefing")
            .withActivityDateTime("06/05/2019 1200").withActivityLocation("Biz Library")
            .withActivityDescription("Briefing beginners about club details").build();
    public static final Activity COHESION = new ActivityBuilder().withActivityName("Coffee Cohesion")
            .withActivityDateTime("07/05/2019 0905").withActivityLocation("Starbucks Cafe")
            .withActivityDescription("One venti coffee on the house.").build();
    public static final Activity DINNER = new ActivityBuilder().withActivityName("Dinner and Dance")
            .withActivityDateTime("03/03/2018 1920").withActivityLocation("Utown Dance Room")
            .withActivityDescription("Spring dance for members.").build();
    public static final Activity ECOMMERCE = new ActivityBuilder().withActivityName("Ecommerce Exploration")
            .withActivityDateTime("05/03/2018 1530").withActivityLocation("SoC Hangout")
            .withActivityDescription("How to build a leading Ecommerce platform like Taobao").build();
    public static final Activity FUN = new ActivityBuilder().withActivityName("Fun Fact Factory")
            .withActivityDateTime("09/03/2018 1859").withActivityLocation("Com1 Tutorial Room 0210")
            .withActivityDescription("Share less known facts with friends").build();

    // Manually added
    public static final Activity GETAWAY = new ActivityBuilder().withActivityName("Gallery Getaway")
            .withActivityDateTime("08/05/2019 0820").withActivityLocation("National Gallery")
            .withActivityDescription().build();
    public static final Activity HAPPY = new ActivityBuilder().withActivityName("Happy Hour")
            .withActivityDateTime("06/06/2018 2320").withActivityLocation("Clarke Quay")
            .withActivityDescription("Adults only").build();

    // Manually added - Activity's details found in {@code CommandTestUtil}
    public static final Activity HTML = new ActivityBuilder().withActivityName(VALID_ACTIVITY_NAME_HTML)
            .withActivityDateTime(VALID_ACTIVITY_DATETIME_HTML).withActivityLocation(VALID_ACTIVITY_LOCATION_HTML)
            .withActivityDescription().build();
    public static final Activity OUTING = new ActivityBuilder().withActivityName(VALID_ACTIVITY_NAME_OUTING)
            .withActivityDateTime(VALID_ACTIVITY_DATETIME_OUTING).withActivityLocation(VALID_ACTIVITY_LOCATION_OUTING)
            .withActivityDescription(VALID_ACTIVITY_DESCRIPTION_OUTING).build();

    private TypicalActivities() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical activities.
     */
    public static AddressBook getTypicalAddressBookWithActivities() {
        AddressBook ab = new AddressBook();
        for (Activity activity : getTypicalActivities()) {
            ab.addActivity(activity);
        }
        return ab;
    }

    public static List<Activity> getTypicalActivities() {
        return new ArrayList<>(Arrays.asList(AI, BEGINNER, COHESION, DINNER, ECOMMERCE, FUN));
    }
}
