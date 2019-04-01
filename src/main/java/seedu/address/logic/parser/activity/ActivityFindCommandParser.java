package seedu.address.logic.parser.activity;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITYNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.ActivityFindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.ActivityContainsKeywordsPredicate;
import seedu.address.model.activity.ActivityDescriptionContainsKeywordsPredicate;
import seedu.address.model.activity.ActivityLocationContainsKeywordsPredicate;
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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACTIVITYNAME, PREFIX_LOCATION, PREFIX_ALL,
                        PREFIX_ADESCRIPTION);
        if (!isPrefixPresent(argMultimap, PREFIX_ACTIVITYNAME, PREFIX_LOCATION, PREFIX_ADESCRIPTION, PREFIX_ALL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFindCommand.MESSAGE_USAGE));
        }

        if (areMultiplePrefixesPresent(argMultimap, PREFIX_ACTIVITYNAME, PREFIX_LOCATION, PREFIX_ADESCRIPTION,
                PREFIX_ALL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ActivityFindCommand.MESSAGE_MULTIPLE_PREFIXES));
        }

        if (argMultimap.getValue(PREFIX_ALL).isPresent()) {
            String[] keywords = getKeyWords(argMultimap, PREFIX_ALL);
            return new ActivityFindCommand(new ActivityContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (argMultimap.getValue(PREFIX_ACTIVITYNAME).isPresent()) {
            String[] keywords = getKeyWords(argMultimap, PREFIX_ACTIVITYNAME);
            return new ActivityFindCommand(new ActivityNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (argMultimap.getValue(PREFIX_ADESCRIPTION).isPresent()) {
            String[] keywords = getKeyWords(argMultimap, PREFIX_ADESCRIPTION);
            return new ActivityFindCommand(new ActivityDescriptionContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        String[] keywords = getKeyWords(argMultimap, PREFIX_LOCATION);
        return new ActivityFindCommand(new ActivityLocationContainsKeywordsPredicate(Arrays.asList(keywords)));

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns the keywords for the given prefix
     */
    private static String[] getKeyWords (ArgumentMultimap argumentMultimap, Prefix prefix) throws ParseException {
        String arg = argumentMultimap.getValue(prefix).get();
        String trimmedArg = arg.trim();
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFindCommand.MESSAGE_USAGE));
        }
        return trimmedArg.split("\\s+");
    }

    /**
     * Returns true if there are more than one prefixes contain value {@code Optional} in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areMultiplePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return (Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count() > 1);
    }
}

