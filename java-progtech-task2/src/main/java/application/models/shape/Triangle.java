package application.models;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class Triangle extends Shape {

    public static Double defaultSize = 50.0;
    private Double size = 0.0;
    private Point2D[] vertexs = null;

    public Triangle(Color border, Color background, Point2D position, double size) {
        super(border, background, position);
        this.size = size;
        this.vertexs = this.calculateVertexs();
    }
    public Triangle() {
        this(Color.web("#D68ADB"), Color.web("#F2CCED"), Point2D.ZERO, Triangle.defaultSize);
    }

    private Point2D[] calculateVertexs() {
        return new Point2D[] {
                new Point2D(super.getX() + this.size / 2, super.getY() + this.size / (2 * Math.sqrt(3))),
                new Point2D(super.getX() - this.size / 2, super.getY() + this.size / (2 * Math.sqrt(3))),
                new Point2D(super.getX(), super.getY() - this.size / Math.sqrt(3)),
        };
    }

    public final Point2D[] getVertexs() { return this.vertexs; }
    public final Double getSize() { return this.size; }

    public final void setSize(final Double value) {
        this.size = value;
        this.vertexs = this.calculateVertexs();
    }

    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
        this.vertexs = this.calculateVertexs();
    }

    @Override
    public void shapeDraw(@NotNull GraphicsContext context) throws Exception {
        if (this.vertexs == null) throw new Exception("Вершины не были сформированы");
        final double[] positionX = new double[this.vertexs.length], positionY = new double[this.vertexs.length];

        for(int index = 0; index < this.vertexs.length; index++) {
            positionX[index] = this.getVertexs()[index].getX();
            positionY[index] = this.getVertexs()[index].getY();
        }
        if (super.getBorder() != null) {
            context.setStroke(super.getBorder());
            context.strokePolygon(positionX, positionY, this.vertexs.length);
        }
        context.setFill(super.getBackground());
        context.fillPolygon(positionX, positionY, this.vertexs.length);
    }

    @Override
    public String toString() {
        return String.format("Позиция треугольника: (%.2f, %.2f)", super.getX(), super.getY());
    }
}
