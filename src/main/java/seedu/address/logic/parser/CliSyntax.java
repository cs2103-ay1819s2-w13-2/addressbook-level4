package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_MAJOR = new Prefix("m/");
    public static final Prefix PREFIX_MATRICNUMBER = new Prefix("mn/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_YEAROFSTUDY = new Prefix("y/");

    public static final Prefix PREFIX_ACTIVITYNAME = new Prefix("n/");
    public static final Prefix PREFIX_DATETIME = new Prefix("d/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_ADESCRIPTION = new Prefix("de/");
    public static final Prefix PREFIX_IC = new Prefix("ic/");
    public static final Prefix PREFIX_ALL = new Prefix("a/");

    public static final String CONDITION_POSITIVE = "+";
    public static final String CONDITION_NEGATIVE = "-";
}
