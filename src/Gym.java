import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.*;

public class Gym {

    List<Customers> membersList = new ArrayList<>();

    public boolean loadCustomerList(String s) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(s));
        }
        catch (FileNotFoundException e) {
            showMessageDialog(null, "Filen kunde inte hittas!");
            return false;
        }

        String info1;
        String [] info2;

        while (true) {
            try {
               if ((info1 = in.readLine()) != null) {
                    info2 = info1.split(",");
                    info1 = in.readLine().trim();
                    LocalDate localDate = LocalDate.parse(info1);

                    Customers customers = new Customers(info2[0].trim(), info2[1].trim(), localDate);
                    membersList.add(customers);
               }
                else {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Hoppas, det blev fel");
            }
        }
        return true;
    }

    public Customers getCustomers (String input) {
        Customers customersFound = null;
        for (int i = 0; i < membersList.size() ; i++) {
            if (input.equalsIgnoreCase(membersList.get(i).getName())
                    || input.equalsIgnoreCase(membersList.get(i).getPersonalNumber())) {
                customersFound = membersList.get(i);
                break;
            }
        }
        return customersFound;
    }

    //method that checks if customers membership has expired or not
    public boolean hasMembershipExpired(Customers customers) {
        LocalDate now = LocalDate.now();
        Period period = customers.getDatePayment().until(now);
        return period.getYears() > 0;
    }

    //method that calculates when a customers membership expires
    public Period whenMembershipExpires(Customers customers) {
        LocalDate expiryDate = customers.getDatePayment().plusYears(1);
        return LocalDate.now().until(expiryDate);
    }

    //method that prints out a customers membership expire date
    public void printMemberValidTill(Customers customers) {
        Period period = whenMembershipExpires(customers);
        int monthsTillExpired = period.getMonths();
        int daysTillExpired = period.getDays();
        showMessageDialog(null, String.format("Välkommen %s, ditt medlemsskap går ut om %d månader %d dagar",
                customers.getPersonalNumber(), monthsTillExpired, daysTillExpired));
    }

    //method that tracks how often a particular customer visits the gym
    public void trackFrequency(Customers customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("trackFrequency.txt", true))) {
            DateTimeFormatter visitDateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
            String printToNewFile = String.format("%-20s %-20s Besökte gymmet datum: %-20s",
                    customers.getPersonalNumber(), customers.getName(), LocalDateTime.now().format(visitDateFormat));
            writer.write(printToNewFile);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
