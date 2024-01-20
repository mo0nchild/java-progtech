package application.models;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Map;

public abstract class TreeDecorator implements IChristmasTree {
    private final IChristmasTree tree;
    public TreeDecorator(IChristmasTree tree) {
        super();
        this.tree = tree;
    }
    public IChristmasTree getTree() { return this.tree; }
    @Override
    public void draw(Pane pane) { tree.draw(pane); }
    public final String getState() {
        if (this.tree instanceof TreeDecorator decorator) {
            return String.format("%s\n%s", this.toString(), decorator.getState());
        }
        return String.format("%s\n", this.toString());
    }
}
