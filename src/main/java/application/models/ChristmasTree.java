package application.models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

public class ChristmasTree implements IChristmasTree {

    public ChristmasTree() {
        super();
    }
    @Override
    public void draw(Pane pane) {
        var leafPath = new Path() {{
            setFill(Color.GREEN);
            setFillRule(FillRule.EVEN_ODD);
            setStroke(Color.GREENYELLOW);
        }};
        leafPath.getElements().addAll(
                new MoveTo(100, 20),
                new LineTo(200, 140),
                new LineTo(0, 140),
                new ClosePath(),
                new MoveTo(120, 140),
                new LineTo(200, 220),
                new LineTo(0, 220),
                new LineTo(80, 140),
                new ClosePath()
        );
        var rectangle = new Rectangle(80, 220, 40, 40) {{
            setFill(Color.BROWN);
        }};
        pane.getChildren().addAll(leafPath, rectangle);
    }
}
