package seedu.address.logic.parser.activity;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ActivityFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.ActivityNameContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new ActivityFindCommand object
 */
public class ActivityFindCommandParser implements Parser<ActivityFindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ActivityFindCommand
     * and returns an ActivityFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ActivityFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ActivityFindCommand(new ActivityNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}

