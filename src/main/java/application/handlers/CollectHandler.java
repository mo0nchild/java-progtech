package application.handlers;

import application.models.FieldModel;
import application.models.HandlerModel;
import application.services.handlers.HandlerBase;
import application.services.handlers.IHandler;

public class CollectHandler extends HandlerBase {

    public CollectHandler(IHandler processor) {
        super(processor);
    }
    @Override
    public HandlerResponse handle(HandlerModel request) {
        if(request.getCellType() == FieldModel.FieldCellType.MONET) {
            request.getRequestState().addMonet();
            return HandlerResponse.HANDLED;
        }
        else if (this.exitsProcessor()) return this.getProcessor().handle(request);
        else return HandlerResponse.NOT_HANDLED;
    }
}
