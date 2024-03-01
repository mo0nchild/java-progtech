package application.handlers;

import application.models.FieldModel;
import application.models.HandlerModel;
import application.services.handlers.HandlerBase;
import application.services.handlers.IHandler;

public class LoseHandler extends HandlerBase {
    public LoseHandler(IHandler processor) {
        super(processor);
    }
    @Override
    public HandlerResponse handle(HandlerModel request) {
        if(request.getCellType() == FieldModel.FieldCellType.BOMB) {
            var state = request.getRequestState();
            state.removeMonet();
            state.setTermination();
            return HandlerResponse.HANDLED;
        }
        else if (this.exitsProcessor()) return this.getProcessor().handle(request);
        else return HandlerResponse.NOT_HANDLED;
    }
}
