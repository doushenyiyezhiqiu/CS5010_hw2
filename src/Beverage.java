/**
 * This class represents beverage.
 */
public class Beverage {
    private String name;
    private Recipe recipe;

    public Beverage() {}

    public Beverage(String name, Recipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}


