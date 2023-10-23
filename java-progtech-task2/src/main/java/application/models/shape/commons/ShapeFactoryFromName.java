package application.models.shape.commons;

import application.models.shape.Circle;
import application.models.shape.Shape;
import application.models.shape.Square;
import application.models.shape.Triangle;

import java.util.HashMap;

public class ShapeFactoryFromName implements IShapeFactoryFromName {

    interface ShapeCreating { public Shape create(); }
    private final HashMap<String, ShapeCreating> shapes = new HashMap<>();

    public ShapeFactoryFromName() {
        super();
        shapes.put("Квадрат", () -> new Square());
        shapes.put("Круг", () -> new Circle());
        shapes.put("Треугольник", () -> new Triangle());
    }
    @Override
    public Shape createShape(String name) throws ShapeFactoryException {
        if (!this.shapes.containsKey(name)){
            throw new ShapeFactoryException(name, "Фигура не найдена");
        }
        return this.shapes.get(name).create();
    }
}
