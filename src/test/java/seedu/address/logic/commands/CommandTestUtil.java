package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITYNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAROFSTUDY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.InvalidCommandModeException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.ActivityNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_MATRICNUMBER_AMY = "A0123456Z";
    public static final String VALID_MATRICNUMBER_BOB = "A0654321Z";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_GENDER_AMY = "Female";
    public static final String VALID_GENDER_BOB = "Male";
    public static final String VALID_YEAROFSTUDY_AMY = "Year 2";
    public static final String VALID_YEAROFSTUDY_BOB = "Year 1";
    public static final String VALID_MAJOR_AMY = "Engineering";
    public static final String VALID_MAJOR_BOB = "Science";
    public static final String VALID_TAG_RUNNING = "running";
    public static final String VALID_TAG_SWIMMING = "swimming";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String MATRICNUMBER_DESC_AMY = " " + PREFIX_MATRICNUMBER + VALID_MATRICNUMBER_AMY;
    public static final String MATRICNUMBER_DESC_BOB = " " + PREFIX_MATRICNUMBER + VALID_MATRICNUMBER_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String MAJOR_DESC_AMY = " " + PREFIX_MAJOR + VALID_MAJOR_AMY;
    public static final String MAJOR_DESC_BOB = " " + PREFIX_MAJOR + VALID_MAJOR_BOB;
    public static final String YEAROFSTUDY_DESC_AMY = " " + PREFIX_YEAROFSTUDY + VALID_YEAROFSTUDY_AMY;
    public static final String YEAROFSTUDY_DESC_BOB = " " + PREFIX_YEAROFSTUDY + VALID_YEAROFSTUDY_BOB;


    public static final String TAG_DESC_SWIMMING = " " + PREFIX_TAG + VALID_TAG_SWIMMING;
    public static final String TAG_DESC_RUNNING = " " + PREFIX_TAG + VALID_TAG_RUNNING;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_MATRICNUMBER_DESC = " " + PREFIX_MATRICNUMBER + "A1 D"; // only one word allowed;
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "mafema"; // only male and female allowed
    public static final String INVALID_YEAROFSTUDY_DESC = " " + PREFIX_YEAROFSTUDY + "2 year";
    public static final String INVALID_MAJOR_DESC = " " + PREFIX_MAJOR + "2134";

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final MemberEditCommand.EditPersonDescriptor DESC_AMY;
    public static final MemberEditCommand.EditPersonDescriptor DESC_BOB;

    public static final String VALID_ACTIVITY_NAME_HTML = "HTML WorkShop";
    public static final String VALID_ACTIVITY_NAME_OUTING = "Sentosa Outing ";
    public static final String VALID_ACTIVITY_DATETIME_HTML = "02/02/2019 1200";
    public static final String VALID_ACTIVITY_DATETIME_OUTING = "06/06/2019 1000";
    public static final String VALID_ACTIVITY_LOCATION_HTML = "Icube Auditorium";
    public static final String VALID_ACTIVITY_LOCATION_OUTING = "KR MRT";
    public static final String VALID_ACTIVITY_DESCRIPTION_OUTING = "Meet at KR MRT. Remember to apply sunblock";
    public static final String DEFAULT_ACTIVITY_DESCRIPTION = "More details to be added.";

    public static final String ACTIVITY_NAME_DESC_HTML = " " + PREFIX_ACTIVITYNAME + VALID_ACTIVITY_NAME_HTML;
    public static final String ACTIVITY_NAME_DESC_OUTING = " " + PREFIX_ACTIVITYNAME + VALID_ACTIVITY_NAME_OUTING;
    public static final String ACTIVITY_DATETIME_DESC_HTML = " " + PREFIX_DATETIME + VALID_ACTIVITY_DATETIME_HTML;
    public static final String ACTIVITY_DATETIME_DESC_OUTING = " " + PREFIX_DATETIME + VALID_ACTIVITY_DATETIME_OUTING;
    public static final String ACTIVITY_LOCATION_DESC_HTML = " " + PREFIX_LOCATION + VALID_ACTIVITY_LOCATION_HTML;
    public static final String ACTIVITY_LOCATION_DESC_OUTING = " " + PREFIX_LOCATION + VALID_ACTIVITY_LOCATION_OUTING;
    public static final String ACTIVITY_DESCRIPTION_DESC_HTML = " " + PREFIX_ADESCRIPTION
            + DEFAULT_ACTIVITY_DESCRIPTION;
    public static final String ACTIVITY_DESCRIPTION_DESC_OUTING = " " + PREFIX_ADESCRIPTION
            + VALID_ACTIVITY_DESCRIPTION_OUTING;


    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withMatricNumber(VALID_MATRICNUMBER_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withGender(VALID_GENDER_AMY).withYearOfStudy(VALID_YEAROFSTUDY_AMY)
                .withMajor(VALID_MAJOR_AMY).withTags(VALID_TAG_SWIMMING).build();

        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withMatricNumber(VALID_MATRICNUMBER_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withGender(VALID_GENDER_BOB).withYearOfStudy(VALID_YEAROFSTUDY_BOB).withMajor(VALID_MAJOR_BOB)
                .withTags(VALID_TAG_RUNNING, VALID_TAG_SWIMMING).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            CommandResult expectedCommandResult, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException | InvalidCommandModeException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());
        Person expectedSelectedPerson = actualModel.getSelectedPerson();

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException | InvalidCommandModeException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
            assertEquals(expectedSelectedPerson, actualModel.getSelectedPerson());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Deletes the first person in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstPerson(Model model) {
        Person firstPerson = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        model.commitAddressBook();
    }

    /**
     * Updates {@code model}'s filtered list to show only the activity at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showActivityAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredActivityList().size());

        Activity activity = model.getFilteredActivityList().get(targetIndex.getZeroBased());
        final String[] splitName = activity.getName().fullActivityName.split("\\s+");
        model.updateFilteredActivityList(new ActivityNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredActivityList().size());
    }

    /**
     * Deletes the first activity in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstActivity(Model model) {
        Activity firstActivity = model.getFilteredActivityList().get(0);
        model.deleteActivity(firstActivity);
        model.commitAddressBook();
    }
}
