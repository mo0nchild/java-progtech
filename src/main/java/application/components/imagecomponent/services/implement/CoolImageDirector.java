package application.components.imagecomponent.services.implement;

import application.components.imagecomponent.models.CoolImage;
import application.components.imagecomponent.services.interfaces.IBuilder;
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
