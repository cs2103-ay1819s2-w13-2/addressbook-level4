package seedu.address.logic.parser.exceptions;

/**
 * Represents an error which a command cannot run in the current mode.
 */
public class InvalidCommandModeException extends Exception {

    public InvalidCommandModeException(String message) {
        super(message);
    }

}
