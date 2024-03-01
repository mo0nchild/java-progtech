package application.services.handlers;

import application.models.HandlerModel;

public abstract class HandlerBase implements IHandler {
    private final IHandler processor;

    public HandlerBase(IHandler processor) {
        super();
        this.processor = processor;
    }
    protected final IHandler getProcessor() { return this.processor; }
    protected final boolean exitsProcessor() { return this.processor != null; }
    @Override
    public abstract HandlerResponse handle(HandlerModel request);
}
