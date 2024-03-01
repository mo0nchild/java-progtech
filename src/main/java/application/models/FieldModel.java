package application.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FieldModel implements Serializable, Iterable<FieldModel.FieldRow> {
    private final ArrayList<FieldRow> rowsList;
    public FieldModel(ArrayList<FieldRow> rows) {
        super();
        this.rowsList = rows;
    }
    public enum FieldCellType { BOMB, MONET, EMPTY }
    public static class FieldCell implements Cloneable {
        private final FieldCellType cellType;
        private Boolean cellChecked = false;

        public FieldCell(FieldCellType type) {
            super();
            this.cellType = type;
        }
        public final FieldCellType getCellType() { return this.cellType; }
        public final void setCellChecked(Boolean value) { this.cellChecked = value; }
        public final Boolean getCellChecked() { return this.cellChecked; }

    }
    public static class FieldRow implements Iterable<FieldCell> {
        private final ArrayList<FieldCell> cellsList = new ArrayList<>();

        public void addCell(FieldCell cell) { this.cellsList.add(cell); }
        public void clear() { this.cellsList.clear(); }

        public final FieldCell getCell(Integer index) { return this.cellsList.get(index); }
        public final Integer getCellsCount() { return this.cellsList.size(); }
        @Override
        public Iterator<FieldCell> iterator() { return this.cellsList.iterator(); }
    }
    public final Integer getRowsCount() { return this.rowsList.size(); }
    public final FieldRow getRow(Integer index) { return this.rowsList.get(index); }
    @Override
    public Iterator<FieldRow> iterator() { return this.rowsList.iterator(); }
}
