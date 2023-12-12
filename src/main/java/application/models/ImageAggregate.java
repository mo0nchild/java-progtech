package application.services.implement;

import application.services.interfaces.IAggregate;
import application.services.interfaces.Iterator;
import javafx.scene.image.Image;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ConcreteAggregate implements IAggregate<Image> {
    private final String path;
    public ConcreteAggregate(String path) {
        super();
        this.path = path;
    }
    public final String getPath() { return this.path; }
    private List<File> searchFiles(File dir) { return Arrays.stream(dir.listFiles()).toList(); }
    @Override
    public Iterator<Image> createIterator() {
        return new ImageIterator(this.path, this.searchFiles(Paths.get(this.path).toFile()).size());
    }
}
