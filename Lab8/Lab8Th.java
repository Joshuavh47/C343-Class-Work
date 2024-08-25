public class Lab8Th {

    /** This method simply swaps 2 elements in an array.
     *  This is used for insertion sort
     *
     * @param arr array that contains the elements that need swapped
     * @param i index of the first element
     * @param j index of the second element
     */
    void swap(int[] arr, int i, int j)
    {
        int first=arr[i];
        int second=arr[j];
        arr[i]=second;
        arr[j]=first;
    }

    /** INSERTION SORT
     *
     *  do NOT use Collections.sort that will result in a 0 for this method
     *
     *  This is an insertion sort where you must sort the elements in the array
     *  For an in depth explanation of insertion sort see:
     *  https://www.geeksforgeeks.org/insertion-sort/
     *
     * @param arr array to be sorted
     * @return the sorted array
     */
    public int[] insertionSort(int[] arr) {
        for(int i=0;i<arr.length;i++){
            int j=i-1;
            while (j>=0&&arr[j+1]<arr[j]){
                swap(arr,j,j+1);
                j--;
            }
        }
        return arr;
    }

    /** MERGE
     *
     * This merges 2 subarrays of an array arr.
     * These sub arrays are organized:
     *  leftArr = [left...middle]
     *  rightArr = [middle + 1...right]
     *
     * Make 2 arrays that contain the elements of the subarrays
     * and then insert them into arr in a sorted order such that merging:
     * leftArr = [1, 2, 5]
     * rightArr = [3, 4, 6, 7]
     *
     * into arr becomes:
     * arr = [1, 2, 3, 4, 5, 6, 7]
     *
     * @param arr array that contains both subarrays
     * @param left the index of the left
     * @param middle the index of the middle
     * @param right the index of the right
     */
    void merge(int arr[], int left, int middle, int right)
    {
        int size1=middle-left+1;
        int size2=right-middle;
        //System.out.println(size1+" "+size2);
        int[] leftArr=new int[size1];
        int[] rightArr=new int[size2];
        for(int i=0;i<size1;i++){
            leftArr[i]=arr[left+i];
        }
        for(int i=0;i<size2;i++){
            rightArr[i]=arr[middle+i+1];
        }
        int l=0;
        int r=0;
        int p=left;
        while(l<size1&&r<size2){
            if(leftArr[l]<=rightArr[r]){
                arr[p]=leftArr[l];
                l++;
            }
            else{
                arr[p]=rightArr[r];
                r++;
            }
            p++;
        }
        while(l<size1){
            arr[p]=leftArr[l];
            l++;
            p++;
        }
        while(r<size2){
            arr[p]=rightArr[r];
            r++;
            p++;
        }
    }

    /** MERGESORT
     *
     * do NOT use Collections.sort that will result in a 0 for this method
     *
     * A recursive implementation of mergeSort where in every recursive call,
     * you check if the left index is greater thatn the right index.
     * if so, you must find the index directly in the middle between those 2
     * and then recursively call mergeSort on the 2 sections of the array.
     * After the recusive calls you should merge the 2 subarrays that were
     * just defined by left, right, and the middle indices.
     *
     * @param arr array to be sorted
     * @param left left index
     * @param right right index
     */
    void mergeSort(int arr[], int left, int right){
        if(left<right){
            int middle=left+(right-left)/2;
            mergeSort(arr,left,middle);
            mergeSort(arr,middle+1,right);
            merge(arr,left,middle,right);
        }

    }

}