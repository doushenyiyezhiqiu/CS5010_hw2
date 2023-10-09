/**
 * The class represents recipe library which contains all recipe existed in the factory.
 */
public class RecipeLibrary {
    private MyOwnLinkedList<Recipe> recipeCatalog;

    public RecipeLibrary() {
        recipeCatalog = new MyOwnLinkedList<>();
    }

    public boolean contains(String name) {
        for (int i = 0; i < recipeCatalog.size(); i++) {
            if (name.equals(recipeCatalog.get(i).getDrinkName())) {
                return true;
            }
        }
        return false;
    }

    public int index(String name) {
        for (int i = 0; i < recipeCatalog.size(); i++) {
            if (name.equals(recipeCatalog.get(i).getDrinkName())) {
                return i;
            }
        }
        return -1;
    }

    public MyOwnLinkedList<Recipe> getRecipeCatalog() {
        return recipeCatalog;
    }

    public void setRecipeCatalog(MyOwnLinkedList<Recipe> recipeCatalog) {
        this.recipeCatalog = recipeCatalog;
    }
}
