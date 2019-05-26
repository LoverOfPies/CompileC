package functions;

//Класс переменных для отрисовки
public class Vars {
    private String name;    //имя переменной
    private String type;    //тип переменной
    private String value;   //значение переменной
    public boolean isLink; //если указатель true
    public boolean isMas; //если массив true
    private String masLen; //длинна массива, если isMas true

    Vars(String name, String type){
        this.name = name;
        this.type = type;
        this.value = null;
        this.isLink = false;
        this.isMas = false;
    }

    public void printData() {
        System.out.println("name: " + name);
        System.out.println("type: " + type);
        System.out.println("value: " + value);
        System.out.println("isLink: " + isLink);
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
}