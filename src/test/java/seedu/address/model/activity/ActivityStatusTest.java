package seedu.address.model.activity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ActivityStatusTest {

    private static final ActivityDateTime t1 = new ActivityDateTime("23/03/2019 1200");
    private static final ActivityDateTime t2 = new ActivityDateTime("12/03/2020 1200");
    private static final ActivityStatus s1 = new ActivityStatus(ActivityDateTime.isPast(t1));
    private static final ActivityStatus s2 = new ActivityStatus(ActivityDateTime.isPast(t2));

    @Test
    public void isCompleted() {
        assertEquals(ActivityStatus.Status.COMPLETED, s1.status);
        assertEquals(ActivityStatus.Status.ONGOING, s2.status);
    }
}
