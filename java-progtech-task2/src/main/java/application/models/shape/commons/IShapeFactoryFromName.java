package application.models.shape.commons;

import application.models.shape.Shape;

public interface IShapeFactoryFromName {
    public Shape createShape(String name) throws ShapeFactoryException;
}
