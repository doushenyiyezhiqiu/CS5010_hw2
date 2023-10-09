public class BigBottle implements Bottle{
    private boolean dirty;
    private static final int units = 2;
    private Beverage beverage;

    public BigBottle() {
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
            return "a dirty big bottle";
        }
        return "a clean big bottle;";
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public void fillInBeverage(Recipe recipe) {
        beverage = new Beverage(recipe.getDrinkName(), recipe);
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
