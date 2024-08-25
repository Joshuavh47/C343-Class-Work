import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Lab8ThTest {
    Lab8Th l=new Lab8Th();

    @Test
    void swap() {
        int[] arr=new int[10];
        for(int i=0;i<arr.length;i++){
            arr[i]=i;
        }
        l.swap(arr,0,1);
        assertEquals(1,arr[0]);
        assertEquals(0,arr[1]);
    }

    @Test
    void insertionSort() {
        int[] arr1=new int[100];
        int[] arr2=new int[100];
        for(int i=0;i<arr1.length;i++){
            arr1[i]=(int)Math.random()*1000;
        }
        for(int i=0;i<arr2.length;i++){
            arr2[i]=arr1[i];
        }
        l.insertionSort(arr1);
        Arrays.sort(arr2);
        assertArrayEquals(arr1,arr2);
    }

    @Test
    void merge() {
        int[] arr={1,2,5,3,4,6,7};
        l.merge(arr,0,2,6);
        int[] ans={1,2,3,4,5,6,7};
        assertArrayEquals(ans,arr);
    }

    @Test
    void mergeSort() {
        int[] arr1=new int[100];
        int[] arr2=new int[100];
        for(int i=0;i<arr1.length;i++){
            arr1[i]=(int)Math.random()*1000;
        }
        for(int i=0;i<arr2.length;i++){
            arr2[i]=arr1[i];
        }
        l.mergeSort(arr1,0,arr1.length-1);
        Arrays.sort(arr2);
        assertArrayEquals(arr1,arr2);
    }

    @Test
    public void sop(){
        int[] test = new int[1000];
        for(int i = 0; i < 1000; i++){
            test[i] = (int) (Math.random() * 10000);
        }
        l.mergeSort(test, 0, test.length-1);
        for(int i = 0; i < 999; i++){
            assertTrue(test[i] <= test[i+1]);
        }
    }
}