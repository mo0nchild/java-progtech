package application.domen.taskunittest.testsystem;

import application.domen.taskunittest.testsystem.infrastructure.ITestSystem;
import application.domen.taskunittest.testsystem.infrastructure.TestMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestSystem implements ITestSystem {
    public TestSystem() { super(); }
    @Override
    public void runTest(Class<? extends TestClass> testClass) throws ReflectiveOperationException {
        var testMethods = new ArrayList<Method>();
        for (var item : testClass.getMethods()) {
            if (item.isAnnotationPresent(TestMethod.class)) testMethods.add(item);
        }
        if (testMethods.isEmpty()) return;
        var testObject = testClass.getConstructor(new Class[] {}).newInstance();
        testMethods.forEach(method -> {
            try { method.invoke(testObject); }
            catch (ReflectiveOperationException error) { throw new RuntimeException(error); }
            catch ()
        });
    }
}
