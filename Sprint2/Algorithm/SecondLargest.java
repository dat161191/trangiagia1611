import java.util.Arrays;
public class SecondLargest {
    static void print2largest(int arr[], int size) {
        int i;
        if (size < 2) {
            System.out.print("Không tồn tại phần tử lớn thứ  2");
            return;
        }
        Arrays.sort(arr);
        for (i = size - 2; i >= 0; i--) {
            if (arr[i] != arr[size - 1]) {
                System.out.printf("Phần tử lớn thứ 2: " + arr[i]);
                return;
            }
        }
        System.out.print("Không tồn tại phần tử lớn thứ  2");
    }

    public static void main(String[] args) {
        int arr[] = {35, 35, 35, 10, 34, 35};
        int n = arr.length;
        print2largest(arr, n);
    }
}
