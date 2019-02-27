package seedu.address.commons.core;

/**
 * Mode which the app is running
 */
public class AppMode {

    private static int MODE; // 1 member 2 activity

    public static void init() {
        MODE = 1;
    }

    public static void setModeMember () {
        MODE = 1;
    }

    public static void setModeActivity () {
        MODE = 2;
    }

    public static int getMode () {
        return MODE;
    }

}
