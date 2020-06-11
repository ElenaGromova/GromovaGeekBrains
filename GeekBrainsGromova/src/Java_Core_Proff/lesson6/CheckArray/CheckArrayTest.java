package Java_Core_Proff.lesson6.CheckArray;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class CheckArrayTest {
    private CheckArray checkArrayTest;

    @Before
    public void init(){
        checkArrayTest = new CheckArray();
    }

    @Test
    public void checkArrayTest1(){
        Assert.assertEquals(true, checkArrayTest.checkArray(new int[] {1, 1, 4, 4}));
    }

    @Test
    public void checkArrayTest2(){
        Assert.assertEquals(false, checkArrayTest.checkArray(new int[] {1, 1, 1, 1, 1}));
    }

    @Test
    public void checkArrayTest3(){
        Assert.assertEquals(false, checkArrayTest.checkArray(new int[] {4, 4, 4}));
    }

    @Test
    public void checkArrayTest4(){
        Assert.assertEquals(false, checkArrayTest.checkArray(new int[] {1, 3, 4, 4}));
    }
}