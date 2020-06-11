package Java_Core_Proff.lesson6.ChooseFromArray;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayForChooseTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{8, 9, 17}, new int[]{1, 2, 15, 4, 56, 4, 35, 5, 4, 4, 8, 9, 17}},
                {new int[]{}, new int[]{1, 2, 3}},
                {new int[]{56}, new int[]{1, 2, 15, 4, 56}},
                {new int[]{8}, new int[]{4, 35, 5, 4, 4, 8}}
        });
    }

    private int[] resArray;
    private int[] firstArray;
    private ArrayForChoose arrayForChoose;

    public ArrayForChooseTest(int[] resArray, int[] firstArray) {
        this.resArray = resArray;
        this.firstArray = firstArray;
    }

    @Before
    public void initTest() {
        arrayForChoose = new ArrayForChoose();
    }

    @Test
    public void arrayForChooseMassTest() {
        Assert.assertArrayEquals(resArray, arrayForChoose.chooseFromArray(firstArray));
    }

}
