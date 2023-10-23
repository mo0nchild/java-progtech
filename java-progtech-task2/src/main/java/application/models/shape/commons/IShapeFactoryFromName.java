package application.commons;

import application.models.Shape;

public interface IShapeFactoryFromName {
    public Shape createShape(String name) throws ShapeFactoryException;
}
