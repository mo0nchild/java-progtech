package application.components.imagecomponent.services.implement;

import application.components.imagecomponent.services.interfaces.Iterator;
import javafx.scene.image.Image;

import java.nio.file.Paths;

public class ImageIterator implements Iterator<Image> {
    private final String basePath;
    private final int max;
    private int current = 0;
    public ImageIterator(String basePath, int max) {
        super();
        this.basePath = basePath;
        this.max = max;
    }
    public final int getCurrent() { return this.current; }
    public final String getBasePath() { return this.basePath; }
    public final int getMax() { return this.max; }

    protected Image selectImage(int iterator) {
        return new Image(Paths.get(this.basePath + iterator + ".jpg").toUri().toString());
    }
    @Override
    public boolean hasNext() { return this.current >= 0 && this.current + 1 <= this.max; }
    @Override
    public boolean hasPrevious() {
        return !(this.current - 1 < 1);
    }
    @Override
    public Image next() {
        if (this.hasNext()) return this.selectImage(++this.current);
        this.current = 1;
        return this.selectImage(this.current);
    }
    @Override
    public Image preview() {
        if (this.hasPrevious())return this.selectImage(--current);
        this.current = this.max;
        return this.selectImage(this.current);
    }
}
