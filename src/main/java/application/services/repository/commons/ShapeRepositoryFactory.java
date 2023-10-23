package application.services.repository.commons;

import application.models.shape.Shape;
import application.models.shape.commons.IShapeFactoryFromName;
import application.models.shape.commons.ShapeFactoryFromName;
import application.services.repository.IRepository;
import application.services.repository.ShapeRepository;

public class ShapeRepositoryFactory implements IRepositoryFactory<Shape> {
    private final IShapeFactoryFromName shapeFactory;
    private final String filename;
    public ShapeRepositoryFactory(String filename, IShapeFactoryFromName factory) {
        super();
        this.shapeFactory = factory;
        this.filename = filename;
    }
    @Override
    public final IRepository<Shape> getFactory() {
        return new ShapeRepository(this.filename, this.shapeFactory);
    }
}
