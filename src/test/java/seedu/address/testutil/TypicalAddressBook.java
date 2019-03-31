package seedu.address.testutil;

import static seedu.address.testutil.TypicalActivities.getTypicalActivities;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
import seedu.address.model.activity.Activity;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects and a list of {@code Activity} to be used in tests.
 */
public class TypicalAddressBook {
    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Activity activity: getTypicalActivities()) {
            ab.addActivity(activity);
        }
        return ab;
    }
}
