package seedu.address.model.activity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ActivityLocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ActivityLocation(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ActivityLocation(invalidLocation));
    }

    @Test
    public void isValidLocation() {
        //null location
        Assert.assertThrows(NullPointerException.class, () -> ActivityLocation.isValidLocation(null));

        //empty string
        assertFalse(ActivityLocation.isValidLocation(""));

        //single blank space
        assertFalse(ActivityLocation.isValidLocation(" "));

        //valid location
        String validLocation = "Tutorial room @LT19";
        assertTrue(ActivityLocation.isValidLocation(validLocation));

    }
}
