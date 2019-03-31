package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITYNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;

/**
 * Adds an activity to the club manager.
 */
public class ActivityAddCommand extends ActivityCommand {

    public static final String COMMAND_WORD = "activityAdd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an activity to the address book. "
            + "Parameters: "
            + PREFIX_ACTIVITYNAME + "ACTIVITY_NAME "
            + PREFIX_DATETIME + "ACTIVITY_DATETIME "
            + PREFIX_LOCATION + "ACTIVITY_LOCATION "
            + "[" + PREFIX_ADESCRIPTION + "ACTIVITY_DESCRIPTION] "
            + "[" + PREFIX_IC + "ACTIVITY_IC_MATRICNO]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ACTIVITYNAME + "CSS Workshop "
            + PREFIX_DATETIME + "12/03/2019 1900 "
            + PREFIX_LOCATION + "Com1 02-07 "
            + PREFIX_ADESCRIPTION + "Learn to write beautifully designed webapp. Bring Laptop. "
            + PREFIX_IC + "A0111111Z";

    public static final String MESSAGE_SUCCESS = "New activity added: %1$s";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "This activity already exists in Club Manager";


    private final Activity toAdd;

    /**
     * Creates an ActivityAddCommand to add the specified {@code Person}
     */
    public ActivityAddCommand(Activity activity) {
        requireNonNull(activity);
        toAdd = activity;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasActivity(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
        }

        model.addActivity(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityAddCommand // instanceof handles nulls
                && toAdd.equals(((ActivityAddCommand) other).toAdd));
    }

}
