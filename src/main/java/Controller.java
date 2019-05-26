import functions.FileMenu;
import functions.Reader;
import functions.Writer;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Controller{
    @FXML
    private javafx.scene.control.MenuBar mainMenu;
    @FXML
    private javafx.scene.control.TextArea codeArea;
    @FXML
    private javafx.scene.layout.AnchorPane canvasPane;
    @FXML
    private Canvas regularCanvas;
    @FXML
    private void initialize() {
        GraphicsContext gc = regularCanvas.getGraphicsContext2D();

        ReadOnlyDoubleProperty widthProperty = canvasPane.widthProperty();
        ReadOnlyDoubleProperty heightProperty = canvasPane.heightProperty();
        regularCanvas.widthProperty().bind(widthProperty);
        regularCanvas.heightProperty().bind(heightProperty);

        regularCanvas.widthProperty().addListener((obs, oldWidth, newWidth) -> drawArea(gc));
        regularCanvas.heightProperty().addListener((obs, oldHeight, newHeight) -> drawArea(gc));

        drawArea(gc);
    }

    private void drawArea(GraphicsContext gc) {

    }

    //События меню файл
    public void fileNew(ActionEvent actionEvent) {
        FileMenu.fileNew(codeArea);
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
        Reader reader = new Reader();
        reader.readCode(codeArea);

        GraphicsContext gc = regularCanvas.getGraphicsContext2D();

        Writer writer = new Writer(gc, reader.getVars());
        writer.drawVars();
    }


}