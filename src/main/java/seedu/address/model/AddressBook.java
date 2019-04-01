package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.UniqueActivityList;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueActivityList activities;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();
    private final AppMode appMode;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        activities = new UniqueActivityList();
        appMode = new AppMode();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Returns true if appMode is MEMBER
     */
    public boolean modeIsMember () {
        return appMode.isMember();
    }
    /**
     * Returns true if appMode is ACTIVITY
     */
    public boolean modeIsActivity () {
        return appMode.isActivity();
    }

    /**
     * Returns value of appMode
     */
    public AppMode.Modes getAppMode () {
        return appMode.getAppMode();
    }

    /**
     * Sets value of appMode to (@code mode)
     */
    public void setAppMode (AppMode.Modes mode) {
        appMode.setAppMode(mode);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
        indicateModified();
    }

    /**
     * Replaces the contents of the activity list with {@code activities}.
     * {@code activities} must not contain duplicate activities.
     */
    public void setActivities(List<Activity> activities) {
        this.activities.setActivities(activities);
        indicateModified();
    }

    /**
     * Update the status of the activities in the activity list
     */
    public void updateActivities() {
        List<Activity> activityList = this.getActivityList();
        for (Activity activity: activityList) {
            if (!activity.getStatus().equals(activity.getCurrentStatus())) {
                Activity updated = activity.updateActivity();
                setActivity(activity, updated);
            }
        }
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());

        setActivities(newData.getActivityList());

        setAppMode(newData.getCurrMode());
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void sortAddressBook(Predicate<String> predicate) {
        this.persons.sortList(predicate);
        indicateModified();
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same matricNumber as {@code person} exists in the address book.
     */
    public boolean hasMatricNumber(MatricNumber matricNumber) {
        requireNonNull(matricNumber);
        return persons.containsMatricNumber(matricNumber);
    }

    /**
     * Returns the person if a person with the same matricNumber as {@code person} exists in the address book.
     */
    public Person getPersonWithMatricNumber(MatricNumber matricNumber) {
        requireNonNull(matricNumber);
        return persons.getPersonWithMatricNumber(matricNumber);
    }
    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        indicateModified();
    }


    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// activity-level operations

    /**
     * Returns true if activity with the same identity as {@code activity} exists in the address book.
     */
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return activities.contains(activity);
    }

    /**
     * Returns true if activity that clash in location and time as {@code activity} exists in the address book.
     */
    public boolean hasActivityClashInLocation(Activity activity) {
        requireNonNull(activity);
        return activities.containsActivityWithLocationClash(activity);
    }

    /**
     * Adds an activity to the address book.
     * The activity must not already exist in the address book.
     */
    public void addActivity(Activity a) {
        activities.add(a);
        indicateModified();
    }

    /**
     * Replaces the given activity {@code target} in the list with {@code editedActivity}.
     * {@code target} must exist in the address book.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity in the
     * address book.
     */
    public void setActivity(Activity target, Activity editedActivity) {
        requireNonNull(editedActivity);

        activities.setActivity(target, editedActivity);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeActivity(Activity key) {
        activities.remove(key);
        indicateModified();
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Activity> getActivityList() {
        return activities.asUnmodifiableObservableList();
    }

    @Override
    public AppMode.Modes getCurrMode() {
        return appMode.getAppMode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && activities.equals(((AddressBook) other).activities));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, activities);
    }
}
