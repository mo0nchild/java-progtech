package application.services.repository;

import application.models.shape.commons.IShapeFactoryFromName;
import application.models.shape.Shape;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.regex.Pattern;

public class ShapeRepository implements IRepository<Shape> {

    private final ArrayList<Shape> items = new ArrayList<>();
    private final File databaseFile;
    private final IShapeFactoryFromName shapeFactory;
    private static final Pattern pattern = Pattern.compile("\\w+:\\[(?<value>.+)]");

    public ShapeRepository(String filename, IShapeFactoryFromName factory) {
        super();
        this.databaseFile = new File(filename);
        this.shapeFactory = factory;

        if (!this.databaseFile.exists()) {
            try { if (this.databaseFile.createNewFile()) throw new IOException(); }
            catch (IOException error) {
                throw new RuntimeException(error);
            }
        }
        try (var stream = new BufferedReader(new FileReader(filename))) {
            var paramLine = "";
            while ((paramLine = stream.readLine()) != null) {
                this.items.add(this.generateShape(paramLine));
            }
        }
        catch (Exception error) { throw new RuntimeException(error); }
    }
    public final String getFileName() { return this.databaseFile.getName(); }

    @Override
    public Collection<Shape> getAll() throws RepositoryException { return this.items; }
    @Override
    public void putItem(Shape shape) throws RepositoryException { this.items.add(shape);}
    @Override
    public int getCount() throws RepositoryException { return this.items.size(); }
    private Shape generateShape(String value) throws Exception {
        var params = value.split("; ");

        Function<Integer, String> getValue = (Integer index) -> {
            var matcher = ShapeRepository.pattern.matcher(params[index]);
            if (!matcher.find()) return null;
            return matcher.group("value");
        };
        var shapeResult = this.shapeFactory.createShape(getValue.apply(1));
        shapeResult.setPosition(Double.parseDouble(getValue.apply(4)),
                Double.parseDouble(getValue.apply(5)));

        shapeResult.setBackground(Color.valueOf(getValue.apply(2)));
        shapeResult.setBorder(Color.valueOf(getValue.apply(3)));
        return shapeResult;
    }
    @Override
    public Shape getItem(int index) throws RepositoryException { return this.items.get(index); }
    @Override
    public void putAll(Collection<Shape> shapes) throws RepositoryException { this.items.addAll(shapes); }
    @Override
    public void removeAll() throws RepositoryException { this.items.clear(); }
    @Override
    public void removeItem(int index) throws RepositoryException { this.items.remove(index); }

    private final String generateString(int id, Shape shape) {
        return String.format("id:[%s]; name:[%s]; background:[%s]; border:[%s]; x:[%.2f]; y:[%.2f]",
                String.valueOf(id), shape.getName(),
                shape.getBackground().toString(), shape.getBorder().toString(),
                shape.getX(), shape.getY()
        );
    }
    @Override
    public void close() throws IOException {
        try (var stream = new PrintWriter(new FileWriter(this.databaseFile.getName()))) {
            for (var index = 0; index < this.items.size(); index++) {
                stream.println(this.generateString(index, (Shape)this.items.get(index)));
            }
            stream.flush();
        }
    }
}
