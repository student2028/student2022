package search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};

        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 2));
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 4));
        System.out.println(binarySearch(arr, 5));
        System.out.println(binarySearch(arr, 6));
        System.out.println(binarySearch(arr, 7));


    }

    private static int binarySearch(int[] arr, int target) {
        int N = arr.length;
        int left = 0;
        int right = N - 1;
        while( left <= right) {
            int mid = (left + right)/2;
            if(arr[mid] > target) right = mid - 1;
            else if(arr[mid] < target) left = mid + 1;
            else return mid;
        }
        return -1;
    }


}
