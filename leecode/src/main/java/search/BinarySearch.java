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

    private static int binarySearch(int[] arr, int key) {

        int result = -1;
        int left = 0;
        int right = arr.length - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (arr[mid] == key) {
                result = mid;
                break;
            } else if (arr[mid] > key) right = mid - 1;
            else left = mid + 1;
            mid = left + (right - left) / 2;
        }
        return result;
    }
}
