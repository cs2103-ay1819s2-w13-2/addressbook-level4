package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.ActivityDateTime;
import seedu.address.model.activity.ActivityDescription;
import seedu.address.model.activity.ActivityLocation;
import seedu.address.model.activity.ActivityName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Major;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.YearOfStudy;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new MatricNumber("A0111111B"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Gender("Male"), new YearOfStudy("Year 1"), new Major("Chemistry"),
                    getTagSet("swimming")),
            new Person(new Name("Bernice Yu"), new MatricNumber("A0222222C"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Gender("Female"), new YearOfStudy("Year 2"), new Major("Computer Science"),
                    getTagSet("running")),
            new Person(new Name("Charlotte Oliveiro"), new MatricNumber("A0333333D"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Gender("Female"), new YearOfStudy("Year 3"), new Major("Mathematics"),
                    getTagSet("running")),
            new Person(new Name("David Li"), new MatricNumber("A0444444Z"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Gender("Male"), new YearOfStudy("Year 3"), new Major("Bioengineering"),
                    getTagSet("soccer")),
            new Person(new Name("Irfan Ibrahim"), new MatricNumber("A0555555K"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Gender("Male"), new YearOfStudy("Year 2"), new Major("Physics"),
                    getTagSet("basketball")),
            new Person(new Name("Roy Balakrishnan"), new MatricNumber("A0777777L"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"), new Gender("Male"),
                    new YearOfStudy("Year 2"), new Major("Physics"), getTagSet("colleagues"))
        };
    }

    public static Activity[] getSampleActivities() {
        return new Activity[] {
            new Activity(new ActivityName("CS2103 Help Session"), new ActivityDateTime("10/02/2019 1400"),
                new ActivityLocation("Com1 02-06"), new ActivityDescription("Bring Laptop")),
            new Activity(new ActivityName("Club Annual Meeting"), new ActivityDateTime("11/02/2019 1600"),
                new ActivityLocation("Computer Club Room"), new ActivityDescription("Summary of past year activities")),
            new Activity(new ActivityName("Avenger Movie Viewing"), new ActivityDateTime("10/05/2019 2000"),
                new ActivityLocation("Cathay cinema"), new ActivityDescription("12 dollar per pax")),
            new Activity(new ActivityName("Summer Hackerthon"), new ActivityDateTime("06/06/2019 0900"),
                new ActivityLocation("Icube Auditorium"), new ActivityDescription())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Activity sampleActivity : getSampleActivities()) {
            sampleAb.addActivity(sampleActivity);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
