/**
 * The class represents the whole beverage factory.
 * "run()" method will run the whole factory and make every operations.
 * "checkIfEnoughBottlesAndIngredients()" method will check if there are enough bottles and ingredients in the factory.
 * "produce()" method will represent the produce process.
 */
public class Factory {
    private InventorySystem inventorySystem;
    private RecipeLibrary recipeLibrary;
    private BottleTrackSystem bottleTrackSystem;

    private boolean quitFlag;

    public Factory() {
        inventorySystem = new InventorySystem();
        recipeLibrary = new RecipeLibrary();
        bottleTrackSystem = new BottleTrackSystem();
        quitFlag = false;
    }

    public void run() {
        View.welcomeMessage();
        while (!quitFlag) {
            String drinkName = View.userEnterDrinkName();
            if (recipeLibrary.contains(drinkName)) {
                boolean res = View.askIfUseExistingRecipe();
                if (res) {
                    int index = recipeLibrary.index(drinkName);
                    Recipe existingRecipe = recipeLibrary.getRecipeCatalog().get(index);
                    if (!checkIfEnoughBottlesAndIngredients(existingRecipe.getNumberOfUnits(), existingRecipe.getIngredients(), existingRecipe.getNumberOfIngredients())) {
                        quitFlag = View.quitMessage();
                        continue;
                    }
                    // after checking everything is OK and then produce
                    produce(existingRecipe);
                } else {
                    int index = recipeLibrary.index(drinkName);
                    MyOwnLinkedList<String> tempComponents = new MyOwnLinkedList<>();
                    MyOwnLinkedList<Integer> tempNumberOfComponents = new MyOwnLinkedList<>();
                    int tempUnits = View.userEnterIngredientsAndUnits(tempComponents, tempNumberOfComponents);
                    if (tempUnits > BottleTrackSystem.getTotalUnits()) {
                        View.showNoEnoughBottles();
                        quitFlag = View.quitMessage();
                        continue;
                    }
                    if (!checkIfEnoughBottlesAndIngredients(tempUnits, tempComponents, tempNumberOfComponents)) {
                        quitFlag = View.quitMessage();
                        continue;
                    }
                    Recipe existingRecipe = recipeLibrary.getRecipeCatalog().get(index);
                    existingRecipe.setIngredients(tempComponents);
                    existingRecipe.setNumberOfIngredients(tempNumberOfComponents);
                    existingRecipe.setNumberOfUnits(tempUnits);
                    produce(existingRecipe);
                }
            } else {
                MyOwnLinkedList<String> tempComponents = new MyOwnLinkedList<>();
                MyOwnLinkedList<Integer> tempNumberOfComponents = new MyOwnLinkedList<>();
                int tempUnits = View.userEnterIngredientsAndUnits(tempComponents, tempNumberOfComponents);;
                if (tempUnits > BottleTrackSystem.getTotalUnits()) {
                    View.showNoEnoughBottles();
                    quitFlag = View.quitMessage();
                    continue;
                }
                if (!checkIfEnoughBottlesAndIngredients(tempUnits, tempComponents, tempNumberOfComponents)) {
                    quitFlag = View.quitMessage();
                    continue;
                }
                Recipe tempRecipe = new Recipe(drinkName, tempComponents, tempNumberOfComponents, tempUnits);
                recipeLibrary.getRecipeCatalog().add(tempRecipe);
                produce(tempRecipe);
            }
            quitFlag = View.quitMessage();
        }

    }

    private void cleanContainer() {
        MyOwnLinkedList<BigBottle> bigBottleList = bottleTrackSystem.getBigBottleList();
        MyOwnLinkedList<SmallBottle> smallBottleList = bottleTrackSystem.getSmallBottleList();

        for (int i = 0; i < bottleTrackSystem.getNumberOfBigBottle(); i++) {
            BigBottle current = bigBottleList.get(i);
            if (current.isDirty()) {
                Beverage temp = current.cleanBottle();
                for (int j = 0; j < BigBottle.getUnits(); j++) {
                    inventorySystem.removeOneBeverage(temp);
                }
            }
        }

        for (int i = 0; i < bottleTrackSystem.getNumberOfSmallBottle(); i++) {
            SmallBottle current = smallBottleList.get(i);
            if (current.isDirty()) {
                Beverage temp = current.cleanBottle();
                for (int j = 0; j < BigBottle.getUnits(); j++) {
                    inventorySystem.removeOneBeverage(temp);
                }
            }
        }

    }

    // please note that do not consider the units beyond the total units factory can use
    private boolean checkIfEnoughBottlesAndIngredients(int amount, MyOwnLinkedList<String> components, MyOwnLinkedList<Integer> numberOfComponents) {
        // check if there is enough bottles in factory. It is not possible that the stored recipe will need more bottles than the total bottles.
        if (!bottleTrackSystem.hasEmptyBottles(amount)) {
            cleanContainer();
        }
        if (!inventorySystem.checkIfEnoughIngredients(components, numberOfComponents)) {
            View.showNoEnoughIngredients();
            return false;
        }
        return true;
    }

    // begin produce the beverage via recipe
    private void produce(Recipe recipe) {
        // fill in bottle
        bottleTrackSystem.fillInBottles(recipe);
        // add beverage into inventory system
        if (!inventorySystem.containsBeverage(recipe.getDrinkName())) {
            inventorySystem.getBeverages().add(new Beverage(recipe.getDrinkName(), recipe));
            inventorySystem.getNumberOfBeverages().add(0);
        }
        int index = inventorySystem.indexOfBeverage(recipe.getDrinkName());
        inventorySystem.getNumberOfBeverages().set(index, inventorySystem.getNumberOfBeverages().get(index) + recipe.getNumberOfUnits());
        // delete the ingredients used
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            String current = recipe.getIngredients().get(i);
            for (int j = 0; j < inventorySystem.getIngredients().size(); j++) {
                String temp = inventorySystem.getIngredients().get(i);
                if (temp.equals(current)) {
                    inventorySystem.getNumberOfIngredients().set(j, inventorySystem.getNumberOfIngredients().get(j) - recipe.getNumberOfIngredients().get(i));
                }
            }
        }
    }

}
