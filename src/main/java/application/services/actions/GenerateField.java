package application.services.actions;

import application.models.FieldModel;

import java.util.ArrayList;
import java.util.Random;

public class GenerateField implements IGenerateField {
    private static final Random RANDOM = new Random();
    public GenerateField() { super(); }
    @Override
    public FieldModel createField(int width, int height) {
        var fieldResult = new ArrayList<FieldModel.FieldRow>();
        for(var row = 0; row < height; row++) {
            var newFieldRow = new FieldModel.FieldRow();
            for(var col = 0; col < width; col++) {
                var randomValue = GenerateField.RANDOM.nextInt(FieldModel.FieldCellType.values().length);
                var cellType = FieldModel.FieldCellType.values()[randomValue];
                newFieldRow.addCell(new FieldModel.FieldCell(cellType));
            }
            fieldResult.add(newFieldRow);
        }
        return new FieldModel(fieldResult);
    }
}
