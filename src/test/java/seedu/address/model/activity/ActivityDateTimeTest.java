package seedu.address.model.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ActivityDateTimeTest {

    private static final ActivityDateTime t1 = new ActivityDateTime("12/02/2018 1200");
    private static final ActivityDateTime t2 = new ActivityDateTime("12/02/2018 1300");
    private static final ActivityDateTime t3 = new ActivityDateTime("14/02/2018 1200");
    private static final ActivityDateTime t4 = new ActivityDateTime("12/03/2018 1200");
    private static final ActivityDateTime t5 = new ActivityDateTime("12/03/2020 1200");

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ActivityDateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ActivityDateTime(invalidDateTime));
    }

    @Test
    public void isValidActivityDateTime() {
        //null datetime
        Assert.assertThrows(NullPointerException.class, () -> ActivityDateTime.isValidActivityDateTime(null));

        //blank datetime
        assertFalse(ActivityDateTime.isValidActivityDateTime("")); //empty string
        assertFalse(ActivityDateTime.isValidActivityDateTime("")); //single space

        //missing parts
        assertFalse(ActivityDateTime.isValidActivityDateTime("21/12/2012")); //missing time
        assertFalse(ActivityDateTime.isValidActivityDateTime("1230")); //missing date
        assertFalse(ActivityDateTime.isValidActivityDateTime("21/12/20121230")); //missing blank

        //invalid parts
        assertFalse(ActivityDateTime.isValidActivityDateTime("xx/12/2012 1230")); //date not in numbers
        assertFalse(ActivityDateTime.isValidActivityDateTime("21.12.2012 1230"));
        //date separation not using forward slash
        assertFalse(ActivityDateTime.isValidActivityDateTime("21/12/2012 -130")); //time not in numbers
        assertFalse(ActivityDateTime.isValidActivityDateTime("21/12/2012.1230"));
        //separation of date and time not using space
        assertFalse(ActivityDateTime.isValidActivityDateTime("12/31/2012 1230")); //date input invalid
        assertFalse(ActivityDateTime.isValidActivityDateTime("29/02/2018 1230")); //invalid date input
        assertFalse(ActivityDateTime.isValidActivityDateTime("21/12/2012 2430")); //hour input out of range
        assertFalse(ActivityDateTime.isValidActivityDateTime("21/12/2012 1260")); //minute input out of range

        //valid date time
        assertTrue(ActivityDateTime.isValidActivityDateTime("29/02/2012 1230")); //valid leap year date
        assertTrue(ActivityDateTime.isValidActivityDateTime("21/02/2012 0000")); //valid 0am
        assertTrue(ActivityDateTime.isValidActivityDateTime("21/12/2012 2359")); //valid time 2359
    }

    @Test
    public void isPast() {
        assertTrue(ActivityDateTime.isPast(t1));
        assertFalse(ActivityDateTime.isPast(t5));
    }


    @Test
    public void compareTo() {

        //same time
        assertEquals(0, t1.compareTo(t1));

        //before
        assertEquals(-1, t1.compareTo(t2));
        assertEquals(-1, t1.compareTo(t3));
        assertEquals(-1, t1.compareTo(t4));

        //after
        assertEquals(1, t2.compareTo(t1));
        assertEquals(1, t3.compareTo(t1));
        assertEquals(1, t4.compareTo(t1));
    }
}
