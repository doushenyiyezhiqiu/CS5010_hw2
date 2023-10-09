import java.util.Scanner;

/**
 *  The class can be used as a global scanner class in the whole factory.
 */
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
