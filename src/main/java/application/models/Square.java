package application.models;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public final class Square extends Shape {
    public Square() {
        super(new Point2D(0, 0));
    }
    @Override
    public void shapeDraw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(super.getBackground());
        graphicsContext.fillRect(super.getX() - this.getSize() / 2,
                super.getY() - this.getSize() / 2,
                this.getSize(), this.getSize());
    }
    @Override
    public String toString() { return "Квадрат"; }
}
