package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ActivityAddCommand;
import seedu.address.logic.commands.ActivityDeleteCommand;
import seedu.address.logic.commands.ActivityFindCommand;
import seedu.address.logic.commands.ActivityListCommand;
import seedu.address.logic.commands.ActivitySelectCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.MemberAddCommand;
import seedu.address.logic.commands.MemberDeleteCommand;
import seedu.address.logic.commands.MemberEditCommand;
import seedu.address.logic.commands.MemberExportCommand;
import seedu.address.logic.commands.MemberFindCommand;
import seedu.address.logic.commands.MemberListCommand;
import seedu.address.logic.commands.MemberSelectCommand;
import seedu.address.logic.commands.MemberSortCommand;
import seedu.address.logic.commands.ModeCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.activity.ActivityAddCommandParser;
import seedu.address.logic.parser.activity.ActivityDeleteCommandParser;
import seedu.address.logic.parser.activity.ActivityFindCommandParser;
import seedu.address.logic.parser.activity.ActivitySelectCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ModeCommand.COMMAND_WORD:
            return new ModeCommandParser().parse(arguments);

        case MemberAddCommand.COMMAND_WORD:
            return new MemberAddCommandParser().parse(arguments);

        case MemberEditCommand.COMMAND_WORD:
            return new MemberEditCommandParser().parse(arguments);

        case MemberSelectCommand.COMMAND_WORD:
            return new MemberSelectCommandParser().parse(arguments);

        case MemberDeleteCommand.COMMAND_WORD:
            return new MemberDeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case MemberFindCommand.COMMAND_WORD:
            return new MemberFindCommandParser().parse(arguments);

        case MemberSortCommand.COMMAND_WORD:
            return new MemberSortCommandParser().parse(arguments);

        case MemberListCommand.COMMAND_WORD:
            return new MemberListCommand();

        case MemberExportCommand.COMMAND_WORD:
            return new MemberExportCommandParser().parse(arguments);

        case ActivityAddCommand.COMMAND_WORD:
            return new ActivityAddCommandParser().parse(arguments);

        case ActivityFindCommand.COMMAND_WORD:
            return new ActivityFindCommandParser().parse(arguments);

        case ActivitySelectCommand.COMMAND_WORD:
            return new ActivitySelectCommandParser().parse(arguments);

        case ActivityDeleteCommand.COMMAND_WORD:
            return new ActivityDeleteCommandParser().parse(arguments);

        case ActivityListCommand.COMMAND_WORD:
            return new ActivityListCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
