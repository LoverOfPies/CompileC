package functions;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {

    private ArrayList<Vars> vars = new ArrayList<Vars>(); ///массив объектов для отрисовки

    public void readCode(TextArea codeArea){
        String text = codeArea.getText();
        String[] words;
        String[] lines =  text.trim().split(";"); //чтение строк
        int varsCount = 0;
        for(String line : lines){
            words = line.trim().split("\\s+"); //чтение слов
            if (words[0] != null && isType(words[0])){
                if (words[1] != null && isNewVar(words[1])){
                    Vars var = new Vars(words[1], words[0]);
                    vars.add(var); //новая переменная Имя Тип
                    if (isMas(words[1])){
                        vars.get(varsCount).setMas(true);
                        String t1 = "abc[12], das[22]";
                        Pattern pattern = Pattern.compile("А.+а");
                        Matcher matcher = pattern.matcher(t1);
                        while (matcher.find()) {
                            System.out.println(text.substring(matcher.start(), matcher.end()));
                        }
                    }
                    if (words[2] != null && words[3] != null && words[2].equals("=")){
                        vars.get(varsCount).setValue(words[3]);
                    }
                    varsCount++;
                }
            }
            if (words[0] != null && !isNewVar(words[0]) && !isType(words[0])){

            }
        }
        for (int i = 0; i < varsCount; i++){
            vars.get(i).printData();
            System.out.println();
        }
    }

    //проверка на тип
    boolean isType(String word){
        String[] types = {"bool", "char", "int", "long", "float", "double", "string"};
        for (String type: types){
            if (word.equals(type)){
                return true;
            }
        }
        return false;
    }

    //Переменная существует?
    boolean isNewVar(String name){
        if (vars != null){
            for (Vars var: vars){
                if (var != null && var.getName() == name){
                    return false;
                }
            }
        }
        return true;
    }


    boolean isMas(String name){
        int indexStart = name.indexOf("[");
        int indexLast = name.indexOf("[");
        if (indexStart == - 1){
            return false;
        }
        if (indexLast == - 1){
            return false;
        }
        return true;
    }

    public ArrayList<Vars> getVars() {
        return vars;
    }
}