package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AppMode;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ModeCommand extends GeneralCommand {

    public static final String COMMAND_WORD = "mode";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " : Get / Sets the mode\n"
            + "Parameters:\n"
            + "if left blank the function returns current mode\n"
            + "if MEMBER (case insensitive), changes mode to Member Management\n"
            + "if ACTIVITY (case insensitive), changes mode to Activity Management";

    public static final String MESSAGE_USAGE_CHANGE_MODE = COMMAND_WORD + " : Sets the mode\n"
            + "Parameters:\n"
            + "if MEMBER (case insensitive), changes mode to Member Management\n"
            + "if ACTIVITY (case insensitive), changes mode to Activity Management";

    public static final String MESSAGE_USAGE_CHECK_MODE = COMMAND_WORD + " : Get the mode\n";


    private final AppMode.Modes modeToChange;

    public ModeCommand(AppMode.Modes mode) {
        modeToChange = mode;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        if (modeToChange == null) {
            String modeText = model.getAddressBookMode().toString();
            return new CommandResult(String.format("Mode %s", modeText));
        } else if (modeToChange.equals(model.getAddressBookMode())) {
            String modeText = model.getAddressBookMode().toString();
            return new CommandResult(String.format("Mode is already: %s", modeText));
        } else {
            model.setAddressBookMode(modeToChange);
            return new CommandResult(String.format("Mode changed to %s",
                    model.getAddressBookMode().toString()),
                    false, false, true);
        }
    }
}
