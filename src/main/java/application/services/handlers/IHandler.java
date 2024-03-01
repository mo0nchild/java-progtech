package application.services.handlers;

import application.models.HandlerModel;

public interface IHandler {
    public enum HandlerResponse { HANDLED, NOT_HANDLED }
    public HandlerResponse handle(HandlerModel request);
}
