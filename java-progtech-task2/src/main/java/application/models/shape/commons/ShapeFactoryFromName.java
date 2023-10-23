package application.commons;

import application.models.Circle;
import application.models.Shape;
import application.models.Square;
import application.models.Triangle;

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
