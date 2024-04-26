package application.domen.testsystem;

import application.domen.testsystem.infrastructure.TestAssertException;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public abstract class TestClass {
    private final List<String> logMessages = new ArrayList<>();
    public <VType> void assertThat(VType current, VType expected) {
        if(current.equals(expected)) return;
        var errorMessage = String.format("(current=%s), (expected=%s)", current, expected);
        throw new TestAssertException(errorMessage);
    }
    public void assertIfFalse(boolean expression) {
        if(!expression) throw new TestAssertException("(expression=false)");
    }
    private List<String> getLogsAndClear() {
        var resultLogs = new ArrayList<String>() {{ addAll(logMessages); }};
        this.logMessages.clear();
        return resultLogs;
    }
    public void logInfo(String message) { this.logMessages.add(message); }
}
