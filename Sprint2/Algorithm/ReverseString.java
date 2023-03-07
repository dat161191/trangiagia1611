import java.util.Scanner;

public class ReverseString {
    public static String reverseString(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
//    public static String reverseString2(String str) {
//        if (str.isEmpty()) {
//            return str;
//        }
//        System.out.println(str.substring(1));
//        System.out.println(str.charAt(0));
//        return reverseString(str.substring(1)) + str.charAt(0);
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập chuỗi cần đảo ngược: ");
        String str = scanner.nextLine();
        System.out.printf(reverseString(str));
//        System.out.println(reverseString2(str));
    }
}
