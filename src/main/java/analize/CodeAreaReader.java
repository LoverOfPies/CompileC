package analize;

import functions.Var;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeAreaReader {

    private static List<Var> vars = new ArrayList<>(); ///массив объектов для отрисовки
    private final TextArea codeArea;

    public CodeAreaReader(TextArea codeArea) {
        this.codeArea = codeArea;
        readCode(codeArea);
    }

    /*
    int a, b, c;
    int *a;
    char ^fd;
    int dfd[443];

     */
    public void readCode(TextArea codeArea) {
        vars.clear();
        String text = codeArea.getText().replaceAll("\n","");
        String words[];
        String[] lines = text.trim().split(";");
        //цикл по строкам (;)
        for (String line : lines) {
            line = line.trim();
            words = line.split("\\s+"); //чтение слов
            if (isType(words[0])) {
                String type = words[0];
                String strNonType = line.substring(words[0].length()).replaceAll(" ", "");
                checkIndex(strNonType, type);
                checkLink(strNonType, type);
                checkMas(strNonType, type);
            } else {
                //TO DO: продумать дальнейший алгоритм
            }
        }
        vars.forEach(x -> x.printData());
    }

    //проверка на название переменной
    boolean isNewVar(String name) {
        if (name == null)
            return false;
        if (vars != null) {
            for (Var var : vars) {
                if (var != null && var.getName().equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    //проверка на тип
    boolean isType(String word) {
        String[] types = {"bool", "char", "int", "long", "float", "double"};
        for (String type : types) {
            if (word.equals(type)) {
                return true;
            }
        }
        return false;
    }

    private void checkIndex(String text, String type) {
        String varName = null;
        Pattern pattern = Pattern.compile("^\\*\\S+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            varName = text.substring(matcher.start()+1, matcher.end());
        }
        pattern = Pattern.compile("^\\*\\S+?=");
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            varName = text.substring(matcher.start()+1, matcher.end()-1);
        }
        if (!isNewVar(varName))
            return;
        Var var = new Var(varName, type);
        var.setIndex(true);
        vars.add(var);
    }

    private void checkLink(String text, String type) {
        String varName = null;
        Pattern pattern = Pattern.compile("^\\^\\S+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            varName = text.substring(matcher.start() + 1, matcher.end());
        }
        pattern = Pattern.compile("^\\^\\S+?=");
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            varName = text.substring(matcher.start() + 1, matcher.end() - 1);
        }
        if (!isNewVar(varName))
            return;
        Var var = new Var(varName, type);
        var.setLink(true);
        vars.add(var);
    }

    private void checkMas(String text, String type) {
        String varName = null;
        Pattern pattern = Pattern.compile("^\\S+$\\[\\d+\\]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            varName = text.substring(matcher.start() + 1, matcher.end());
        }
        pattern = Pattern.compile("^\\S+\\[\\d+\\]?=");
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            varName = text.substring(matcher.start() + 1, matcher.end() - 1);
        }
        if (!isNewVar(varName))
            return;
        Var var = new Var(varName, "int");
        vars.add(var);
    }

    public List<Var> getVars() {
        return vars;
    }

    public TextArea getCodeArea() {
        return codeArea;
    }
}