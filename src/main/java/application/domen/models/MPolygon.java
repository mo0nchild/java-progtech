package application.domen.models;

import application.domen.models.interfaces.PointContaining;
import application.domen.models.interfaces.Resizable;
import javafx.geometry.Point2D;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.batik.ext.awt.geom.Polygon2D;

import java.util.ArrayList;
import java.util.Arrays;

@NoArgsConstructor
@Getter
@Setter
public class MPolygon implements Resizable, PointContaining {
    private Point2D[] points = new Point2D[] {};
    private int kpoint = 0;
    private double width = 0.0, height = 0.0;
    @Override
    public boolean contains(double x, double y) {
        var polygonShape = new Polygon2D();
        for (var item : this.points) {
            polygonShape.addPoint((float)item.getX(), (float)item.getY());
        }
        return polygonShape.contains(x, y);
    }
    @Override
    public void resize(double scale) {
        for (var i = 0; i < points.length; i++) {
            this.points[i] = new Point2D(points[i].getX() * scale, points[i].getY() * scale);
        }
    }
    @Override
    public String toString() {
        var result = "Полигон с вершинами: ";
        for (var item : this.points) {
            result = result.concat(String.format("(%s, %s) ", item.getX(), item.getY()));
        }
        return result;
    }
}
