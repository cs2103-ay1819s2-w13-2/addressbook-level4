package seedu.address.model.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DEFAULT_ACTIVITY_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITY_LOCATION_HTML;
import static seedu.address.testutil.TypicalActivities.BEGINNER;
import static seedu.address.testutil.TypicalActivities.ECOMMERCE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.activity.exceptions.ActivityNotFoundException;
import seedu.address.model.activity.exceptions.DuplicateActivityException;
import seedu.address.testutil.ActivityBuilder;

public class UniqueActivityListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueActivityList uniqueActivityList = new UniqueActivityList();

    @Test
    public void contains_nullActivity_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueActivityList.contains(null);
    }

    @Test
    public void contains_activityNotInList_returnsFalse() {
        assertFalse(uniqueActivityList.contains(BEGINNER));
    }

    @Test
    public void contains_activityInList_returnsTrue() {
        uniqueActivityList.add(BEGINNER);
        assertTrue(uniqueActivityList.contains(BEGINNER));
    }
    @Test
    public void contains_activityWithSameIdentityFieldsInList_returnsTrue() {
        uniqueActivityList.add(BEGINNER);
        Activity editedBeginner = new ActivityBuilder(BEGINNER).withActivityLocation(VALID_ACTIVITY_LOCATION_HTML)
                .withActivityDescription(DEFAULT_ACTIVITY_DESCRIPTION).build();
        assertTrue(uniqueActivityList.contains(editedBeginner));
    }

    @Test
    public void add_nullActivity_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueActivityList.add(null);
    }

    @Test
    public void add_duplicateActivity_throwsDuplicateActivityException() {
        uniqueActivityList.add(BEGINNER);
        thrown.expect(DuplicateActivityException.class);
        uniqueActivityList.add(BEGINNER);
    }

    @Test
    public void setActivity_nullTargetActivity_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueActivityList.setActivity(null, BEGINNER);
    }

    @Test
    public void setActivity_nullEditedActivity_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueActivityList.setActivity(BEGINNER, null);
    }

    @Test
    public void setActivity_targetActivityNotInList_throwsActivityNotFoundException() {
        thrown.expect(ActivityNotFoundException.class);
        uniqueActivityList.setActivity(BEGINNER, BEGINNER);
    }

    @Test
    public void setActivity_editedActivityIsSameActivity_success() {
        uniqueActivityList.add(BEGINNER);
        uniqueActivityList.setActivity(BEGINNER, BEGINNER);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(BEGINNER);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasSameIdentity_success() {
        uniqueActivityList.add(BEGINNER);
        Activity editedBeginner = new ActivityBuilder(BEGINNER).withActivityLocation(VALID_ACTIVITY_LOCATION_HTML)
                .withActivityDescription(DEFAULT_ACTIVITY_DESCRIPTION).build();
        uniqueActivityList.setActivity(BEGINNER, editedBeginner);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(editedBeginner);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasDifferentIdentity_success() {
        uniqueActivityList.add(BEGINNER);
        uniqueActivityList.setActivity(BEGINNER, ECOMMERCE);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(ECOMMERCE);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasNonUniqueIdentity_throwsDuplicateActivityException() {
        uniqueActivityList.add(BEGINNER);
        uniqueActivityList.add(ECOMMERCE);
        thrown.expect(DuplicateActivityException.class);
        uniqueActivityList.setActivity(BEGINNER, ECOMMERCE);
    }

    @Test
    public void remove_nullActivity_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueActivityList.remove(null);
    }

    @Test
    public void remove_activityDoesNotExist_throwsActivityNotFoundException() {
        thrown.expect(ActivityNotFoundException.class);
        uniqueActivityList.remove(BEGINNER);
    }

    @Test
    public void remove_existingActivity_removesActivity() {
        uniqueActivityList.add(BEGINNER);
        uniqueActivityList.remove(BEGINNER);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_nullUniqueActivityList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueActivityList.setActivities((UniqueActivityList) null);
    }

    @Test
    public void setActivities_uniqueActivityList_replacesOwnListWithProvidedUniqueActivityList() {
        uniqueActivityList.add(BEGINNER);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(ECOMMERCE);
        uniqueActivityList.setActivities(expectedUniqueActivityList);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueActivityList.setActivities((List<Activity>) null);
    }

    @Test
    public void setActivities_list_replacesOwnListWithProvidedList() {
        uniqueActivityList.add(BEGINNER);
        List<Activity> activityList = Collections.singletonList(ECOMMERCE);
        uniqueActivityList.setActivities(activityList);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(ECOMMERCE);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_listWithDuplicateActivities_throwsDuplicateActivityException() {
        List<Activity> listWithDuplicateActivities = Arrays.asList(BEGINNER, BEGINNER);
        thrown.expect(DuplicateActivityException.class);
        uniqueActivityList.setActivities(listWithDuplicateActivities);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueActivityList.asUnmodifiableObservableList().remove(0);
    }
}
