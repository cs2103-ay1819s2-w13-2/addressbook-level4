package seedu.address.model.activity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ActivityNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ActivityName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidActivityName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ActivityName(invalidActivityName));
    }

    @Test
    public void isValidActivityName() {
        // null activity name
        Assert.assertThrows(NullPointerException.class, () -> ActivityName.isValidActivityName(null));

        // invalid activity name
        assertFalse(ActivityName.isValidActivityName("")); // empty string
        assertFalse(ActivityName.isValidActivityName(" ")); // spaces only
        assertFalse(ActivityName.isValidActivityName("@")); // only non-alphanumeric characters
        assertFalse(ActivityName.isValidActivityName("HTML@")); // contains non-alphanumeric characters

        //valid name
        assertTrue(ActivityName.isValidActivityName("club cohesion")); // alphabets only
        assertTrue(ActivityName.isValidActivityName("12345")); // numbers only
        assertTrue(ActivityName.isValidActivityName("2nd general meeting")); // alphanumeric characters
        assertTrue(ActivityName.isValidActivityName("Club Cohesion")); // with capital letters
    }
}
