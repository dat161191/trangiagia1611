import java.util.Arrays;

public class SortString {
    public static void main(String[] args) {
        String str = "Hello    World";
        char[] chars = str.toCharArray(); // chuyển chuỗi thành mảng ký tự
        System.out.println(chars);

        Arrays.sort(chars); // sắp xếp mảng ký tự theo thứ tự tăng dần

        String sortedStr = new String(chars); // tạo chuỗi mới từ mảng ký tự đã sắp xếp

        System.out.println(sortedStr); // in ra chuỗi đã sắp xếp
    }
}
