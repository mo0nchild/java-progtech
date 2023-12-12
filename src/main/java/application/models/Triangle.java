package application.models;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public final class Triangle extends Shape {
    private Point2D[] vertexs = null;
    public Triangle() {
        super(new Point2D(0, 0));
        this.vertexs = this.calculateVertexs();
    }
    private Point2D[] calculateVertexs() {
        return new Point2D[] {
            new Point2D(super.getX() + this.getSize() / 2, super.getY() + this.getSize() / (2 * Math.sqrt(3))),
            new Point2D(super.getX() - this.getSize() / 2, super.getY() + this.getSize() / (2 * Math.sqrt(3))),
            new Point2D(super.getX(), super.getY() - this.getSize() / Math.sqrt(3)),
        };
    }
    public final Point2D[] getVertexs() { return this.vertexs; }
    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
        this.vertexs = this.calculateVertexs();
    }
    @Override
    public void shapeDraw(GraphicsContext context) {
        if (this.vertexs == null) return;
        final double[] positionX = new double[this.vertexs.length], positionY = new double[this.vertexs.length];

        for(int index = 0; index < this.vertexs.length; index++) {
            positionX[index] = this.getVertexs()[index].getX();
            positionY[index] = this.getVertexs()[index].getY();
        }
        context.setFill(super.getBackground());
        context.fillPolygon(positionX, positionY, this.vertexs.length);
    }

    @Override
    public String toString() { return "Треугольник"; }
}
