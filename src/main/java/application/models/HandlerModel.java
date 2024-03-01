package application.models;

import java.io.Serializable;

public class HandlerModel {
    private final FieldModel.FieldCellType cellType;
    private RequestState requestState = new RequestState(0);

    public HandlerModel(FieldModel.FieldCellType type, RequestState state) {
        this(type);
        this.requestState = state;
    }
    public HandlerModel(FieldModel.FieldCellType type) {
        super();
        this.cellType = type;
    }
    public static class RequestState implements Serializable {
        private Integer monetCount = 0;
        private Boolean termination = false;

        public RequestState(int monet) {
            super();
            this.monetCount = monet;
        }
        public final Integer getMonetCount() { return this.monetCount; }
        public final Boolean getTermination() { return this.termination; }
        public final void addMonet() { this.monetCount++; }
        public final void removeMonet() {
            this.monetCount = this.monetCount <= 0 ? 0 : this.monetCount - 1;
        }
        public final void setTermination() { this.termination = true; }
    }

    public final RequestState getRequestState() { return this.requestState; }
    public final FieldModel.FieldCellType getCellType() { return this.cellType; }


}
