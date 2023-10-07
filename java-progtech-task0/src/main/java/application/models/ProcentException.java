package application.models;

public class ProcentException extends Exception {

    private final String methodName;

    public ProcentException(String name, String message) {
        super(message);
        this.methodName = name;
    }

    public final String getMethodName() { return this.methodName; }
}
