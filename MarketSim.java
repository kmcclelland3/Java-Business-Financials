/**
 * @author: Kristen McClelland
 *     GTID: 902944956
 *
 * @author Thomas Lilly
 * @version 1.0
 */

import java.util.Random;

public class MarketSim {

    /* Please use this array to test your classes.
     * Feel free to add data to test resizing
     */
    private static String[][] businessData = {
        {"Amazon"},
        {"Fitbit ChargeHR", "129.89", "40"},
        {"Amazon Echo", "179.99" , "34"},
        {"Roses", "139.99", "100"},
        {"Lindt Chocolate", "30.40", "57"},
        {"Jaybird X2", "128.50", "20"},
        {"Football", "28.44", "176"},
        {"Shawl", "12.99", "230"},
        {"CLRS", "66.32", "281"},
        {"USB Micro-USB to USB Cable", "4.96", "132"},
        {"Stationery", "9.89", "75"},
        {"Dell Laptop X15", "1200.50", "20"},
        {"NorthFace Ski Pants", "29.50", "45"},
    };

    /** default and required variables */
    private static int people = 5;
    private static int days = 31;
    private static boolean check = true;
    private static Random gen = new Random(4);
    private int numTransactions;
    private double revenue;
    private int itemsSold;
    private static double time1, time2;


    public static void main(String[] args) {
        time1 = System.nanoTime();

        boolean check = true;
        // 3 cases for args: length 0, length even, length odd
        // if length = 0, nothing changes, use defaults
        // if length = even, further checking to determine if correct
        // if length = odd, automatically fails check
        if (args.length != 0 && (args.length % 2) == 1) {
            check = false;
        } else if (args.length != 0 && (args.length % 2) == 0) {
            for (int i = 0; i < args.length - 1; i += 2) {
                if (args[i].equals("-d")) {
                    days = Integer.parseInt(args[i + 1]);
                } else if (args[i].equals("-p")) {
                    people = Integer.parseInt(args[i + 1]);
                } else {
                    check = false;
                }
            }
        }

        if (!check) {
            System.out.println("\nUsage: java MarketSim [-p <numberOfPeople>"
                + "] [-d <numberOfDays>]");
        } else {
            Business mine = new Business(businessData);

            System.out.printf("Running simulation with %d people over "
                + "%d days...\n\n", people, days);

            Person[] shoppers = new Person[people];
            for (int p = 1; p <= people; p++) {
                String name = "Person " + p;
                int wallet = (gen.nextInt(51) + 50) * 1000;
                shoppers[p - 1] = new Person(name, wallet);
                //System.out.println("Shopper " + p + " Created");
            }

            for (int d = 1; d <= days; d++) {
            //System.out.println("Day " + d);
                for (int p = 1; p <= shoppers.length; p++) {
                    double toBuy = gen.nextDouble();
                    if (toBuy < 0.75) {
                        //System.out.println("Shopper " + p + " is Shopping");
                        Item x = mine.itemToBuy();
                        int quantity = gen.nextInt(5) + 1;
                        mine.sell(shoppers[p - 1], x, quantity);
                    }
                }
            }
            // last thing to do
            time2 = (System.nanoTime() - time1) / (Math.pow(10, 6));
            String output = mine.getReport(days, time2);
            System.out.println(output);
        }
    }
}