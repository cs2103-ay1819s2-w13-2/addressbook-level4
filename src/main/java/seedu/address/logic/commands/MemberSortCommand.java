package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

public class MemberSortCommand {
    public static final String COMMAND_WORD = "memberSort";

    public static final String MESSAGE_SUCCESS = "List sorted!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the member list based on name (first priority)."
            + "If the names are the same, sort by email address (2nd priority)."
            + "The specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
}
