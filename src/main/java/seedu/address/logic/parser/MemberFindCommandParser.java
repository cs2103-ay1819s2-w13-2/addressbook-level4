package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.MemberFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new MemberFindCommand object
 */
public class MemberFindCommandParser implements Parser<MemberFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MemberFindCommand
     * and returns an MemberFindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public MemberFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MemberFindCommand.MESSAGE_USAGE));
        }

        return new MemberFindCommand(new FindCriteriaContainsKeywordPredicate(trimmedArgs));

    }
}
