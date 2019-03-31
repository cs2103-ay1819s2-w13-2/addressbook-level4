package seedu.address.model.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ActivityDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ActivityDescription(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ActivityDescription(invalidDescription));
    }

    @Test
    public void constructor_valid_noInput() {
        ActivityDescription defaultDescription = new ActivityDescription("More details to be added.");
        ActivityDescription emptyConstructor = new ActivityDescription();
        assertEquals(defaultDescription, emptyConstructor);
    }


    @Test
    public void isValidDescription() {
        //null description
        Assert.assertThrows(NullPointerException.class, () -> ActivityDescription.isValidDescription(null));

        //empty string
        assertFalse(ActivityDescription.isValidDescription(""));

        //single blank space
        assertFalse(ActivityDescription.isValidDescription(" "));

        //valid description
        String validDescription = "HTML workshop with guest speaker from Google.";
        assertTrue(ActivityDescription.isValidDescription(validDescription));
    }

}
