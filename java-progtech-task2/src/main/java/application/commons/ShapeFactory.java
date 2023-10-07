package application.commons;

import application.models.Shape;

public interface ShapeFactory {
    public Shape createShape(String name) throws ShapeFactoryException;
}
