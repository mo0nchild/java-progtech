package application.domen.testsystem.infrastructure;

public class TestAssertException extends RuntimeException {
    public TestAssertException(String message) {
        super(String.format("Assert Error: %s", message));
    }
}
