package Java_Core_Proff.lesson7.TestsStart;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.*;

public class MainCalcTest {
    public static void main(String[] args) throws Exception {
        int countBefore = 0;
        int countAfter = 0;
        int max = 0;
        ArrayList<Method> g = new ArrayList<Method>();
        Class calc = CalculatorTest.class;
        CalculatorTest calculatorTest = new CalculatorTest();
        Method[] methods = calc.getDeclaredMethods();

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                countBefore++;
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                countAfter++;
            }

            if ((countAfter == 2) || (countBefore == 2)) throw new RuntimeException();

            if (m.isAnnotationPresent(Test.class)) {
                if (max < m.getAnnotation(Test.class).priotity()) {max = m.getAnnotation(Test.class).priotity();}
                g.add(m);
            }
        }

        int i = 0;

        while (i<=max) {
            for (Method method1 : g) {
                if (method1.getAnnotation(Test.class).priotity() == i) {
                    method1.invoke(calculatorTest);
                }
            }
            i++;
        }

    }
}
