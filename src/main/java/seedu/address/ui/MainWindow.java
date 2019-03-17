package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private Model model;

    // Independent Ui parts residing in this Ui container
    private BrowserPanel browserPanel;
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane browserPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private TextArea areaPlaceholder;

    private DemoListPanel demoListPanel;

    public MainWindow(Stage primaryStage, Logic logic, Model model) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.model = model;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        browserPanel = new BrowserPanel(logic.selectedPersonProperty());
        browserPlaceholder.getChildren().add(browserPanel.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), logic.selectedPersonProperty(),
                logic::setSelectedPerson);
        demoListPanel = new DemoListPanel();
        personListPanelPlaceholder.getChildren().add(demoListPanel.getRoot());
        //personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath(), logic.getAddressBook());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, logic.getHistory());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        TextArea area111Placeholder = new TextArea("111");
        TextArea area222Placeholder = new TextArea("222");

        //this.setAreaPlaceholder(logic.getModel());

    }

    public void setAreaPlaceholder(Model model) {
        System.out.println("UI Set Area Placeholder Triggered, mode is " + model.getMode());
        TextArea area111Placeholder = new TextArea("111");
        TextArea area222Placeholder = new TextArea("222");
        if(model.getMode() == 0){
            this.areaPlaceholder.setText(area111Placeholder.getText());
            //personListPanelPlaceholder.getChildren().removeAll(demoListPanel.getRoot());
            //personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
            personListPanelPlaceholder.getChildren().removeAll(personListPanel.getRoot());
            personListPanelPlaceholder.getChildren().add(demoListPanel.getRoot());
        }else{
            this.areaPlaceholder.setText(area222Placeholder.getText());
            //personListPanelPlaceholder.getChildren().removeAll(personListPanel.getRoot());
            //personListPanelPlaceholder.getChildren().add(demoListPanel.getRoot());
            personListPanelPlaceholder.getChildren().removeAll(demoListPanel.getRoot());
            personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        }
    }


    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            // maybe can use boolean
            if(model.getMode() == 0){
                System.out.println("in mainwindow.executecommand, mode " + model.getMode());
                this.setAreaPlaceholder(model);
            }else{
                System.out.println("in mainwindow.executecommand, mode " + model.getMode());
                this.setAreaPlaceholder(model);
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
