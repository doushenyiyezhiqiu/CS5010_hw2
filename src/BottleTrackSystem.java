/**
 * The class represents bottle track system in the factory. It will track each bottle.
 * "hasEmptyBottles()" method will check if there are enough bottles for filling in.
 * "fillInBottles()" method will fill in beverage as units provided.
 */
public class BottleTrackSystem {
    private MyOwnLinkedList<BigBottle> bigBottleList;
    private MyOwnLinkedList<SmallBottle> smallBottleList;

    private final int numberOfBigBottle = 50000;
    private final int numberOfSmallBottle = 100000;

    private static int totalUnits;

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    public static int getTotalUnits() {
        return totalUnits;
    }

    public BottleTrackSystem() {
        bigBottleList = new MyOwnLinkedList<>();
        smallBottleList = new MyOwnLinkedList<>();

        for (int i = 0; i < numberOfBigBottle; i++) {
            bigBottleList.add(new BigBottle());
        }

        for (int i = 0; i < numberOfSmallBottle; i++) {
            smallBottleList.add(new SmallBottle());
        }

        totalUnits = numberOfBigBottle * BigBottle.getUnits() + numberOfSmallBottle * SmallBottle.getUnits();

    }

    public MyOwnLinkedList<BigBottle> getBigBottleList() {
        return bigBottleList;
    }

    public MyOwnLinkedList<SmallBottle> getSmallBottleList() {
        return smallBottleList;
    }

    public int getNumberOfBigBottle() {
        return numberOfBigBottle;
    }

    public int getNumberOfSmallBottle() {
        return numberOfSmallBottle;
    }

    public void setBigBottleList(MyOwnLinkedList<BigBottle> bigBottleList) {
        this.bigBottleList = bigBottleList;
    }

    public void setSmallBottleList(MyOwnLinkedList<SmallBottle> smallBottleList) {
        this.smallBottleList = smallBottleList;
    }

    public boolean hasEmptyBottles(int units) {
        for (int i = 0; i < numberOfBigBottle; i++) {
            if (units <= 0) {
                break;
            }
            BigBottle current = bigBottleList.get(i);
            if (!current.isDirty()) {
                units -= current.getUnits();
            }
        }
        if (units > 0) {
            for (int i = 0; i < numberOfSmallBottle; i++) {
                if (units <= 0) {
                    break;
                }
                SmallBottle current = smallBottleList.get(i);
                if (!current.isDirty()) {
                    units -= current.getUnits();
                }
            }
        }
        return units <= 0;
    }

    // only make the drink in the recipe filled into the small bottles
    private void fillInSmallBottles(Recipe recipe, int needUnits) {
        for (int i = 0; i < numberOfSmallBottle; i++) {
            if (needUnits <= 0) {
                break;
            }
            SmallBottle current = smallBottleList.get(i);
            if (!current.isDirty()) {
                needUnits -= current.getUnits();
                current.fillInBeverage(recipe);
            }
        }
    }

    // make the drink in the recipe filled in the bottles (first big bottles and then small bottles)
    public void fillInBottles(Recipe recipe) {
        int curUnits = recipe.getNumberOfUnits();
        for (int i = 0; i < numberOfBigBottle; i++) {
            BigBottle current = bigBottleList.get(i);
            if (curUnits < current.getUnits()) {
                fillInSmallBottles(recipe, curUnits);
                if (curUnits > 0) {
                    if (current.isDirty()) {
                        continue;
                    } else {
                        curUnits = 0;
                        current.fillInBeverage(recipe);
                    }
                }
                break;
            }
            if (!current.isDirty()) {
                curUnits -= current.getUnits();
                current.fillInBeverage(recipe);
            }
        }
        if (curUnits > 0) {
            fillInSmallBottles(recipe, curUnits);
        }
    }

}
