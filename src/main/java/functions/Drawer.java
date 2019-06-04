package functions;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

import static javafx.scene.paint.Color.BLACK;

public class Drawer {

    private final Canvas canvas;
    private List<Vars> vars;
    private int offset = 0;

    public Drawer(Canvas canvas, List<Vars> vars) {
        this.canvas = canvas;
        this.vars = vars;
    }

    public void draw() {
        offset = 0;
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.clearRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getWidth()
        );
        paint();
        canvas.autosize();
    }

    private void paint() {
        if (vars != null) {
            for (Vars var : vars) {
                if (var.isLink()) {
                    drawLink();
                } else if (var.isMas()) {
                    for (int i = 0; i < var.getMasLen(); i++) {
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

    private void drawBox(int masOff) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(10 + offset, 30 + masOff, 40, 40);
    }

    private void drawLink() {
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(30, 30, 40, 40);
    }

    private void drawValue(String value, int masOff) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(value, 13 + offset, 50 + masOff, 100);
    }

    private void drawName(String name) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeText(name, 15 + offset, 10, 100);
    }

    public void setVars(List<Vars> vars) {
        this.vars = vars;
    }
}