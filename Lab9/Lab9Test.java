import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class Lab9Test {

    @Test
    void bucketSort() {
        int[] arr={-12,-3,-69,5,1,9,4,78,4,8,2,0,123,2354,4567,345,678,123,456,6783,12};

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        //tests for when (max-min)/noOfBuckets divides evenly
        //also tests that bucket sort works for negatives
        Lab9.bucketSort(arr,3);
        assertEquals("-69\n"+"-12\n"+"-3\n"+"0\n"+"1\n"+"2\n"+"4\n"+"4\n"+"5\n"+"8\n"+"9\n"+"12\n"+"78\n"+"123\n"+"123\n"+"345\n"+"456\n"+"678\n"+"2354\n"+"4567\n"+"6783\n",baos.toString());
        System.out.flush();
        baos.reset();
        //tests for when (max-min)/noOfBuckets doesn't divide evenly
        Lab9.bucketSort(arr,6);
        assertEquals("-69\n"+"-12\n"+"-3\n"+"0\n"+"1\n"+"2\n"+"4\n"+"4\n"+"5\n"+"8\n"+"9\n"+"12\n"+"78\n"+"123\n"+"123\n"+"345\n"+"456\n"+"678\n"+"2354\n"+"4567\n"+"6783\n",baos.toString());
        System.out.flush();
        baos.reset();
        System.setOut(old);

    }

    @Test
    void radixSort() {
        //Tests radix sort accuracy for positive and negative numbers
        int[] arr={-12,-3,-69,5,1,9,4,78,4,8,2,0,123,2354,4567,345,678,123,456,6783,12};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        Lab9.radixSort(arr);
        assertEquals("-69\n"+"-12\n"+"-3\n"+"0\n"+"1\n"+"2\n"+"4\n"+"4\n"+"5\n"+"8\n"+"9\n"+"12\n"+"78\n"+"123\n"+"123\n"+"345\n"+"456\n"+"678\n"+"2354\n"+"4567\n"+"6783\n",baos.toString());
        System.out.flush();
        System.setOut(old);
    }

    @Test
    void max() {
        int[] arr={-12,-3,-69,5,1,9,4,78,4,8,2,0,123,2354,4567,345,678,123,456,6783,12};
        assertEquals(6783,Lab9.max(arr));
    }

    @Test
    void min() {
        int[] arr={-12,-3,-69,5,1,9,4,78,4,8,2,0,123,2354,4567,345,678,123,456,6783,12};
        assertEquals(-69,Lab9.min(arr));
    }
}