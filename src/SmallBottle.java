/**
 * The SmallBottle class represents one bottle class which can contain less beverage.
 */
public class SmallBottle implements Bottle {
    private boolean dirty;
    private static final int units = 1;
    private Beverage beverage;

    public SmallBottle() {
        dirty = false;
        beverage = new Beverage();
    }

    public static int getUnits() {
        return units;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public String toString() {
        if (dirty) {
            return "a dirty small bottle";
        }
        return "a clean small bottle;";
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public void fillInBeverage(Recipe recipe) {
        beverage.setName(recipe.getDrinkName());
        beverage.setRecipe(recipe);
        dirty = true;
    }

    @Override
    public Beverage cleanBottle() {
        dirty = false;
        Beverage temp = beverage;
        beverage = new Beverage();
        return temp;
    }
}
