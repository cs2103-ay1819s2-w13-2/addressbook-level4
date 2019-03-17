package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class DemoListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DemoListPanel.class);

    @FXML
    private StackPane demoPart;

    public DemoListPanel() {
        super(FXML);
        demoPart.getChildren().add(new Button("A"));
        demoPart.getChildren().add(new Button("B"));
        demoPart.getChildren().add(new Button("C"));

    }

}
