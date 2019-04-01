package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code MatricNum} matches any of the keywords given.
 */

public class MatricNumberContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public MatricNumberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsIgnoreCase(person.getMatricNumber().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatricNumberContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MatricNumberContainsKeywordsPredicate) other).keywords)); // state check
    }
}
