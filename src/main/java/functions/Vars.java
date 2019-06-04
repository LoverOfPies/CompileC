package functions;

//Класс переменных для отрисовки
public class Vars {
    private String name;    //имя переменной
    private String type;    //тип переменной
    private String value;   //значение переменной
    private boolean isLink; //если указатель true
    private boolean isMas; //если массив true
    private Integer masLen; //длинна массива, если isMas true

    Vars(String name, String type){
        this.name = name;
        this.type = type;
        this.value = null;
        this.isLink = false;
        this.isMas = false;
        this.masLen = 0;
    }

    public void printData() {
        System.out.println("name: " + name);
        System.out.println("type: " + type);
        System.out.println("value: " + value);
        System.out.println("isLink: " + isLink);
        System.out.println("isLink: " + isLink);
        System.out.println("isMas: " + isMas);
        System.out.println("masLen: " + masLen);
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

    public boolean isMas() {
        return isMas;
    }

    public void setMasLen(Integer masLen) {
        this.masLen = masLen;
    }

    public Integer getMasLen() {
        return masLen;
    }
}