package seedu.address.logic.commands.sales;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_RECORD_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_RECORD_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_RECORD_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_RECORD_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RECORD_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_SOLD_RECORD_TWO;

import org.junit.Test;

import seedu.address.logic.commands.salescommands.EditSalesCommand.EditRecordDescriptor;
import seedu.address.testutil.salesrecords.EditRecordDescriptorBuilder;

public class EditRecordDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditRecordDescriptor descriptorWithSameValues = new EditRecordDescriptor(DESC_RECORD_ONE);
        assertTrue(DESC_RECORD_ONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_RECORD_ONE.equals(DESC_RECORD_ONE));

        // null -> returns false
        assertFalse(DESC_RECORD_ONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_RECORD_ONE.equals(5));

        // different values -> returns false
        assertFalse(DESC_RECORD_ONE.equals(DESC_RECORD_TWO));

        // different date -> returns false
        EditRecordDescriptor editedRecordOne =
                new EditRecordDescriptorBuilder(DESC_RECORD_ONE).withDate(VALID_DATE_RECORD_TWO).build();
        assertFalse(DESC_RECORD_ONE.equals(editedRecordOne));

        // different name -> returns false
        editedRecordOne =
                new EditRecordDescriptorBuilder(DESC_RECORD_ONE).withName(VALID_ITEM_NAME_RECORD_TWO).build();
        assertFalse(DESC_RECORD_ONE.equals(editedRecordOne));

        // different quantity sold -> returns false
        editedRecordOne =
                new EditRecordDescriptorBuilder(DESC_RECORD_ONE)
                        .withQuantitySold(VALID_QUANTITY_SOLD_RECORD_TWO).build();
        assertFalse(DESC_RECORD_ONE.equals(editedRecordOne));

        // different price -> returns false
        editedRecordOne =
                new EditRecordDescriptorBuilder(DESC_RECORD_ONE).withPrice(VALID_PRICE_RECORD_TWO).build();
        assertFalse(DESC_RECORD_ONE.equals(editedRecordOne));
    }
}
