package application.domen.taskunittest.testsystem;

import application.domen.taskunittest.testsystem.infrastructure.TestAssertException;

public abstract class TestClass {
    public TestClass() { super(); }
    public <VType> void assertThat(VType current, VType expected) {
        if(current.equals(expected)) return;
        var errorMessage = String.format("[Current]: %s, [Expected]: %s", current, expected);
        throw new TestAssertException(errorMessage);
    }
}
