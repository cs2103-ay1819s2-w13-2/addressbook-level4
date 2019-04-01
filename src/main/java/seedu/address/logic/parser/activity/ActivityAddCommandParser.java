package seedu.address.logic.parser.activity;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITYNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;

import java.util.stream.Stream;

import seedu.address.logic.commands.ActivityAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.activity.Activity;
import seedu.address.model.activity.ActivityDateTime;
import seedu.address.model.activity.ActivityDescription;
import seedu.address.model.activity.ActivityLocation;
import seedu.address.model.activity.ActivityName;


/**
 * Parses input arguments and creates a new ActivityAddCommand object
 */
public class ActivityAddCommandParser implements Parser<ActivityAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ActivityAddCommand
     * and returns an ActivityAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ActivityAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACTIVITYNAME, PREFIX_DATETIME, PREFIX_LOCATION,
                        PREFIX_ADESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_ACTIVITYNAME, PREFIX_DATETIME, PREFIX_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityAddCommand.MESSAGE_USAGE));
        }

        ActivityName name = ParserUtil.parseActivityName(argMultimap.getValue(PREFIX_ACTIVITYNAME).get());
        ActivityDateTime datetime = ParserUtil.parseActivityDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        ActivityLocation location = ParserUtil.parseActivityLocation(argMultimap.getValue(PREFIX_LOCATION).get());

        if (argMultimap.getValue(PREFIX_ADESCRIPTION).isPresent()) {
            ActivityDescription description =
                    ParserUtil.parseActivityDescription(argMultimap.getValue(PREFIX_ADESCRIPTION).get());
            Activity activity = new Activity(name, datetime, location, description);

            return new ActivityAddCommand(activity);
        }

        Activity activity = new Activity(name, datetime, location);

        return new ActivityAddCommand(activity);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
