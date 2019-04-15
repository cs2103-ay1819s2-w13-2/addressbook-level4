package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;
import seedu.address.model.person.MatricNumber;

/**
 * Selects an activity identified using it's displayed index from the address book.
 */
public class ActivityAddMemberCommand extends ActivityCommand {

    public static final String COMMAND_WORD = "activityAddMember";
    public static final String COMMAND_ALIAS = "aAddM";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the activity identified by the index number used in the displayed activity list and adds"
            + " the Person object with given matriculation number into its attendance list.\n"
            + "Parameters: INDEX (must be a positive integer) + MATRICNUMBER\n"
            + "Example: " + COMMAND_WORD + " 1" + " A1234567H";

    public static final String MESSAGE_ACTIVITY_ADD_MEMBER_SUCCESS = "Successfully added to selected Activity: %1$s";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "This activity already exists in the address book.";

    private final Index targetIndex;
    private final MatricNumber targetMatric;

    /**
     * @param targetIndex of the activity in the filtered activity list to edit
     * @param targetMatric of the member we want to add to the activity
     */
    public ActivityAddMemberCommand(Index targetIndex, MatricNumber targetMatric) {
        requireNonNull(targetIndex);
        requireNonNull(targetMatric);
        this.targetIndex = targetIndex;
        this.targetMatric = targetMatric;
    }



    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Activity> filteredActivityList = model.getFilteredActivityList();

        if (targetIndex.getZeroBased() >= filteredActivityList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        if (!model.hasMatricNumber(this.targetMatric)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_MATRIC_NUMBER);
        }

        model.setSelectedActivity(filteredActivityList.get(targetIndex.getZeroBased()));
        Activity selectedActivity = model.getSelectedActivity();

        if (selectedActivity.hasPersonInAttendance(targetMatric)) {
            throw new CommandException(Messages.MESSAGE_ACTIVITY_ALREADY_HAS_PERSON);
        }

        Activity copyActivity = Activity.addMemberToActivity(selectedActivity, targetMatric);


        if (!selectedActivity.isSameActivity(copyActivity) && model.hasActivity(copyActivity)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
        }

        model.setActivity(selectedActivity, copyActivity);
        model.updateFilteredActivityList(Model.PREDICATE_SHOW_ALL_ACTIVITIES);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_ACTIVITY_ADD_MEMBER_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityAddMemberCommand // instanceof handles nulls
                && targetIndex.equals(((ActivityAddMemberCommand) other).targetIndex)); // state check
    }
}
