package application.commons;

import application.models.Circle;
import application.models.Shape;
import application.models.Square;
import application.models.Triangle;

public class ShapeFactoryFromName implements ShapeFactory {
    public ShapeFactoryFromName() { super(); }
    @Override
    public Shape createShape(String name) throws ShapeFactoryException {
        return switch (name) {
            case "Квадрат" -> new Square();
            case "Круг" -> new Circle();
            case "Треугольник" -> new Triangle();
            default -> throw new ShapeFactoryException(name, "Фигура не найдена");
        };
    }
}
