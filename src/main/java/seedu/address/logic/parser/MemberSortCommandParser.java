package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.MemberSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MemberSortCommand object
 */
public class MemberSortCommandParser implements Parser<MemberSortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MemberSortCommand
     * and returns an MemberSortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MemberSortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] sortCriteria = trimmedArgs.split("\\s+");
        if (trimmedArgs.isEmpty() || sortCriteria.length > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MemberSortCommand.MESSAGE_USAGE));
        }

        return new MemberSortCommand(new SortCriteriaContainsKeywordPredicate(trimmedArgs));
    }
}
