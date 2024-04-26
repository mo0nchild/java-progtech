package application.domen.testsystem.infrastructure;

import application.domen.testsystem.TestClass;

public interface ITestSystem {
    public void runTest(Class<? extends TestClass> testClass) throws ReflectiveOperationException;
}
