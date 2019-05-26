package functions;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileMenu {
    private static File globalFile = null;

    //Новый файл
    public static void fileNew(TextArea codeArea){
        globalFile = null;
        codeArea.clear();
    }

    //Открытие файла
    public static void fileOpen(Stage stage, TextArea codeArea){
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Open Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("C++ files (*.cpp)", "*.cpp");//Расширение
        codeArea.clear();
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);//Указываем текущую сцену
        globalFile = file;
        if (file != null) {
            List<String> lines = null;
            try {
                lines = Files.readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(String line: lines){
                codeArea.appendText(line + "\n");
            }
        }
    }

    //Сохранение файла
    public static void fileSave(Stage stage, TextArea codeArea){
        if (globalFile != null){
            try(FileWriter writer = new FileWriter(globalFile, false))
            {
                String text = codeArea.getText();
                // запись всей строки
                writer.write(text);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            fileSaveAs(stage, codeArea);
        }
    }

    //Сохранить как
    public static void fileSaveAs(Stage stage, TextArea codeArea){
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("C++ files (*.cpp)", "*.cpp");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену
        globalFile = file;
        if (file != null) {
            try(FileWriter writer = new FileWriter(file, false))
            {
                String text = codeArea.getText();
                // запись всей строки
                writer.write(text);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}