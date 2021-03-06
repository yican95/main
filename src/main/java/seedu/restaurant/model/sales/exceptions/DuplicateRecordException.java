package seedu.restaurant.model.sales.exceptions;

//@@author HyperionNKJ
/**
 * Signals that the operation will result in duplicate Records (Records are considered duplicates if they have the same
 * identity).
 */
public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException() {
        super("Operation would result in duplicate records");
    }
}
