import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Lab12ThTest {

    @Test
    void houseRobbersBottomUp() {
        int[] arr={6, 7, 1, 3, 8, 2, 4};
        assertEquals(19,Lab12Th.houseRobbersBottomUp(arr));
        int[] arr1={2,10,20,30,50,4};
        assertEquals(72,Lab12Th.houseRobbersBottomUp(arr1));
        //asserts that if the length of the array is 0 the method returns 0
        int[] arr2={};
        assertEquals(0,Lab12Th.houseRobbersBottomUp(arr2));
        //asserts that if there are 2 elements in the array the method returns the max of the 2 elements
        int[] arr3={1,2};
        assertEquals(2,Lab12Th.houseRobbersBottomUp(arr3));
    }

    @Test
    void knapSackTopDown() {
        int profit[]={60, 100, 120};
        int weight[]={10, 20, 30};

        int capacity=50;
        int n=profit.length;
        assertEquals(220,Lab12Th.knapSackTopDown(capacity, weight, profit, n));
        //asserts that if the profits array is of length 0, the method returns 0
        int[] profit1={};
        int[] weight1={10, 20, 30};
        n=profit1.length;
        assertEquals(0,Lab12Th.knapSackTopDown(capacity, weight1, profit1, n));
        //asserts that if the capacity array is of length 0, the method returns 0
        assertEquals(0,Lab12Th.knapSackTopDown(0, weight, profit, profit.length));
    }

    @Test
    void knapSackBottomUp() {
        int profit[]={60, 100, 120};
        int weight[]={10, 20, 30};

        int capacity=50;
        int n=profit.length;
        assertEquals(220,Lab12Th.knapSackBottomUp(capacity, weight, profit, n));
        //asserts that if the profits array is of length 0, the method returns 0
        int[] profit1={};
        int[] weight1={10, 20, 30};
        n=profit1.length;
        assertEquals(0,Lab12Th.knapSackBottomUp(capacity, weight1, profit1, n));
        //asserts that if the capacity array is of length 0, the method returns 0
        assertEquals(0,Lab12Th.knapSackBottomUp(0, weight, profit, profit.length));
    }
}