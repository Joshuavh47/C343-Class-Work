import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lab9 {

    /** RADIX SORT
     *
     * This is NOT a recursive algorithm in the normal sense
     * This is to iterate through the digits of each number
     *
     * This method simply iterates through the number of digits in the max
     * element and calls radixHelper
     *
     * @param arr array to be sorted
     */
    static void radixSort(int[] arr) {
        if(arr.length==0){
            System.out.println("Nothing to sort");
            return;
        }
        int max=max(arr);
        int count=0;
        while(max!=0){
            max/=10;
            count++;
        }
        radixHelper(arr,count);
    }

    /** RADIX HELPER
     *
     * Words do not do it justice. For a visualization see:
     * https://www.cs.usfca.edu/~galles/visualization/RadixSort.html
     *
     * @param arr array to be sorted
     * @param place the placement of the digit you are on
     */
    static void radixHelper(int[] arr, int place) {
        LinkedList<Integer>[] l=new LinkedList[19];
        for(int i=0;i<l.length;i++){
            l[i]=new LinkedList<Integer>();
        }

        for(int i=0;i<place;i++){
            for(int j=0;j<l.length;j++){
                l[j].clear();
            }
            for(int j=0;j<arr.length;j++){

                int temp;
                if(i*10==0){

                    if(arr[j]<0){
                        temp=((arr[j]*-1)%10);
                        temp*=-1;
                    }
                    else {
                        temp = (arr[j] % 10);
                    }
                }
                else{
                    if(arr[j]<0){
                        temp=(arr[j]*-1)/(int)Math.pow(10,i);
                        temp%=10;
                        temp*=-1;
                    }
                    else {
                        temp = arr[j] / (int) Math.pow(10, i);
                        temp %= 10;
                    }
                }


                l[temp+9].add(arr[j]);
            }
            int arrCount=0;
            for(int j=0;j<l.length;j++){
                for(int k=0;k<l[j].size();k++){
                    arr[arrCount]=l[j].get(k);
                    arrCount++;
                }

            }
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    /** BUCKET SORT
     *
     * you are allowed to use Collections.Sort ONLY for sorting the individual buckets
     *
     * bucket sort is a scatter and gather sorting algorithm where you split all the
     * elements into "buckets" that correspond with which range the number lies in.
     *
     * You must first get the maximum and minimum elements and then calculate the ranges
     * the buckets will use.
     *
     * hint: (max - min) / number of buckets
     *
     * You must then disperse the elements into their corresponding buckets
     *
     * Then sort the buckets. (This is where you may use Collections.Sort or copy code
     * from the previous lab idc)
     *
     * Lastly "dump" the buckets back into the arr and you are done!
     *
     * @param arr array to be sorted
     * @param noOfBuckets the number of buckets to be used
     */
    static void bucketSort(int[] arr, int noOfBuckets){
        ArrayList<Integer>[] l=new ArrayList[noOfBuckets];
        for(int i=0;i<l.length;i++){
            l[i]=new ArrayList<>();
        }
        int max=max(arr);
        int min=min(arr);
        int range=(int)((max-min)/(double)noOfBuckets);
        if(((max-min)/(double)noOfBuckets)!=(double)((int)(max-min)/noOfBuckets)){
            range=(int)((double)(max-min)/noOfBuckets)+1;
        }
        else{
            range=(int)((max-min)/noOfBuckets);
        }
        for(int i=0;i<arr.length;i++){
            if((arr[i]-min)/range==noOfBuckets){
                l[(arr[i]-min)/range-1].add(arr[i]);
            }
            else {
                l[(arr[i]-min )/ range].add(arr[i]);
            }
        }
        int count=0;
        for(int i=0;i<l.length;i++){
            Collections.sort(l[i]);
            for(int j=0;j<l[i].size();j++){
                arr[count]=l[i].get(j);
                count++;
            }
        }

        for(int i:arr){
            System.out.println(i);
        }
    }

    /** MAX
     * useful for bucket sort and radix sort
     *
     * iterate through the array and return the largest element
     *
     * @param arr array of integers
     * @return the maximum element of the array
     */
    public static int max(int[] arr) {
        int max=arr[0];
        for(int i:arr){
            if(i>max){
                max=i;
            }
        }
        return max;
    }

    /** MIN
     * useful for bucketsort
     *
     * iterate through the array and return the smallest element
     *
     * @param arr array of integers
     * @return the minimum element of the array
     */
    public static int min(int[] arr) {
        int max=arr[0];
        for(int i:arr){
            if(i<max){
                max=i;
            }
        }
        return max;
    }

}
