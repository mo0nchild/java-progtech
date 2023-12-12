package application.services.implement;

import application.models.CoolImage;
import application.services.interfaces.IBuilder;
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
