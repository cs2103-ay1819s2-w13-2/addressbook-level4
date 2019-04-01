package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a criteria given matches the list of available sorting criteria.
 */
public class SortCriteriaContainsKeywordPredicate implements Predicate<String> {
    private final String criteria;
    private final String[] keywords = {"name", "gender", "major", "yearOfStudy"};

    public SortCriteriaContainsKeywordPredicate(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean test(String criteria) {
        requireNonNull(criteria);
        for (int i = 0; i < keywords.length; i++) {
            if (criteria == keywords[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCriteriaContainsKeywordPredicate // instanceof handles nulls
                && criteria.equals(((SortCriteriaContainsKeywordPredicate) other).criteria)); // state check
    }
    @Override
    public String toString() {
        return criteria;
    }

}
