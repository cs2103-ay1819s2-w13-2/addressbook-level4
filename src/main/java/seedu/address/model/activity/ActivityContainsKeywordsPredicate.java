package seedu.address.model.activity;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that the attributes of an {@code Activity} matches any of the keywords given.
 */
public class ActivityContainsKeywordsPredicate implements Predicate<Activity> {
    private final List<String> keywords;

    public ActivityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns true if activity contains the predicate
     */
    public boolean test(Activity activity) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(activity.getName().fullActivityName, keyword)
                || StringUtil.containsWordIgnoreCase(activity.getDescription().value, keyword)
                || StringUtil.containsWordIgnoreCase(activity.getLocation().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.activity.ActivityContainsKeywordsPredicate
                // instanceof handles nulls
                && keywords.equals(((ActivityContainsKeywordsPredicate) other).keywords)); // state check
    }

}
