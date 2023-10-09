import java.util.Scanner;

public class MyScanner {
    private static Scanner scanner;

    private MyScanner() {}

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
