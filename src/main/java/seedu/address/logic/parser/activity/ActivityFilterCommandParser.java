package seedu.address.logic.parser.activity;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.CONDITION_NEGATIVE;
import static seedu.address.logic.parser.CliSyntax.CONDITION_POSITIVE;

import seedu.address.logic.commands.ActivityFilterCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.ActivityDateTimeAfterPredicate;
import seedu.address.model.activity.ActivityDateTimeBeforePredicate;

/**
 * Parses input arguments and creates a new ActivityFilterCommand object
 */
public class ActivityFilterCommandParser implements Parser<ActivityFilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ActivityFindCommand
     * and returns an ActivityFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ActivityFilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFilterCommand.MESSAGE_USAGE));
        }
        String[] input = trimmedArgs.split(" ");
        if (input.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFilterCommand.MESSAGE_USAGE));
        }
        String cond = input[0];

        Integer days;
        try {
            days = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFilterCommand.MESSAGE_INCORECT_DAYS));
        }

        switch(cond) {
        case CONDITION_POSITIVE:
            return new ActivityFilterCommand(new ActivityDateTimeAfterPredicate(days));

        case CONDITION_NEGATIVE:
            return new ActivityFilterCommand(new ActivityDateTimeBeforePredicate(days));

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFilterCommand.MESSAGE_NO_CONDITION));
        }
    }
}
