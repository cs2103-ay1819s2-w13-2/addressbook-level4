package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICNUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICNUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_RUNNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAROFSTUDY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAROFSTUDY_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withMatricNumber("A0123123Z")
            .withPhone("94351253")
            .withEmail("alice@example.com")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withGender("Female")
            .withYearOfStudy("Year 4")
            .withMajor("Business Analytics")
            .withTags("running").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withMatricNumber("A0222222B")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withGender("male")
            .withYearOfStudy("year 1")
            .withMajor("computing")
            .withTags("swimming", "running").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withMatricNumber("A0333333C")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withGender("male")
            .withYearOfStudy("year 1")
            .withMajor("computing")
            .withTags("swimming", "running").build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withMatricNumber("A0444444D")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withGender("male")
            .withYearOfStudy("year 1")
            .withMajor("computing")
            .withTags("hockey").build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withMatricNumber("A0555555E")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withGender("Female")
            .withYearOfStudy("year 1")
            .withMajor("computing")
            .withTags("hockey").build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withMatricNumber("A0777777R")
            .withPhone("94812427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withGender("female")
            .withYearOfStudy("year 1")
            .withMajor("computing")
            .withTags("hockey").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withMatricNumber("A0888888R")
            .withPhone("94182442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withGender("Male")
            .withYearOfStudy("year 1")
            .withMajor("computing")
            .withTags("hockey").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withMatricNumber("A0999999P")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withGender("Female")
            .withYearOfStudy("year 1")
            .withMajor("computing")
            .withTags("hockey").build();

    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withMatricNumber("A0321654S")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withGender("female")
            .withYearOfStudy("year 1")
            .withMajor("computing")
            .withTags("hockey").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withMatricNumber(VALID_MATRICNUMBER_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withGender(VALID_GENDER_AMY).withYearOfStudy(VALID_YEAROFSTUDY_AMY)
            .withMajor(VALID_MAJOR_AMY).withTags(VALID_TAG_SWIMMING).build();

    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withMatricNumber(VALID_MATRICNUMBER_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withGender(VALID_GENDER_BOB)
            .withYearOfStudy(VALID_YEAROFSTUDY_BOB).withMajor(VALID_MAJOR_BOB)
            .withTags(VALID_TAG_RUNNING, VALID_TAG_SWIMMING).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBookWithPerson() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
