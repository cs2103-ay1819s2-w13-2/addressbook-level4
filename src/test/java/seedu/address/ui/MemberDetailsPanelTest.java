package seedu.address.ui;

import static guitests.guihandles.WebViewUtil.waitUntilBrowserLoaded;
import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.MemberDetailsPanelHandle;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.model.person.Person;

public class MemberDetailsPanelTest extends GuiUnitTest {
    private SimpleObjectProperty<Person> selectedPerson = new SimpleObjectProperty<>();
    private MemberDetailsPanel memberDetailsPanel;
    private MemberDetailsPanelHandle memberDetailsPanelHandle;

    @Before
    public void setUp() {
        guiRobot.interact(() -> memberDetailsPanel = new MemberDetailsPanel(selectedPerson));
        uiPartRule.setUiPart(memberDetailsPanel);

        memberDetailsPanelHandle = new MemberDetailsPanelHandle(memberDetailsPanel.getRoot());
    }

    @Test
    public void display() throws Exception {
        // default web page
        assertEquals(MemberDetailsPanel.DEFAULT_PAGE, memberDetailsPanelHandle.getLoadedUrl());

        // associated web page of a person
        guiRobot.interact(() -> selectedPerson.set(ALICE));
        URL expectedPersonUrl = new URL(MemberDetailsPanel.FXML + ALICE.getName().fullName.replaceAll(" ", "%20"));

        waitUntilBrowserLoaded(memberDetailsPanelHandle);
        assertEquals(expectedPersonUrl, memberDetailsPanelHandle.getLoadedUrl());
    }
}
