package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ACTIVITIES;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Lists all activities in the club manager to the user.
 */
public class ActivityListCommand extends ActivityCommand {
    public static final String COMMAND_WORD = "activityList";

    public static final String MESSAGE_SUCCESS = "Listed all activities";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        model.updateFilteredActivityList(PREDICATE_SHOW_ALL_ACTIVITIES);

        model.commitAddressBook();

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
