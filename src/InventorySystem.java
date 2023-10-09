public class InventorySystem {
    private MyOwnLinkedList<String> ingredients;
    private MyOwnLinkedList<Integer> numberOfIngredients;

    private MyOwnLinkedList<Beverage> beverages;
    private MyOwnLinkedList<Integer> numberOfBeverages;

    private final int defaultNumberOfIngredients = 10000;

    public InventorySystem() {
        ingredients = new MyOwnLinkedList<>();
        numberOfIngredients = new MyOwnLinkedList<>();
        beverages = new MyOwnLinkedList<>();
        numberOfBeverages = new MyOwnLinkedList<>();

        for (Ingredients ingredient : Ingredients.values()) {
            ingredients.add(ingredient.name());
            numberOfIngredients.add(defaultNumberOfIngredients);
        }
    }

    public MyOwnLinkedList<String> getIngredients() {
        return ingredients;
    }

    public MyOwnLinkedList<Integer> getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public void setIngredients(MyOwnLinkedList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setNumberOfIngredients(MyOwnLinkedList<Integer> numberOfIngredients) {
        this.numberOfIngredients = numberOfIngredients;
    }

    public MyOwnLinkedList<Beverage> getBeverages() {
        return beverages;
    }

    public MyOwnLinkedList<Integer> getNumberOfBeverages() {
        return numberOfBeverages;
    }

    public void setBeverages(MyOwnLinkedList<Beverage> beverages) {
        this.beverages = beverages;
    }

    public void setNumberOfBeverages(MyOwnLinkedList<Integer> numberOfBeverages) {
        this.numberOfBeverages = numberOfBeverages;
    }

    // remove one unit of beverage in the inventory system
    public void removeOneBeverage(Beverage beverage) {
        for(int i = 0; i < beverages.size(); i++) {
            Beverage current = beverages.get(i);
            if (beverage.getName().equals(current.getName())) {
                int amount = numberOfBeverages.get(i);
                if (amount == 1) {
                    beverages.removeUsingIndex(i);
                    numberOfBeverages.removeUsingIndex(i);
                } else {
                    numberOfBeverages.set(i, amount - 1);
                }
            }
        }
    }

    // check if there are enough ingredients needed as input
    public boolean checkIfEnoughIngredients(MyOwnLinkedList<String> components, MyOwnLinkedList<Integer> numberOfComponents) {
        for (int i = 0; i < components.size(); i++) {
            String inputName = components.get(i);
            boolean flag = false;
            for (int j = 0; j < ingredients.size(); j++) {
                String compareName = ingredients.get(j);
                if (inputName.equals(compareName)) {
                    flag = true;
                    if (numberOfComponents.get(i) > numberOfIngredients.get(j)) {
                        return false;
                    }
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    // check if there exists beverage
    public boolean containsBeverage(String drinkName) {
        for (int i = 0; i< beverages.size(); i++) {
            Beverage current = beverages.get(i);
            if (drinkName.equals(current.getName())) {
                return true;
            }
        }
        return false;
    }

    // get the index of beverage via beverage name
    public int indexOfBeverage(String drinkName) {
        for (int i = 0; i< beverages.size(); i++) {
            Beverage current = beverages.get(i);
            if (drinkName.equals(current.getName())) {
                return i;
            }
        }
        return -1;
    }

}
