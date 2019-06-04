package analize;

import functions.Vars;
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
                    for (int i = 0; i<var.getMasLen(); i++){
                        int masOff = i * 40;
                        drawBox(masOff);
                        drawValue(var.getValue(), masOff);
                    }
                    offset += 80;
                } else {
                    drawBox(0);
                    drawName(var.getName());
                    drawValue(var.getValue(), 0);
                    offset += 80;
                }
            }
        }
    }

    private void drawBox(int masOff){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(10 + offset, 30 + masOff, 40, 40);
    }

    private void drawLink(){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(30, 30, 40, 40);
    }

    private void drawValue(String value, int masOff){
        gc.fillText(value, 13 + offset, 50 + masOff, 100);
    }

    private void drawName(String name){
        gc.strokeText(name, 15 + offset, 10, 100);
    }

    private void drawMas(){

    }
}