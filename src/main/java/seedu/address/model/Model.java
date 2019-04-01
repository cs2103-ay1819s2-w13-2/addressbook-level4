package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.activity.Activity;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Activity> PREDICATE_SHOW_ALL_ACTIVITIES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Sets AddressBook mode to {@code mode}.
     */
    void setAddressBookMode(AppMode.Modes mode);

    /**
     * Get AddressBook mode.
     */
    AppMode.Modes getAddressBookMode();

    /**
     * Returns true if appMode is MEMBER.
     */
    boolean addressBookModeIsMember ();

    /**
     * Returns true if appMode is ACTIVITY.
     */
    boolean addressBookModeIsActivity ();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same matricNumber as {@code person} exists in the address book.
     */
    boolean hasMatricNumber(MatricNumber matricNumber);

    /**
     * Returns Person if a person with the same matricNumber as {@code person} exists in the address book.
     */
    Person getPersonWithMatricNumber(MatricNumber matricNumber);
    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered person and activity list to NONE.
     */
    void resetLists();

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void sortAddressBook(Predicate<String> predicate);

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    /**
     * Selected person in the filtered person list.
     * null if no person is selected.
     */
    ReadOnlyProperty<Person> selectedPersonProperty();

    /**
     * Returns the selected person in the filtered person list.
     * null if no person is selected.
     */
    Person getSelectedPerson();

    /**
     * Sets the selected person in the filtered person list.
     */
    void setSelectedPerson(Person person);

    /**
     * Sets the selected person in the filtered person list.
     * Returns the filtered person, for export
     */
    Person generateExportedPerson(Person person);

    /** Returns an unmodifiable view of the filtered activity list */
    ObservableList<Activity> getFilteredActivityList();

    /**
     * Updates the filter of the filtered activity list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredActivityList(Predicate<Activity> predicate);

    /**
     * Returns true if an activity with the same identity as {@code activity} exists in the address book.
     */
    boolean hasActivity(Activity activity);

    /**
     * Returns true if an activity at the same time and location as {@code activity} exists in the address book.
     */
    boolean hasActivityClashInLocation(Activity activity);

    /**
     * Deletes the given activity.
     * The activity must exist in the address book.
     */
    void deleteActivity(Activity target);

    /**
     * Adds the given activity.
     * {@code activity} must not already exist in the address book.
     */
    void addActivity(Activity activity);

    /**
     * Replaces the given activity {@code target} with {@code editedActivity}.
     * {@code target} must exist in the address book.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity
     * in the address book.
     */
    void setActivity(Activity target, Activity editedActivity);

    /**
     * Selected person in the filtered person list.
     * null if no person is selected.
     */
    ReadOnlyProperty<Activity> selectedActivityProperty();

    /**
     * Returns the selected person in the filtered person list.
     * null if no person is selected.
     */
    Activity getSelectedActivity();

    /**
     * Sets the selected person in the filtered person list.
     */
    void setSelectedActivity(Activity activity);

    /**
     * Update entire activity list
     */
    void updateActivityList();

}
