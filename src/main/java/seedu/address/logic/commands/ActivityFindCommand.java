package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.activity.ActivityNameContainsKeywordsPredicate;

/**
 * Finds and lists all activities in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ActivityFindCommand extends ActivityCommand {
    public static final String COMMAND_WORD = "activityFind";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all activities whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cohesion dance outing";

    private final ActivityNameContainsKeywordsPredicate predicate;

    public ActivityFindCommand(ActivityNameContainsKeywordsPredicate predicate) {
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
                || (other instanceof ActivityFindCommand // instanceof handles nulls
                && predicate.equals(((ActivityFindCommand) other).predicate)); // state check
    }
}
