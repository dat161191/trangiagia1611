import java.sql.Array;
import java.util.*;

public class RemoveAndCountWordsInArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng phần tử trong mảng: ");
        int quanlity = Integer.parseInt(scanner.nextLine());
        String[] input = new String[quanlity];
        for (int i = 0; i < input.length; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + " ");
            String ele = scanner.nextLine();
            input[i] = ele;
        }
        System.out.println(Arrays.toString(input));
        Set<String> set = new LinkedHashSet<>(Arrays.asList(input));
        System.out.println("Kết quả đã loại bỏ phần tử trùng lặp" + set);
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String item : input) {
            if (!map.containsKey(item)) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        }
        System.out.println("Kết quả đếm giá trị trùng lặp" + map);
    }
}
