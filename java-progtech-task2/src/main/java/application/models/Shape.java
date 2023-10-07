package application.models;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.jetbrains.annotations.NotNull;

public abstract class Shape extends Object {

    private final Color border, background;
    private Double posX = 0.0, posY = 0.0;

    public Shape(Color border, Color background, Point2D position) {
        this.border = border;
        this.background = background;
        this.posX = position.getX();
        this.posY = position.getY();
    }

    public final Color getBorder() { return this.border; }
    public final Color getBackground() { return this.background; }

    public final Double getX() { return this.posX; }
    public final Double getY() { return this.posY; }

    public void setPosition(double x, double y) {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public String toString() {
        return String.format("Position: (%.2f, %.2f)\n", this.posX, this.posY);
    }

    public abstract void shapeDraw(@NotNull final GraphicsContext context) throws Exception;

}
