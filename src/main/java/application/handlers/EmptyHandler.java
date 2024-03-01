package application.handlers;

import application.models.FieldModel;
import application.models.HandlerModel;
import application.services.handlers.HandlerBase;
import application.services.handlers.IHandler;

public class EmptyHandler extends HandlerBase {
    public EmptyHandler(IHandler processor) {
        super(processor);
    }
    @Override
    public HandlerResponse handle(HandlerModel request) {
        if(request.getCellType() == FieldModel.FieldCellType.EMPTY) {
            return HandlerResponse.HANDLED;
        }
        else if (this.exitsProcessor()) return this.getProcessor().handle(request);
        else return HandlerResponse.NOT_HANDLED;
    }
}
