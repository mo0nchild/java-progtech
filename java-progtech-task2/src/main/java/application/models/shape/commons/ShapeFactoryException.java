package application.models.shape.commons;

public class ShapeFactoryException extends Exception {
    private final String shapeName;
    public ShapeFactoryException(String name, String message) {
        super(message);
        this.shapeName = name;
    }
    public final String getShapeName() { return this.shapeName; }
}
