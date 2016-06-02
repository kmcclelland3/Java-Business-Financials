import java.util.Random;

/**
 * This class represents a Business which holds
 * an inventory of Items that a Person can purchase.
 *
 * @author Kristen McClelland
 */
public class Business {
    private Item[] inventory;
    private final String name;
    private double totalSales;
    private int transactions;
    private Random gen = new Random(5);

    public Business(String[][] dataArray) {
        this.name = dataArray[0][0];
        int rLength = 10;
        while (dataArray.length - 1 > rLength) {
            rLength *= 2;
        }
        this.inventory = new Item[rLength];
        for (int r = 1; r < dataArray.length; r++) {
            int quantity = Integer.parseInt(dataArray[r][2]);
            double price = Double.parseDouble(dataArray[r][1]);
            Item x = new Item(dataArray[r][0], quantity, price);
            this.inventory[r - 1] = x;
        }
        this.totalSales = 0;
        this.transactions = 0;
    }

    public String getName() {
        return this.name;
    }

    public Item getBestSellingItem() {
        int i = 0;
        int max = 0;
        Item best = null;
        while (i < inventory.length && inventory[i] != null) {
            int attempts = inventory[i].numAttempts();
            if (attempts >= max) {
                max = attempts;
                best = inventory[i];
            }
            i++;
        }
        return best;
    }

    public void sell(Person p, Item i, int quantity) {
        int loc = -1;
        for (int j = 0; j < inventory.length; j++) {
            if (inventory[j] != null && inventory[j].equals(i)) {
                loc = j;
            }
        }
        if (loc >= 0) {
            int inStock = inventory[loc].getQuantity();
            double price = inventory[loc].getPrice();
            if (quantity <= inStock) {
                double cost = quantity * price;
                boolean bought = p.purchase(cost);
                if (bought) {
                //System.out.println("Purchase made");
                    totalSales += cost;
                    transactions += 1;
                    inventory[loc].increaseNumSold(quantity);
                    inventory[loc].setQuantity(inStock - quantity);
                }
            }
            inventory[loc].increaseAttemptsBy(quantity);
        }
    }

    public Item itemToBuy() {
        int index = gen.nextInt(inventory.length);
        return inventory[index];
    }

    public boolean addItem(Item x) {
        int i = 0;
        boolean add = true;
        while (i < inventory.length && inventory[i] != null) {
            if (inventory[i].equals(x)) {
                add = false;
            }
        }
        if (add) {
            if (i < inventory.length && inventory[i] == null) {
                inventory[i] = x;
            } else {
                Item[] temp = new Item[inventory.length * 2];
                int j = 0;
                for (j = 0; j < inventory.length; j++) {
                    temp[j] = inventory[j];
                }
                inventory = temp;
                inventory[j] = x;
            }
        }
        return add;
    }

    public int itemsInStock() {
        int stock = 0;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                stock += inventory[i].getQuantity();
            }
        }
        return stock;
    }

    public String getReport(int days, double execTime) {
        // revenue is correct
        // total of transactions is correct
        // number of items left is correct
        // best selling item is correct
        // execution time is correct
        String report = new String("Simulation Report: " + this.getName() + "\n"
            + "Execution time: " + Math.round(execTime * 100) / 100.0 + " ms\n"
            + "=========================================\n"
            + "Days of simulation:\t\t" + days + "\n"
            + "Total Transactions:\t\t" + this.transactions + "\n"
            + "Total Revenue:\t\t\t$"
            + Math.round(this.totalSales * 1000.0) / 1000.0 + "\n"
            + "Number of Items in stock:\t" + this.itemsInStock() + "\n"
            + "Best selling item:\t\t\"" + this.getBestSellingItem() + "\"\n"
            + "=========================================");
        return report;
    }
}
