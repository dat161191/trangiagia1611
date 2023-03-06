public class SecondLargest {
    public static int findSecondLargest(Integer[] arr) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        System.out.println(max);
        for (int j : arr) {
            if (j > max) {
                secondMax = max;
                max = j;
            } else if (j > secondMax && j != max) {
                secondMax = j;
            }
        }
        return secondMax;
    }

    public static void main(String[] args) {
        Integer[] array = {6, 6, 5,4};
        System.out.println(SecondLargest.findSecondLargest(array));
    }
}
