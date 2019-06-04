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
                String strNonType = line.substring(words[0].length());
                System.out.println(strNonType);
                if(isMas(strNonType)){
                    strNonType = strNonType.replaceAll("[\\s]{2,}", " ");
                    String strOnlyName = strNonType.substring(0, strNonType.indexOf("["));
                    String strMasLen= strNonType.substring(strNonType.indexOf("[")+1,strNonType.indexOf("]"));
                    Vars var = new Vars(strOnlyName, words[0]);
                    vars.add(var);
                    vars.get(varsCount).setMasLen(Integer.parseInt(strMasLen));
                    vars.get(varsCount).setMas(true);
                    if (strNonType.indexOf("=") > 0){
                        String strOnlyValue = strNonType.substring(strNonType.indexOf("=")+1);
                        for (String val : strOnlyValue.split(",")) {
                            vars.get(varsCount).addInMas(val);
                        }
                    }
                    varsCount++;
                } else {
                    strNonType = strNonType.replaceAll("[\\s]{2,}", " ");
                    String strOnlyName = strNonType.substring(0, strNonType.indexOf("="));
                    String strOnlyValue = strNonType.substring(strNonType.indexOf("=")+1);
                    String[] ws = strOnlyName.trim().split(",");
                    for(String w : ws) {
                        Vars var = new Vars(w, words[0]);
                        vars.add(var);
                        vars.get(varsCount).setValue(strOnlyValue);
                        varsCount++;
                    }
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
        Pattern pattern = Pattern.compile("\\S+\\[\\d+\\]");
        Matcher matcher = pattern.matcher(name);
        while(matcher.find()) {
            return true;
        }
        return false;
    }

    public ArrayList<Vars> getVars() {
        return vars;
    }
}