package seedu.address.logic.parser.activity;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITYNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ActivityFindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.ActivityContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new ActivityFindCommand object
 */
public class ActivityFindCommandParser implements Parser<ActivityFindCommand> {
    private static final ArrayList<Prefix> prefixes = new ArrayList<>(Arrays.asList(PREFIX_ACTIVITYNAME,
            PREFIX_ADESCRIPTION, PREFIX_LOCATION));

    /**
     * Parses the given {@code String} of arguments in the context of the ActivityFindCommand
     * and returns an ActivityFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ActivityFindCommand parse(String args) throws ParseException {

        List<Prefix> prefixesFound = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\s\\w+/").matcher(args);
        while (matcher.find()) {
            prefixesFound.add(new Prefix(matcher.group().trim()));
        }
        for (Prefix p: prefixesFound) {
            if (prefixes.stream().noneMatch(x -> x.equals(p))) {
                throw new ParseException(String
                        .format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFindCommand.MESSAGE_USAGE));
            }
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACTIVITYNAME, PREFIX_LOCATION, PREFIX_ADESCRIPTION);
        HashMap<Prefix, List<String>> activityFindKeywords = new HashMap<>();

        if (!isAnyPrefixPresent(argMultimap) && argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFindCommand.MESSAGE_USAGE));
        }

        if (!isAnyPrefixPresent(argMultimap)) {
            List<String> keywords = new ArrayList<>(Arrays.asList(argMultimap.getPreamble().split("\\s+")));
            activityFindKeywords.put(PREFIX_ALL, keywords);
        }

        for (Prefix p: prefixesFound) {
            List<String> keywords = getKeyWords(argMultimap, p);
            activityFindKeywords.put(p, keywords);
        }

        return new ActivityFindCommand(new ActivityContainsKeywordsPredicate(activityFindKeywords));

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap) {
        return prefixes.stream().anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns the keywords for the given prefix
     */
    private static List<String> getKeyWords (ArgumentMultimap argumentMultimap, Prefix prefix) throws ParseException {
        List<String> args = argumentMultimap.getAllValues(prefix);
        List<String> keywords = new ArrayList<>();
        for (String arg: args) {
            keywords.addAll(Arrays.asList(arg.trim().split("\\s+")));
        }
        if (keywords.isEmpty() || keywords.get(0).equals(new String(""))) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT, ActivityFindCommand.MESSAGE_USAGE));
        }
        return keywords;
    }

}


