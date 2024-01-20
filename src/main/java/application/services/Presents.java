package application.services;

import application.models.IChristmasTree;
import application.models.TreeDecorator;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class Presents extends TreeDecorator {
    private static final java.util.Random Random = new Random();
    private final ArrayList<Color> colors = new ArrayList<>() {{
        add(Color.AQUAMARINE);
        add(Color.CRIMSON);
        add(Color.PURPLE);
        add(Color.CADETBLUE);
        add(Color.DEEPSKYBLUE);
        add(Color.ORANGE);
    }};
    private final ArrayList<Shape> shapes = new ArrayList<>();
    public Presents(IChristmasTree tree) {
        super(tree);
        for (int index = 0; index < 200; index += 40) {
            int size = this.getRandomInt(20, 40);
            this.shapes.addAll(this.createPresent(index, 260 - size, size));
        }
    }
    @Override
    public void draw(Pane panePresent) {
        super.draw(panePresent);
        this.drawWithPresents(panePresent);
    }
    private Collection<Shape> createPresent(int posX, int posY, int size) {
        var presentBox = new Rectangle(posX, posY, size, size) {{
            setFill(colors.get(getRandomInt(0, colors.size() - 1)));
        }};
        var width = (int)(size / 8.0);
        var presentPackage = new Path() {{
           setFill(colors.get(getRandomInt(0, colors.size() - 1)));
           setFillRule(FillRule.EVEN_ODD);
           setStroke(Color.TRANSPARENT);
           getElements().addAll(
                   new MoveTo(posX + (int)(size / 2.0) - width, posY),
                   new LineTo(posX + (int)(size / 2.0) - width, posY + (int)(size / 2.0) - width),
                   new LineTo(posX, posY + (int)(size / 2.0) - width),
                   new LineTo(posX, posY + (int)(size / 2.0) + width),
                   new LineTo(posX + (int)(size / 2.0) - width, posY + (int)(size / 2.0) + width),
                   new LineTo(posX + (int)(size / 2.0) - width, posY + size),
                   new LineTo(posX + (int)(size / 2.0) + width, posY + size),
                   new LineTo(posX + (int)(size / 2.0) + width, posY + (int)(size / 2.0) + width),
                   new LineTo(posX + size, posY + (int)(size / 2.0) + width),
                   new LineTo(posX + size, posY + (int)(size / 2.0) - width),
                   new LineTo(posX + (int)(size / 2.0) + width, posY + (int)(size / 2.0) - width),
                   new LineTo(posX + (int)(size / 2.0) + width, posY),
                   new ClosePath()
           );
        }};
        return Arrays.stream(new Shape[] { presentBox, presentPackage }).toList();
    }
    private final Integer getRandomInt(int min, int max) {
        return Presents.Random.nextInt((max - min) + 1) + min;
    }
    private void drawWithPresents(Pane panePresent) {
        for (var item : this.shapes) panePresent.getChildren().addAll(item);
    }
    @Override
    public String toString() { return "- Подарки"; }
}
