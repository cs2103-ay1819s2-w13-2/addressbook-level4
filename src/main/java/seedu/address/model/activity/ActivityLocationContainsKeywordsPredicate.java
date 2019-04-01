package seedu.address.model.activity;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Activity}'s {@code ActivityLocation} matches any of the keywords given.
 */
public class ActivityLocationContainsKeywordsPredicate implements Predicate<Activity> {
    private final List<String> keywords;

    public ActivityLocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Activity activity) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(activity.getLocation().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.activity.ActivityLocationContainsKeywordsPredicate
                // instanceof handles nulls
                && keywords.equals(((seedu.address.model.activity.ActivityLocationContainsKeywordsPredicate) other)
                .keywords)); // state check
    }
}
