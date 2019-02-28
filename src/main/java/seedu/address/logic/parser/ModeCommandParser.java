package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ModeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ModeCommandParser implements Parser<ModeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ModeCommand parse(String args) throws ParseException {
        if (args.length() == 0) {
            return new ModeCommand(null);
        } else {
            String trimmedArgs = args.trim();
            if (trimmedArgs.matches("^[0-1]$")) {
                return new ModeCommand(Integer.parseInt(trimmedArgs));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModeCommand.MESSAGE_USAGE));
            }
        }
    }

}
