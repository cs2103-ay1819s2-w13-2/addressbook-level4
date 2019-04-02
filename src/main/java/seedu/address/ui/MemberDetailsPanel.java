package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.net.URL;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * A UI for displaying member details.
 */
public class MemberDetailsPanel extends UiPart<Region> {
    public static final URL DEFAULT_PAGE =
            requireNonNull(MainApp.class.getResource(FXML_FILE_FOLDER + "default.html"));
    public static final String FXML = "MemberDetailsPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private WebView browser;

    @FXML
    private Text name;

    @FXML
    private Text matricNumber;

    @FXML
    private Text phone;

    @FXML
    private Text email;

    @FXML
    private Text address;

    @FXML
    private Text gender;

    @FXML
    private Text yearOfStudy;

    @FXML
    private Text major;

    public MemberDetailsPanel (ObservableValue<Person> selectedMember) {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        // Load person page when selected person changes.
        selectedMember.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                loadDefaultPage();
                return;
            }
            loadPersonPage(newValue);
        });
            loadDefaultPage();
    }

    /**
     * Set up all patient details into the display panel.
     * @param member The patient to be displayed.
     */
    private void setUpMemberDetails(Person member) {
        name.setText("Name: " + member.getName().toString());
        matricNumber.setText("Matric Number: " + member.getMatricNumber().toString());
        phone.setText("Phone: " + member.getPhone().toString());
        email.setText("Email: " + member.getEmail().toString());
        address.setText("Address: " + member.getAddress().toString());
        gender.setText("Gender: " + member.getGender().toString());
        yearOfStudy.setText("Year of study: " + member.getYearOfStudy().toString());
        major.setText("Major: " + member.getMajor().toString());
    }

    /**
     * Load the selected person's page with its relevant details.
     * @param member
     */
    private void loadPersonPage(Person member) {
        String url = FXML + member.getName().fullName;
        setUpMemberDetails(member);
        loadPage(url);
    }


    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        loadPage(DEFAULT_PAGE.toExternalForm());
    }
}
