package application.models.shape;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public final class Circle extends Shape {

    public static Double defaultSize = 50.0;
    private Double size = 0.0;

    public Circle(Color border, Color background, Point2D position, double size) {
        super(border, background, position, "Круг");
        this.size = size;
    }
    public Circle() {
        this(Color.web("#D68ADB"), Color.web("#F2CCED"), Point2D.ZERO, Triangle.defaultSize);
    }

    public final Double getSize() { return this.size; }
    public final void setSize(final Double value) { this.size = value; }

    @Override
    public void shapeDraw(GraphicsContext context) throws Exception {
        if (super.getBorder() != null) {
            context.setStroke(super.getBorder());
            context.strokeOval(super.getX() - this.size / 2, super.getY() - this.size / 2, this.size, this.size);
        }
        context.setFill(super.getBackground());
        context.fillOval(super.getX() - this.size / 2, super.getY() - this.size / 2, this.size, this.size);
    }

    @Override
    public String toString() {
        return String.format("Позиция круга: (%.2f, %.2f)", super.getX(), super.getY());
    }

}
