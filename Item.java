/*
 * This class represent an Item that can be sold by Businesses
 * and purchased by People.
 *
 * @author Taylor Hartman
*/

public class Item {
    private final String name; //final so we can't change it!
    private int quantity;
    private int numSold;
    private double price;
    private int attemptsToSell = 0;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /*
     * Increases the attempts to sell an item based on the inputted
     * parameter
     *
     * @param int representing the number of attempts to buy the item
    */
    public void increaseAttemptsBy(int n) {
        attemptsToSell += n;
    }

    /*
     * This method is probably useful for figuring out which item is the
     * best seller... hint... hint...
     *
     * @return the number of times this item was wanted for purchase
    */
    public int numAttempts() {
        return attemptsToSell;
    }

    /*
     * Getter for getting the price of the item.
     * This is the preferred way to retrieve instance data. Note how price
     * is still private!
     *
     * @return the price of the item
    */
    public double getPrice() {
        return price;
    }

    /*
     * Sets the price of the item.
     *
     * @param the new price of the item
    */
    public void setPrice(double price) {
        this.price = price;
    }

    /*
     * Increases the number sold tracker.
     *
     * @param number sold
    */
    public void increaseNumSold(int num) {
        this.numSold += num;
    }

    /*
     * Getter for knowing the quantity of this item left in inventory.
     *
     * @return the quantity left
    */
    public int getQuantity() {
        return quantity;
    }

    /*
     * Sets the quantity of this item left in stock.
     *
     * @param the new quantity left
    */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*
     * Checks equality of this Item and a passed in object
     *
     * @param the object to compare
     * @return whether or not the object param is equal to this Item
    */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Item) { //We have to check if this is actually an item
            if (((Item) o).name.equals(this.name)
                && ((Item) o).price == this.price) {
                return true;
            }
        }
        return false;
    }

    /*
     * @return A string representation of the item
    */
    public String toString() {
        return name;
    }

    /*
     * Ignore this. This is just for checkstyle.
    */
    public int hashCode() {
        return 1;
    }
}