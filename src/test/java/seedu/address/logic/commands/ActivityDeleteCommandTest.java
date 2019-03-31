package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showActivityAtIndex;
import static seedu.address.testutil.TypicalActivities.getTypicalAddressBookWithActivities;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ACTIVITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ACTIVITY;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.activity.Activity;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code ActivityDeleteCommand}.
 */
public class ActivityDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithActivities(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    /**
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Activity activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST_ACTIVITY.getZeroBased());
        ActivityDeleteCommand activityDeleteCommand = new ActivityDeleteCommand(INDEX_FIRST_ACTIVITY);

        String expectedMessage = String.format(ActivityDeleteCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteActivity(activityToDelete);
        expectedModel.commitAddressBook();

        assertCommandSuccess(activityDeleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }
    */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        ActivityDeleteCommand activityDeleteCommand = new ActivityDeleteCommand(outOfBoundIndex);

        assertCommandFailure(activityDeleteCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }
    /**
    @Test
    public void execute_validIndexFilteredList_success() {
        showActivityAtIndex(model, INDEX_FIRST_ACTIVITY);

        Activity activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST_ACTIVITY.getZeroBased());
        ActivityDeleteCommand activityDeleteCommand = new ActivityDeleteCommand(INDEX_FIRST_ACTIVITY);

        String expectedMessage = String.format(ActivityDeleteCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteActivity(activityToDelete);
        expectedModel.commitAddressBook();
        showNoActivity(expectedModel);

        assertCommandSuccess(activityDeleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showActivityAtIndex(model, INDEX_FIRST_ACTIVITY);

        Index outOfBoundIndex = INDEX_SECOND_ACTIVITY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getActivityList().size());

        ActivityDeleteCommand activityDeleteCommand = new ActivityDeleteCommand(outOfBoundIndex);

        assertCommandFailure(activityDeleteCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Activity activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST_ACTIVITY.getZeroBased());
        ActivityDeleteCommand activityDeleteCommand = new ActivityDeleteCommand(INDEX_FIRST_ACTIVITY);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteActivity(activityToDelete);
        expectedModel.commitAddressBook();

        // delete -> first activity deleted
        activityDeleteCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered activity list to show all activities
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first person deleted again
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
    */

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        ActivityDeleteCommand activityDeleteCommand = new ActivityDeleteCommand(outOfBoundIndex);

        // execution failed -> address book state not added into model
        assertCommandFailure(activityDeleteCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes an {@code Activity} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted activity in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the activity object regardless of indexing.
     */
    /**
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameActivityDeleted() throws Exception {
        ActivityDeleteCommand activityDeleteCommand = new ActivityDeleteCommand(INDEX_FIRST_ACTIVITY);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showActivityAtIndex(model, INDEX_SECOND_ACTIVITY);
        Activity activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST_ACTIVITY.getZeroBased());
        expectedModel.deleteActivity(activityToDelete);
        expectedModel.commitAddressBook();

        // delete -> deletes second activity in unfiltered activity list / first activity in filtered activity list
        activityDeleteCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered activity list to show all activities
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(activityToDelete, model.getFilteredActivityList().get(INDEX_FIRST_ACTIVITY.getZeroBased()));
        // redo -> deletes same second activity in unfiltered activity list
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
    */
    @Test
    public void equals() {
        ActivityDeleteCommand deleteFirstCommand = new ActivityDeleteCommand(INDEX_FIRST_ACTIVITY);
        ActivityDeleteCommand deleteSecondCommand = new ActivityDeleteCommand(INDEX_SECOND_ACTIVITY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ActivityDeleteCommand deleteFirstCommandCopy = new ActivityDeleteCommand(INDEX_FIRST_ACTIVITY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different activity -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no activity.
     */
    private void showNoActivity(Model model) {
        model.updateFilteredActivityList(p -> false);

        assertTrue(model.getFilteredActivityList().isEmpty());
    }
}
