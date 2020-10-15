import java.io.*;
import static javax.swing.JOptionPane.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Gym gym1 = new Gym();
        boolean res = gym1.loadCustomerList("customers.txt");
        if (!res) {
            System.exit(0);
        }

        String input;
        Customers customersFound;
        while (true) {
            input = showInputDialog("Ange namn eller personnummer");
            if (input == null || input.equals("")) {
                int choice = showConfirmDialog(null, "Vill du stänga av programmet?", "Fråga", YES_NO_OPTION);
                if (choice == 0) {
                    System.exit(0);
                }
            } else {
                    input = input.replaceAll("\\s+", " ").trim();
                    customersFound = gym1.getCustomers(input);
                    break;
                }
            }
            if (customersFound == null) {
                showMessageDialog(null, "Du saknar ett medlemsskap");
            } else if (gym1.hasMembershipExpired(customersFound)) {
                showMessageDialog(null, "Medlemsskapet har gått ut");
            } else {
                gym1.printMemberValidTill(customersFound);
                gym1.trackFrequency(customersFound);
            }
        }

    }



