package application.models;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public final class Square extends Shape {

    private Double size = 0.0;

    public Square(Color border, Color background, Point2D position, double size) {
        super(border, background, position);
        this.size = size;
    }

    public final Double getSize() { return this.size; }
    public final void setSize(final Double value) { this.size = value; }

    @Override
    public void shapeDraw(@NotNull GraphicsContext context) throws Exception {
        if(super.getBorder() != null) {
            context.setStroke(super.getBorder());
            context.strokeRect(super.getX() - this.size / 2, super.getY() - this.size / 2, this.size, this.size);
        }
        context.setFill(super.getBackground());
        context.fillRect(super.getX() - this.size / 2, super.getY() - this.size / 2, this.size, this.size);
    }

    @Override
    public String toString() {
        return String.format("Позиция квадрата: (%.2f, %.2f)", super.getX(), super.getY());
    }
}
