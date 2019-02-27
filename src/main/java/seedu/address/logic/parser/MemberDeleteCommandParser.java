package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MemberDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MemberDeleteCommand object
 */
public class MemberDeleteCommandParser implements Parser<MemberDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MemberDeleteCommand
     * and returns an MemberDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MemberDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MemberDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MemberDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
