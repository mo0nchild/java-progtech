package application.actions;

import application.models.FieldModel;
import application.services.actions.IGenerateField;
import application.services.observers.ICellObservable;
import application.services.observers.ICellObserver;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class FieldAction implements ICellObservable {
    protected final static Integer CELL_SIZE = 50;
    protected final static Integer ITEM_OFFSET = 10, ITEM_SIZE = 30;
    private final IGenerateField fieldGenerator;
    private final Canvas canvas;
    private FieldModel fieldModel;
    private Integer fieldHeight = 0, fieldWidth = 0;

    private final ArrayList<ICellObserver> cellListeners = new ArrayList<>();

    public FieldAction(IGenerateField generator, Canvas canvas) {
        super();
        this.fieldGenerator = generator;
        this.canvas = canvas;
    }
    public final Integer getFieldHeight() { return this.fieldHeight; }
    public final Integer getFieldWidth() { return this.fieldWidth; }

    protected void canvasMouseClickedHandler(MouseEvent mouseEvent) {
        var cellIndex = (int)(mouseEvent.getX() / CELL_SIZE);
        var rowIndex = (int)(mouseEvent.getY() / CELL_SIZE);
        System.out.printf("cell: %s, %s\n", cellIndex, rowIndex);

        var currentCell = this.fieldModel.getRow(rowIndex).getCell(cellIndex);
        if (!currentCell.getCellChecked()) {
            this.notifyAllListeners(currentCell);
        }
        currentCell.setCellChecked(true);
        this.refreshField(new Point2D(cellIndex, rowIndex));
    }
    @Override
    public void addListener(ICellObserver listener) { this.cellListeners.add(listener); }
    @Override
    public void removeListener(ICellObserver listener) { this.cellListeners.remove(listener); }
    @Override
    public void notifyAllListeners(FieldModel.FieldCell cell) {

        for (var listener : this.cellListeners) { listener.handler(cell); }
    }
    public void setAllCellsChecked() {
        for (var rows : this.fieldModel) {
            for (var cell : rows) cell.setCellChecked(true);
        }
        this.refreshField(null);
    }
    public void setAllCellsNotChecked() {
        for (var rows : this.fieldModel) {
            for (var cell : rows) cell.setCellChecked(false);
        }
        this.refreshField(null);
    }
    protected void refreshField(Point2D selectedCell) {
        var context = this.canvas.getGraphicsContext2D();
        context.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        for (var rowIndex = 0; rowIndex < this.fieldModel.getRowsCount(); rowIndex++) {
            var currentRow = this.fieldModel.getRow(rowIndex);

            for (var cellIndex = 0; cellIndex < currentRow.getCellsCount(); cellIndex++) {
                var currentCell = currentRow.getCell(cellIndex);

                var positionX = cellIndex * CELL_SIZE;
                var positionY = rowIndex * CELL_SIZE;

                if (selectedCell != null && (int)selectedCell.getX() == cellIndex
                        && (int)selectedCell.getY() == rowIndex) {
                    context.setStroke(Color.CRIMSON);
                    context.setLineWidth(2);
                }
                else {
                    context.setStroke(Color.BLACK);
                    context.setLineWidth(1);
                }
                context.strokeRect(positionX, positionY, CELL_SIZE, CELL_SIZE);
                if (currentCell.getCellChecked()) {
                    context.setFill(switch (currentCell.getCellType()) {
                        case BOMB -> Color.DARKRED;
                        case MONET -> Color.GOLD;
                        default -> Color.TRANSPARENT;
                    });
                    context.fillOval(positionX + ITEM_OFFSET, positionY + ITEM_OFFSET, ITEM_SIZE, ITEM_SIZE);
                }
                else {
                    context.setFill(Color.DARKGREY);
                    context.fillRect(positionX + ITEM_OFFSET, positionY + ITEM_OFFSET, ITEM_SIZE, ITEM_SIZE);
                }
            }
        }
    }
    public void initializeField(int width, int height) {
        this.fieldHeight = height;
        this.fieldWidth = width;
        this.fieldModel = this.fieldGenerator.createField(width, height);

        this.refreshField(null);
        this.canvas.setOnMouseClicked(this::canvasMouseClickedHandler);
    }
}
