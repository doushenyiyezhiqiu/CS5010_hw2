public class Recipe {
    private String drinkName;
    private MyOwnLinkedList<String> ingredients;
    private MyOwnLinkedList<Integer> numberOfIngredients;
    private int numberOfUnits;

    public Recipe(String drinkName, MyOwnLinkedList<String> ingredients, MyOwnLinkedList<Integer> numberOfIngredients, int numberOfUnits) {
        this.drinkName = drinkName;
        this.ingredients = ingredients;
        this.numberOfIngredients = numberOfIngredients;
        this.numberOfUnits = numberOfUnits;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public MyOwnLinkedList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(MyOwnLinkedList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public MyOwnLinkedList<Integer> getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfIngredients(MyOwnLinkedList<Integer> numberOfIngredients) {
        this.numberOfIngredients = numberOfIngredients;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    @Override
    public String toString() {
        return "The recipe is for " + drinkName;
    }
}
