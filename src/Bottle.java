/**
 * The interface define some basic method all "Bottle" classes must have.
 * "setDirty()" and "isDirty" are setter and getter methods for the boolean dirty.
 * "fillInBeverage()" can fill in beverage into one full bottle.
 * "cleanBottle()" can fill out beverage and clean the bottle which can be reused.
 */
public interface Bottle {

    void setDirty(boolean dirty);

    boolean isDirty();

    void fillInBeverage(Recipe recipe);

    Beverage cleanBottle();
}
