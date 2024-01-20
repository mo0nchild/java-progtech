package application.services;

import application.models.IChristmasTree;
import application.models.TreeDecorator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Girland extends TreeDecorator {
    private static final Random Random = new Random();
    private static Integer Previous = 0;
    private ArrayList<Color> colors = new ArrayList<>() {{
            add(Color.AQUAMARINE);
            add(Color.CRIMSON);
            add(Color.PURPLE);
            add(Color.CADETBLUE);
            add(Color.DEEPSKYBLUE);
            add(Color.ORANGE);
    }};
    public Girland(IChristmasTree tree) {
        super(tree);
    }
    @Override
    public void draw(Pane paneLight) {
        super.draw(paneLight);
        this.drawWithGirland(paneLight);
    }
    private final Integer getRandomInt(int min, int max) {
        var randomResult = Previous;
        while(randomResult.compareTo(Previous) == 0) {
            randomResult = Girland.Random.nextInt((max - min) + 1) + min;
        }
        return (Previous = randomResult);
    }
    private final Integer getRandomX(int posY) {
        return posY < 140
                ? this.getRandomInt((int)((140 - posY) / 1.2), (int)((posY + 100) / 1.2))
                : this.getRandomInt(220 - posY, posY - 20);
    }
    private void drawWithGirland(Pane paneLight) {
        for (var index = 0; index < this.getRandomInt(10, 20); index++) {
            var posY = this.getRandomInt(60, 210);
            var posX = this.getRandomX(posY);

            var color = this.colors.get(this.getRandomInt(0, this.colors.size() - 1));
            var circle = new Circle(posX, posY, 10, color);
            paneLight.getChildren().addAll(circle);
        }
    }
}
