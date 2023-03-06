import java.util.Scanner;

public class ReverseNumberPrinting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vui lòng nhập số: ");
        int number = scanner.nextInt();

        int reverseNumber = 0;

        while (number != 0) {
            int temp = number % 10;
            reverseNumber = reverseNumber * 10 + temp;
            number = number / 10;
        }
        System.out.print("Số ngược lại: " + reverseNumber);
    }
}
