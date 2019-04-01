package seedu.address.model.activity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ActivityStatusTest {

    private static final ActivityDateTime t1 = new ActivityDateTime("23/03/2019 1200");
    private static final ActivityDateTime t2 = new ActivityDateTime("12/03/2020 1200");
    private static final ActivityStatus s1 = new ActivityStatus(t1.isPast());
    private static final ActivityStatus s2 = new ActivityStatus(t2.isPast());

    @Test
    public void isCompleted() {
        assertTrue(s1.isCompleted());
        assertFalse(s2.isCompleted());
    }
}
