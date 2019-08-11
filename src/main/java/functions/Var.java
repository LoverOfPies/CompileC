package functions;

import java.lang.reflect.Array;
import java.util.ArrayList;

//Класс переменных для отрисовки
public class Var {
    private String name;    //имя переменной
    private String type;    //тип переменной
    private String value;   //значение переменной
    private boolean isLink; //если ссылка true
    private boolean isIndex; //если указатель true
    private boolean isMas; //если массив true
    private Integer masLen; //длинна массива, если isMas true
    private ArrayList<String> mas = new ArrayList();

    public Var(String name, String type) {
        this.name = name;
        this.type = type;
        this.value = null;
        this.isLink = false;
        this.isIndex = false;
        this.isMas = false;
        this.masLen = 0;
    }

    public void printData() {
        System.out.println("-------------------");
        System.out.println("name: " + name);
        System.out.println("type: " + type);
        System.out.println("value: " + value);
        System.out.println("isLink: " + isLink);
        System.out.println("isIndex: " + isIndex);
        System.out.println("isMas: " + isMas);
        System.out.println("masLen: " + masLen);
        System.out.println("masValue: ");
        for (String m : mas){
            System.out.print(m + " || ");
        }
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setMas(boolean mas) {
        isMas = mas;
    }

    public boolean isLink() {
        return isLink;
    }

    public void setLink(boolean link) {
        isLink = link;
    }

    public void setIndex(boolean index) {
        isIndex = index;
    }

    public boolean isMas() {
        return isMas;
    }

    public void setMasLen(Integer masLen) {
        this.masLen = masLen;
    }

    public Integer getMasLen() {
        return masLen;
    }

    public void addInMas(String el){
        mas.add(el);
    }
}