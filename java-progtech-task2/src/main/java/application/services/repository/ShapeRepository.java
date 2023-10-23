package application.services;

import application.models.Shape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

public class ShapeRepository implements IRepository<Shape> {

    private final ArrayDeque<Shape> items = new ArrayDeque<>();
    private final String filename;

    public ShapeRepository(String filename) {
        super();
        this.filename = filename;
    }

    public final String getFileName() { return this.filename; }

    @Override
    public final Iterable<Shape> getAll() { return this.items; }

    @Override
    public final void putItem(Shape shape) throws RepositoryException {
        this.items.push(shape);
        this.saveState();
    }

    @Override
    public final Shape getItem() { return this.items.pop(); }

    private String generateString(int id, Shape shape) {
        return String.format("[%s] back:[%s] border:[%s] x:[%s] y:[%s]",
                String.valueOf(id),
                shape.getBackground().toString(),
                shape.getBorder().toString(),
                shape.getX(), shape.getY()
        );
    }

    @Override
    public final void saveState() throws RepositoryException {
        try (var stream = new BufferedWriter(new FileWriter(this.filename))) {
            for (var index = 0; index < this.items.size(); index++) {
                stream.write(this.generateString(index, (Shape)this.items.toArray()[index]));

            }
            stream.flush();
        }
        catch (IOException error) {
            throw new RepositoryException(error.getMessage(), "saveState");
        }
    }
}
