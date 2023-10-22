package application.services;

public class RepositoryException extends Exception {

    private final String actionName;

    public RepositoryException(String message, String action) {
        super(message);
        this.actionName = action;
    }

    public final String getActionName() { return this.actionName; }

}
