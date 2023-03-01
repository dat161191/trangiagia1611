import java.util.*;

public class RemoveAndCountWordsInString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập chuỗi cần kiểm tra: ");
        String input=scanner.nextLine();
        String[] arr = splitString(input);
        Set<String> set = removeDuplicates(arr);
        System.out.println("Kết quả đã loại bỏ phần tử trùng lặp" + set);
        countAppear(arr);
    }
    private static String[] splitString(String input) {
        String[] arr = input.split(" ");
        return arr;
    }
    private static Set<String> removeDuplicates(String[] arr) {
        Set<String> set = new LinkedHashSet<>(Arrays.asList(arr));
        return set;
    }
    private static void countAppear(String[] arr) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String item : arr) {
            if (!map.containsKey(item)) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        }
        System.out.println("Kết quả đếm giá trị trùng lặp" + map);
    }

}
