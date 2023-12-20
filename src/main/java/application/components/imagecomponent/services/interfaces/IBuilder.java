package application.components.builder.services.interfaces;

import javafx.scene.image.Image;

public interface IBuilder<TItem> {
    public IBuilder<TItem> setImage(Image image);
    public IBuilder<TItem> setText();
    public IBuilder<TItem> setDesign();
    public IBuilder<TItem> setColor();

    public interface IDirector<TItem> { public TItem construct(IBuilder<TItem> builder);}
    public TItem build();
}
