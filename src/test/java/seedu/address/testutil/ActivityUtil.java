package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITYNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;

import seedu.address.logic.commands.ActivityAddCommand;
import seedu.address.model.activity.Activity;

/**
 * A utility class for Activity.
 */
public class ActivityUtil {

    /**
     * Returns an add command string for adding the {@code Activity}.
     */
    public static String getActivityAddCommand(Activity activity) {
        return ActivityAddCommand.COMMAND_WORD + " " + getActivityDetails(activity);
    }

    /**
     * Returns the part of command string for the given {@code activity}'s details.
     */
    public static String getActivityDetails(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ACTIVITYNAME + activity.getName().fullActivityName + " ");
        sb.append(PREFIX_DATETIME + activity.getDateTime().fullDateTime + " ");
        sb.append(PREFIX_LOCATION + activity.getLocation().value + " ");
        sb.append(PREFIX_ADESCRIPTION + activity.getDescription().value + " ");
        return sb.toString();
    }
}
