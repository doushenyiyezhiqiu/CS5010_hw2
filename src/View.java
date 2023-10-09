import java.util.Locale;
import java.util.Scanner;

/**
 * The View class shows all messages we want to be shown to the user.
 */
public class View {

    public static void welcomeMessage() {
        System.out.println("Welcome to the beverage factory management system!");
    }

    public static String userEnterDrinkName() {
        Scanner scanner = MyScanner.getScanner();
        System.out.println("Please enter the name you want to call your drink:");
        String name = scanner.nextLine();
        return name;
    }

    public static boolean quitMessage() {
        Scanner scanner = MyScanner.getScanner();
        System.out.println("Do you want to quit the management system?(y/n)");
        String res = scanner.nextLine();
        if (res.equals("y")) {
            return true;
        }
        return false;
    }

    // ask if the user wants to continue use the existing recipe
    public static boolean askIfUseExistingRecipe() {
        Scanner scanner = MyScanner.getScanner();
        System.out.println("There is existing recipe with the same drink name in the recipe library. Do you want to use it?(y/n)");
        String res = scanner.nextLine();
        if (res.equals("y")) {
            return true;
        }
        return false;
    }

    // show that there are no enough ingredients in inventory system
    public static void showNoEnoughIngredients() {
        System.out.println("There are no enough ingredients in the inventory system.");
    }

    public static int userEnterIngredientsAndUnits(MyOwnLinkedList<String> ingredients, MyOwnLinkedList<Integer> numberOfIngredients) {
        Scanner scanner = MyScanner.getScanner();
        for (Ingredients ingredient : Ingredients.values()) {
            System.out.println("Do you need " + ingredient.name().toLowerCase(Locale.ROOT) + "?(y/n)");
            String result = scanner.nextLine();
            if (result.equals("y")) {
                ingredients.add(ingredient.name());
                System.out.println("How much " + ingredient.name().toLowerCase(Locale.ROOT) + " do you need?");
                int ans = scanner.nextInt();
                scanner.nextLine();
                numberOfIngredients.add(ans);
            }
        }
        System.out.println("Please enter the units of beverage you want to produce:");
        int units = scanner.nextInt();
        scanner.nextLine();
        return units;
    }

    public static void showNoEnoughBottles() {
        System.out.println("The number of units you enter is bigger than the value of total units in inventory system.");
    }
}
