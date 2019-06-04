package functions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Writer {
    private GraphicsContext gc;
    private ArrayList<Vars> vars;
    private int offset = 0;

    public Writer(GraphicsContext gc, ArrayList<Vars> vars){
        this.gc = gc;
        this.vars = vars;
    }

    public void drawVars(){
        if (vars != null){
            for (Vars var : vars){
                if (var.isLink()){
                    drawLink();
                } else if (var.isMas()){

                } else {
                    drawBox();
                    drawName(var.getName());
                    drawValue(var.getValue());
                    offset += 80;
                }
            }
        }
    }

    private void drawBox(){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(10 + offset, 30, 40, 40);
    }

    private void drawLink(){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(30, 30, 40, 40);
    }

    private void drawValue(String value){
        gc.fillText(value, 13 + offset, 50, 100);
    }

    private void drawName(String name){
        gc.strokeText(name, 15 + offset, 10, 100);
    }

    private void drawMas(){

    }
}