package application.components.builder.services.implement;

import application.components.builder.models.CoolImage;
import application.components.builder.services.interfaces.IBuilder;
import javafx.scene.image.Image;

public class CoolImageDirector implements IBuilder.IDirector<CoolImage> {
    private final Image image;
    public CoolImageDirector(Image image) {
        super();
        this.image = image;
    }
    public final Image getImage() { return this.image; }
    @Override
    public CoolImage construct(IBuilder<CoolImage> builder) {
        return builder.setImage(this.image).setDesign().setColor().setText().build();
    }
}
