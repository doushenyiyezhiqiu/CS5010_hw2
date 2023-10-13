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

    public static int userEnterIngredientsAndUnits(MyOwnLinkedList<String> ingredients, MyOwnLinkedList<Integer> numberOfIngredients, MyOwnLinkedList<String> ingredientsInFactory) {
        Scanner scanner = MyScanner.getScanner();
        for (int i = 0; i < ingredientsInFactory.size(); i++) {
            String ingredient = ingredientsInFactory.get(i);
            System.out.println("Do you need " + ingredient.toLowerCase(Locale.ROOT) + "?(y/n)");
            String result = scanner.nextLine();
            if (result.equals("y")) {
                ingredients.add(ingredient);
                System.out.println("How much " + ingredient.toLowerCase(Locale.ROOT) + " do you need?");
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

    public static boolean userIfCleanContainer(MyOwnLinkedList<BigBottle> bigBottles, MyOwnLinkedList<SmallBottle> smallBottles) {
        int bigs = 0, smalls = 0;
        for (int i = 0; i < bigBottles.size(); i++) {
            BigBottle current = bigBottles.get(i);
            if (!current.isDirty()) {
                bigs++;
            }
        }
        for (int i = 0; i < smallBottles.size(); i++) {
            SmallBottle current = smallBottles.get(i);
            if (!current.isDirty()) {
                smalls++;
            }
        }
        Scanner scanner = MyScanner.getScanner();
        System.out.println("There are still " + bigs + " clean big bottles and " + smalls + " clean small bottles. Do you want to clean containers?(y/n)");
        String res = scanner.nextLine();
        if (res.equals("y")) return true;
        return false;
    }

    public static boolean userIfCreateRecipe() {
        System.out.println("Do you want to create recipe according to above ingredients and produce drink?(y/n)");
        Scanner scanner = MyScanner.getScanner();
        String res = scanner.nextLine();
        if (res.equals("y")) return true;
        return false;
    }

    public static void userAddOtherIngredients(MyOwnLinkedList<String> ingredients, MyOwnLinkedList<Integer> numberOfIngredients, MyOwnLinkedList<String> ingredientsInFactory, MyOwnLinkedList<Integer> numberOfIngredientsInFactory) {
        Scanner scanner = MyScanner.getScanner();
        System.out.println("Do you want to add other ingredients?(y/n)");
        String res = scanner.nextLine();
        if (res.equals("y")) {
            boolean hasOtherIngredients = true;
            while (hasOtherIngredients) {
                System.out.println("What ingredients do you want to add?");
                res = scanner.nextLine();
                ingredients.add(res);
                ingredientsInFactory.add(res);
                numberOfIngredientsInFactory.add(10000);
                System.out.println("How much " + ingredients.get(ingredients.size() - 1) + " do you want to add?");
                int ans = scanner.nextInt();
                numberOfIngredients.add(ans);
                scanner.nextLine();
                System.out.println("Do you want to add other ingredients?(y/n)");
                res = scanner.nextLine();
                if (res.equals('y')) hasOtherIngredients = true;
                else hasOtherIngredients = false;
            }
        }
    }

    public static void showRecipeLibrary(MyOwnLinkedList<Recipe> recipeLibrary) {
        if (recipeLibrary.size() == 0) {
            System.out.println("There are no recipes stored in the recipe library.");
        } else if (recipeLibrary.size() == 1) {
            System.out.println("There is " + recipeLibrary.get(0).getDrinkName() + " recipe in the library.");
        }
        else {
            String ans = "There are";
            for (int i = 0; i < recipeLibrary.size(); i++) {
                ans += " " + recipeLibrary.get(i).getDrinkName() + ',';
            }
            ans = ans.substring(0, ans.length() - 1);
            ans += " recipes stored in the recipe library.";
            System.out.println(ans);
        }
    }
}
