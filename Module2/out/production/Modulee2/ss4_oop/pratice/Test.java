import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vui lòng nhập số lượng số nguyên tố numberInput: ");
        int numberInput = scanner.nextInt();
        int number = 2;
        int count = 0;
        while (count < numberInput) {
            boolean check = true;
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    check = false;
                    break;
                }
            }
            if (check) {
                count++;
                System.out.print(number + " ");
            }
            number++;
        }
    }
}
