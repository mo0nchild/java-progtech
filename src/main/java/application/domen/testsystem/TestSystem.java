package application.domen.testsystem;

import application.domen.testsystem.infrastructure.ITestSystem;
import application.domen.testsystem.infrastructure.TestAssertException;
import application.domen.testsystem.infrastructure.TestMethod;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TestSystem implements ITestSystem {
    private List<Method> getTestMethods(Class<? extends TestClass> testClass) {
        var testMethods = new ArrayList<Method>();
        for (var item : testClass.getMethods()) {
            if (item.isAnnotationPresent(TestMethod.class)) testMethods.add(item);
        }
        return testMethods;
    }
    private String getTestMethodName(Method method) {
        var methodName = method.getDeclaredAnnotation(TestMethod.class).name();
        return methodName.isEmpty() ? method.getName() : methodName;
    }
    @SuppressWarnings("unchecked")
    private List<String> getLogsFromMethod(Object testClass) throws ReflectiveOperationException {
        var logMethod = TestClass.class.getDeclaredMethod("getLogsAndClear");
        logMethod.setAccessible(true);
        return (List<String>) logMethod.invoke(testClass);
    }
    @Override
    public void runTest(Class<? extends TestClass> testClass) throws ReflectiveOperationException {
        var testMethods = this.getTestMethods(testClass);
        if (testMethods.isEmpty()) return;
        var testObject = testClass.getConstructor(new Class[] {}).newInstance();

        System.out.printf("[UnitTest for %s class]\n", testClass.getSimpleName());
        for(var method : testMethods) {
            var methodName = this.getTestMethodName(method);
            try {
                method.invoke(testObject);
                System.out.printf("+ [%s]: { Method test complete }\n", methodName);
            }
            catch (InvocationTargetException error) {
                if(!(error.getCause() instanceof TestAssertException)) throw new RuntimeException(error);
                System.out.printf("- [%s]: { %s }\n", methodName, error.getCause().getMessage());
            }
            catch (ReflectiveOperationException error) { throw new RuntimeException(error); }
            this.getLogsFromMethod(testObject).forEach(message -> {
                System.out.printf("\t[LogInfo]: ( %s )\n", message);
            });
        };
    }
}
