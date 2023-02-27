
import java.util.*;

public class NumberDuplicateByMap {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 4, 4, 5, 99};
        int[] arr2 = {4, 4, 5, 5, 6, 6, 99, 7, 8};
        Map<Integer, String> map = new HashMap<>();
        Set<Integer> result = new HashSet<>();
        for (int num : arr1) {
            if (!map.containsKey(num)) {
                map.put(num, "Đây là số không trùng của mảng 1 được put vào map");
            }
        }
        System.out.println(map);
        for (int num : arr2) {
            if (map.containsKey(num)) {
                result.add(num);
            }
        }
        System.out.println("Result = " + result);
    }
}
