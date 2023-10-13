import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        Scanner tempScanner = MyScanner.getScanner();

        Factory factory = new Factory();
        factory.run();

        MyScanner.closeScanner();

    }
}
