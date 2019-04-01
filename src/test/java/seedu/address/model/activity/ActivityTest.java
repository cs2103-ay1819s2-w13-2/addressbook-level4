package seedu.address.model.activity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DEFAULT_ACTIVITY_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_DATETIME_HTML;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_LOCATION_HTML;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_NAME_HTML;
import static seedu.address.testutil.TypicalActivities.AI;
import static seedu.address.testutil.TypicalActivities.HTML;

import org.junit.Test;

import seedu.address.testutil.ActivityBuilder;

public class ActivityTest {

    @Test
    public void isSameActivity() {
        // same object -> returns true
        assertTrue(AI.isSameActivity(AI));

        // null -> returns false
        assertFalse(AI.isSameActivity(null));

        // different name -> returns false
        Activity editedAi = new ActivityBuilder(AI).withActivityName(VALID_ACTIVITY_NAME_HTML).build();
        assertFalse(AI.isSameActivity(editedAi));

        // different datetime -> returns false
        editedAi = new ActivityBuilder(AI).withActivityDateTime(VALID_ACTIVITY_DATETIME_HTML).build();
        assertFalse(AI.isSameActivity(editedAi));

        // same name, datetime, different other attributes -> returns true
        editedAi = new ActivityBuilder(AI).withActivityLocation(VALID_ACTIVITY_LOCATION_HTML)
                .withActivityDescription(DEFAULT_ACTIVITY_DESCRIPTION).build();
        assertTrue(AI.isSameActivity(editedAi));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Activity aiCopy = new ActivityBuilder(AI).build();
        assertTrue(AI.equals(aiCopy));

        // same object -> returns true
        assertTrue(AI.equals(AI));

        // null -> returns false
        assertFalse(AI.equals(null));

        // different type -> returns false
        assertFalse(AI.equals(5));

        // different person -> returns false
        assertFalse(AI.equals(HTML));

        // different name -> returns false
        Activity editedAi = new ActivityBuilder(AI).withActivityName(VALID_ACTIVITY_NAME_HTML).build();
        assertFalse(AI.equals(editedAi));

        // different datetime -> returns false
        editedAi = new ActivityBuilder(AI).withActivityDateTime(VALID_ACTIVITY_DATETIME_HTML).build();
        assertFalse(AI.equals(editedAi));

        // different location -> returns false
        editedAi = new ActivityBuilder(AI).withActivityLocation(VALID_ACTIVITY_LOCATION_HTML).build();
        assertFalse(AI.equals(editedAi));

        // different description -> returns false
        editedAi = new ActivityBuilder(AI).withActivityDescription(DEFAULT_ACTIVITY_DESCRIPTION).build();
        assertFalse(AI.equals(editedAi));
    }
}

