package application.models;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements Cloneable {
    public final static Double ShapeSize = 32.0;

    private Point2D position;
    private final Color background;
    private final Double size;
    public Shape(Point2D position) {
        super();
        this.position = position;
        this.background = Color.CRIMSON;
        this.size = Shape.ShapeSize;
    }
    public final Double getSize() {return this.size;}
    public final Double getX() { return this.position.getX(); }
    public final Double getY() { return this.position.getY(); }
    public final Color getBackground() { return this.background; }

    public void setPosition(double x, double y) { this.position = new Point2D(x, y); }
    public abstract void shapeDraw(GraphicsContext graphicsContext);
    @Override
    public Shape clone() {
        try { return (Shape)super.clone();}
        catch (CloneNotSupportedException error) {
            throw new RuntimeException(error.getMessage());
        }
    }
}
