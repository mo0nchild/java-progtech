package application.models;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Shape extends Object {
    private Double posX, posY;
    private Color border, background;
    private final String name;

    public Shape(Color border, Color background, Point2D position, String name) {
        this.border = border;
        this.background = background;
        this.posX = position.getX();
        this.posY = position.getY();
        this.name = name;
    }
    public final String getName() { return this.name; }

    public final Color getBorder() { return this.border; }
    public final Color getBackground() { return this.background; }

    public final Double getX() { return this.posX; }
    public final Double getY() { return this.posY; }

    public final void setPosition(double x, double y) {
        this.posX = x;
        this.posY = y;
    }
    public final void setBorder(Color value) { this.border = value; }
    public final void setBackground(Color value) { this.background = value; }

    @Override
    public String toString() {
        return String.format("Position: (%.2f, %.2f)\n", this.posX, this.posY);
    }

    public abstract void shapeDraw(final GraphicsContext context) throws Exception;

}
