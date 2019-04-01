package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.activity.ActivityDateTimePredicate;


/**
 * Finds and lists all activities in address book whose date is in the filter range
 */
public class ActivityFilterCommand extends ActivityCommand {
    public static final String COMMAND_WORD = "activityFilter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " ：Filters the activities that is the given "
            + "number of days away from today (inclusive) and displays them as a list with index numbers.\n "
            + "Filter conditions: + is to filter future activities and - is to filter activities.\n"
            + "Parameters: CONDITION NUMBEROFDAYS \n"
            + "Example: " + COMMAND_WORD + " + 100";
    public static final String MESSAGE_INCORECT_DAYS = "The NUMBEROFDAYS input must be integers.";
    public static final String MESSAGE_NO_CONDITION = "Filter condition must be specified by + or -";

    public final ActivityDateTimePredicate predicate;

    public ActivityFilterCommand(ActivityDateTimePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredActivityList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW, model.getFilteredActivityList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityFilterCommand // instanceof handles nulls
                && predicate.equals(((ActivityFilterCommand) other).predicate)); // state check
    }
}
