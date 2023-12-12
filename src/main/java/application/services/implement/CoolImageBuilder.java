package application.services.implement;

import application.models.CoolImage;
import application.services.interfaces.IBuilder;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Random;

public class CoolImageBuilder implements IBuilder<CoolImage> {
    private final CoolImage coolImage = new CoolImage(250, 200);
    private final Text text = new Text();

    private final List<String> texts;
    private final List<Color> colors;
    public CoolImageBuilder(List<String> texts, List<Color> colors) {
        super();
        this.colors = colors;
        this.texts = texts;
    }
    public final List<Color> getColors() { return this.colors; }
    public final List<String> getTexts() { return this.texts; }

    @Override
    public IBuilder<CoolImage> setImage(Image image) { this.coolImage.addImage(image); return this; }
    @Override
    public IBuilder<CoolImage> setText() {
        text.setText(texts.get(new Random().nextInt(0, texts.size())));
        this.coolImage.addText(text); return this;
    }
    @Override
    public IBuilder<CoolImage> setColor(){
        text.setFill(colors.get(new Random().nextInt(0,colors.size())));
        return this;
    }
    @Override
    public IBuilder<CoolImage> setDesign(){
        text.fontProperty().setValue(Font.font("Verdana", FontWeight.BOLD, 12));
        return this;
    }
    @Override
    public CoolImage build() {
        return this.coolImage;
    }
}
