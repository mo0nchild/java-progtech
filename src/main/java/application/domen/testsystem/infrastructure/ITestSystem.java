package application.domen.taskunittest.testsystem.infrastructure;

import application.domen.taskunittest.testsystem.TestClass;

import java.lang.reflect.InvocationTargetException;

public interface ITestSystem {
    public void runTest(Class<? extends TestClass> testClass) throws ReflectiveOperationException;
}
