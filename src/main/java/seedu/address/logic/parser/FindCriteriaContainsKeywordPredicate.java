package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a criteria given matches the list of available sorting criteria.
 */
public class FindCriteriaContainsKeywordPredicate implements Predicate<String> {
    private final String criteria;
    private final String[] findKeywords;
    private final String[] keywords = {"name", "matricnum"};

    public FindCriteriaContainsKeywordPredicate(String criteria) {
        requireNonNull(criteria);
        requireNonNull(criteria.split("\\s+", 2)[1]);
        this.criteria = criteria.split("\\s+", 2)[0];
        this.findKeywords = criteria.split("\\s+", 2)[1].split("\\s+");
    }

    @Override
    public boolean test(String criteria) {
        for (int i = 0; i < keywords.length; i++) {
            if (criteria.equalsIgnoreCase(keywords[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCriteriaContainsKeywordPredicate // instanceof handles nulls
                && criteria.equals(((FindCriteriaContainsKeywordPredicate) other).criteria)); // state check
    }

    @Override
    public String toString() {
        return criteria;
    }

    public String[] getFindKeywords() {
        return findKeywords;
    }

}
