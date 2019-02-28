package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ModeCommand extends Command {

    public static final String COMMAND_WORD = "mode";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Get / sets the mode"
            + "Parameters:\n"
            + "if left blank the function returns current mode\n"
            + "if 0, changes mode to Member Management\n"
            + "if 1, changes mode to Activity Management";

    private final Integer modeToChange;

    public ModeCommand(Integer mode) {
        modeToChange = mode;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        if (modeToChange == null) {
            String modeText = (model.getMode() == 0 ? "MEMBER" : "ACTIVITY");
            return new CommandResult(String.format("Mode %s", modeText));
        } else if (modeToChange.equals(model.getMode())) {
            String modeText = (modeToChange.equals(0) ? "MEMBER" : "ACTIVITY");
            return new CommandResult(String.format("Mode is already: %s", modeText));
        } else {
            model.setMode(modeToChange);
            String modeText = (modeToChange.equals(0) ? "MEMBER" : "ACTIVITY");
            return new CommandResult(String.format("Mode changed to %s", modeText));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ModeCommand; // instanceof handles nulls
    }
}
