package application.services.actions;

import application.models.FieldModel;

public interface IGenerateField {
    public FieldModel createField(int width, int height);
}
