import analize.CodeAreaReader;
import analize.Drawer;
import functions.FileMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    public ScrollPane scrollPane;
    @FXML
    private MenuBar mainMenu;
    @FXML
    private TextArea codeArea;
    @FXML
    private AnchorPane canvasPane;
    @FXML
    private Canvas canvas;

    private Drawer drawer;
    private CodeAreaReader reader;

    @FXML
    private void initialize() {
        scrollPane.setContent(canvas);
        this.reader = new CodeAreaReader(codeArea);
        this.drawer = new Drawer(canvas, reader.getVars());
        registerEventsForGraphicsContext();
        drawer.draw();
    }

    /**
     * Метод регистрации событий для контекста графики
     */
    private void registerEventsForGraphicsContext() {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                gc.getCanvas().widthProperty().setValue(newValue);
                drawer.draw();
            }
        });
        scrollPane.heightProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.setHeight((Double) newValue);
            drawer.draw();
        }));
    }

    //События меню файл
    public void fileNew(ActionEvent actionEvent) {
        FileMenu.fileNew(codeArea);
        String t1 = "abc[23], das[22]";
        Pattern pattern = Pattern.compile("\\[\\d+\\]");
        Matcher matcher = pattern.matcher(t1);
        int count = 0;
        while (matcher.find()) {
            count++;
            String substr2 = t1.substring(matcher.start() + 1, matcher.end() - 1);
        }

    }

    public void fileOpen(ActionEvent actionEvent) {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        FileMenu.fileOpen(stage, codeArea);
    }

    public void fileSave(ActionEvent actionEvent) {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        FileMenu.fileSave(stage, codeArea);
    }

    public void fileSaveAs(ActionEvent actionEvent) {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        FileMenu.fileSaveAs(stage, codeArea);
    }

    public void fileExit(ActionEvent actionEvent) {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    //События меню выполнить
    public void runFull(ActionEvent actionEvent) {
        reader.readCode(codeArea);
        drawer.setVars(reader.getVars());
        drawer.draw();
    }

    public void runStep(ActionEvent actionEvent) {
        String text = "^fd";
        text = text.replaceAll(" ", "");
        Pattern pattern = Pattern.compile("^\\S+$\\[\\d+\\]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(text.substring(matcher.start() + 1, matcher.end()));
        }
        pattern = Pattern.compile("^\\S+\\[\\d+\\]?=");
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(text.substring(matcher.start() + 1, matcher.end() - 1));
        }
    }
}