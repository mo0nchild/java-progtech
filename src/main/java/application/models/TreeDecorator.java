package application.services;

import application.models.IChristmasTree;
import javafx.scene.layout.Pane;

public abstract class TreeDecorator implements IChristmasTree {

    public TreeDecorator() {
        super();
    }
    @Override
    public abstract void draw(Pane pane);
}
