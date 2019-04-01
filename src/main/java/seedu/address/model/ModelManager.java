package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.exceptions.ActivityNotFoundException;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SimpleObjectProperty<Person> selectedPerson = new SimpleObjectProperty<>();
    private final FilteredList<Activity> filteredActivities;
    private final SimpleObjectProperty<Activity> selectedActivity = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());
        filteredPersons.addListener(this::ensureSelectedPersonIsValid);
        filteredActivities = new FilteredList<>(versionedAddressBook.getActivityList());
        filteredActivities.addListener(this::ensureSelectedActivityIsValid);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        versionedAddressBook.resetData(addressBook);
    }

    @Override
    public void setAddressBookMode(AppMode.Modes mode) {
        versionedAddressBook.setAppMode(mode);
    }

    @Override
    public AppMode.Modes getAddressBookMode () {
        return versionedAddressBook.getAppMode();
    }

    @Override
    public boolean addressBookModeIsMember () {
        return versionedAddressBook.modeIsMember();
    }
    @Override
    public boolean addressBookModeIsActivity () {
        return versionedAddressBook.modeIsActivity();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook () {
        return versionedAddressBook;
    }

    @Override
    public boolean hasPerson (Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public boolean hasMatricNumber(MatricNumber matricNumber) {
        requireNonNull(matricNumber);
        return versionedAddressBook.hasMatricNumber(matricNumber);
    }

    @Override
    public Person getPersonWithMatricNumber(MatricNumber matricNumber) {
        requireNonNull(matricNumber);
        return versionedAddressBook.getPersonWithMatricNumber(matricNumber);
    }
    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        versionedAddressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return versionedAddressBook.hasActivity(activity);
    }

    @Override
    public boolean hasActivityClashInLocation(Activity activity) {
        requireNonNull(activity);
        return versionedAddressBook.hasActivityClashInLocation(activity);
    }

    @Override
    public void deleteActivity(Activity target) {
        versionedAddressBook.removeActivity(target);
    }

    @Override
    public void addActivity(Activity activity) {
        versionedAddressBook.addActivity(activity);
        updateFilteredActivityList(PREDICATE_SHOW_ALL_ACTIVITIES);
    }

    @Override
    public void setActivity(Activity target, Activity editedActivity) {
        requireAllNonNull(target, editedActivity);

        versionedAddressBook.setActivity(target, editedActivity);
    }

    public void sortAddressBook(Predicate<String> predicate) {
        versionedAddressBook.sortAddressBook(predicate);
    }

    @Override
    public void updateActivityList() {
        versionedAddressBook.updateActivities();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    @Override
    public void resetLists() {
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        updateFilteredActivityList(PREDICATE_SHOW_ALL_ACTIVITIES);
    }

    //=========== Selected person ===========================================================================

    @Override
    public ReadOnlyProperty<Person> selectedPersonProperty() {
        return selectedPerson;
    }

    @Override
    public Person getSelectedPerson() {
        return selectedPerson.getValue();
    }

    @Override
    public Person generateExportedPerson(Person person) {
        if (person != null && !filteredPersons.contains(person)) {
            throw new PersonNotFoundException();
        }
        setSelectedPerson(person);
        return selectedPerson.get(); // this function returns a person
    }

    @Override
    public void setSelectedPerson(Person person) {
        if (person != null && !filteredPersons.contains(person)) {
            throw new PersonNotFoundException();
        }
        selectedPerson.setValue(person);
    }

    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedPersonIsValid(ListChangeListener.Change<? extends Person> change) {
        while (change.next()) {
            if (selectedPerson.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedPersonReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedPerson.getValue());
            if (wasSelectedPersonReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedPerson.getValue());
                selectedPerson.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                    .anyMatch(removedPerson -> selectedPerson.getValue().isSamePerson(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedPerson.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    //=========== Filtered Activity List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Activity} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Activity> getFilteredActivityList() {
        return filteredActivities;
    }

    @Override
    public void updateFilteredActivityList(Predicate<Activity> predicate) {
        requireNonNull(predicate);
        updateActivityList();
        filteredActivities.setPredicate(predicate);
    }

    //=========== Selected activity ===========================================================================

    @Override
    public ReadOnlyProperty<Activity> selectedActivityProperty() {
        return selectedActivity;
    }

    @Override
    public Activity getSelectedActivity() {
        return selectedActivity.getValue();
    }

    @Override
    public void setSelectedActivity(Activity activity) {
        if (activity != null && !filteredActivities.contains(activity)) {
            throw new ActivityNotFoundException();
        }
        selectedActivity.setValue(activity);
    }

    /**
     * Ensures {@code selectedActivity} is a valid activity in {@code filteredActivities}.
     */
    private void ensureSelectedActivityIsValid(ListChangeListener.Change<? extends Activity> change) {
        while (change.next()) {
            if (selectedActivity.getValue() == null) {
                // null is always a valid selected activity, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedActivityReplaced = change.wasReplaced()
                    && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedActivity.getValue());
            if (wasSelectedActivityReplaced) {
                // Update selectedActivity to its new value.
                int index = change.getRemoved().indexOf(selectedActivity.getValue());
                selectedActivity.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedActivityRemoved = change.getRemoved().stream()
                    .anyMatch(removedActivity -> selectedActivity.getValue().isSameActivity(removedActivity));
            if (wasSelectedActivityRemoved) {
                // Select the activity that came before it in the list,
                // or clear the selection if there is no such activity.
                selectedActivity.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && Objects.equals(selectedPerson.get(), other.selectedPerson.get());
    }

}
