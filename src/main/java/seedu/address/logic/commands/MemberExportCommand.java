package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.exportutil.ExportUtil;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Exports a person identified using it's displayed index from the address book.
 */
public class MemberExportCommand extends Command {

    public static final String COMMAND_WORD = "memberExport";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_PERSON_SUCCESS = "Exported Person: %1$s";

    private final Index targetIndex;

    public MemberExportCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Person> filteredPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= filteredPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person exportPerson = model.generateExportedPerson(filteredPersonList.get(targetIndex.getZeroBased()));
        ExportUtil.exportPersonToFile(exportPerson);


        return new CommandResult(String.format(MESSAGE_SELECT_PERSON_SUCCESS, targetIndex.getOneBased()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MemberExportCommand // instanceof handles nulls
                && targetIndex.equals(((MemberExportCommand) other).targetIndex)); // state check
    }
}
