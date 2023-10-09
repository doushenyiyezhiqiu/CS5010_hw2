public interface Bottle {

    void setDirty(boolean dirty);

    boolean isDirty();

    void fillInBeverage(Recipe recipe);

    Beverage cleanBottle();
}
