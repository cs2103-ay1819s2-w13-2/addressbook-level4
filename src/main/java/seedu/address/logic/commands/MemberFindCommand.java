package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.parser.FindCriteriaContainsKeywordPredicate;
import seedu.address.model.Model;
import seedu.address.model.person.MatricNumberContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all members in Club Manager whose name and/or matric number contain any of the argument keywords.
 * Keyword matching is case insensitive and partial matching is available.
 */
public class MemberFindCommand extends MemberCommand {

    public static final String COMMAND_WORD = "memberFind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all members whose specified criteria "
            + "contain any of the specified keyword (case-insensitive) and displays them as a list with index "
            + "numbers.\n"
            + "Parameters: CRITERIA KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "name alice bob charlie"
            + "Example: " + COMMAND_WORD + "matricnum A012";

    private final FindCriteriaContainsKeywordPredicate predicate;
    private final NameContainsKeywordsPredicate namePredicate;
    private final MatricNumberContainsKeywordsPredicate matricNumPredicate;

    public MemberFindCommand(FindCriteriaContainsKeywordPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(predicate.getFindKeywords()));
        this.matricNumPredicate = new MatricNumberContainsKeywordsPredicate(Arrays.asList(predicate.getFindKeywords()));
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        if (predicate.toString().equalsIgnoreCase("name")) {
            model.updateFilteredPersonList(namePredicate);
        } else if (predicate.toString().equalsIgnoreCase("matricnum")) {
            model.updateFilteredPersonList(matricNumPredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MemberFindCommand // instanceof handles nulls
                && predicate.equals(((MemberFindCommand) other).predicate)); // state check
    }
}
