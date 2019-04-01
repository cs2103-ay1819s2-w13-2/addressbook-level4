package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITYNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;

/**
 * Finds and lists all activities in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ActivityFindCommand extends ActivityCommand {
    public static final String COMMAND_WORD = "activityFind";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all activities whose attribute contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "The attributes that activityFind search for is specified by the prefix input by the user.\n"
            + "Parameters: PREFIX KEYWORD [MORE_KEYWORDS]...\n"
            + "The provided PREFIX includes " + PREFIX_ALL + " (all attributes), "
            + PREFIX_ACTIVITYNAME + " (ActivityName), " + PREFIX_ADESCRIPTION + " (ActivityDescription) and "
            + PREFIX_LOCATION + " (ActivityLocation)." + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ALL + " cohesion dance outing";
    public static final String MESSAGE_MULTIPLE_PREFIXES = "Only one prefix at a time is allowed.";

    private final Predicate<Activity> predicate;

    public ActivityFindCommand(Predicate<Activity> predicate) {
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
