package Java_Core_Proff.lesson7.TestsStart;

import org.junit.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class CalculatorTest {

    public int add(int a, int b) {
        return a + b;
    }

    @BeforeSuite
    public void init() {
        System.out.println("init");
    }

//    @BeforeSuite
//    public void init1() {
//        System.out.println("init");
//    }

    @Test(priotity = 3)
    public void testAdd1() {
        System.out.println("priotity 3");
        Assert.assertEquals(4, add(2,2));
    }

    @Test(priotity = 2)
    public void testAdd2() {
        System.out.println("priotity 2");
        Assert.assertEquals(5, add(2,3));
    }

    @Test
    public void testAdd3() {
        System.out.println("priotity 5");
        Assert.assertEquals(6, add(3,3));
    }

    @Test(priotity = 1)
    public void testAdd4() {
        System.out.println("priotity 1");
        Assert.assertEquals(8, add(4,4));
    }

    @Test(priotity = 1)
    public void testAdd5() {
        System.out.println("priotity 1");
        Assert.assertEquals(10,  add(3,7));
    }

    @Test(priotity = 4)
    public void testAdd6() {
        System.out.println("priotity 4");
        Assert.assertEquals(10, add(2,8));
    }

    @AfterSuite
    public void shutdown() {
        System.out.println("end");
    }

//    @AfterSuite
//    public void shutdown1() {
//        System.out.println("end");
//    }
}
