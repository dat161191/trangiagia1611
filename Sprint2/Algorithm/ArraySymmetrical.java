import java.util.Arrays;

public class ArraySymmetrical {
    public static void main(String[] args) {
        int[] arr1 = {1, 5, 10, 5, 10, 5, 1};
        int[] arr2 = {2, 3, 4, 5, 6, 4, 3, 2};
        checkSymmetrical(arr1);
        checkSymmetrical(arr2);
    }

    public static void checkSymmetrical(int[] arr) {
        boolean check = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                check = false;
                break;
            }
        }
        if (check) {
            System.out.println("Mảng " + Arrays.toString(arr) + " là mảng đối xứng");
        } else {
            System.out.println("Mảng " + Arrays.toString(arr) + " là  mảng không đối xứng");
        }
    }
}

