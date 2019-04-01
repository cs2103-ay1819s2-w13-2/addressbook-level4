package seedu.address.model.activity;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Activity}'s {@code ActivityDescription} matches any of the keywords given.
 */
public class ActivityDescriptionContainsKeywordsPredicate implements Predicate<Activity> {
    private final List<String> keywords;

    public ActivityDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Activity activity) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(activity.getDescription().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.activity.ActivityDescriptionContainsKeywordsPredicate
                // instanceof handles nulls
                && keywords.equals(((seedu.address.model.activity.ActivityDescriptionContainsKeywordsPredicate) other)
                .keywords)); // state check
    }
}
