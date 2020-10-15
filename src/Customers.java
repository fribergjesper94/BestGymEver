import java.time.LocalDate;

public class Customers {
    private String name;
    private String personalNumber;
    private LocalDate DatePayment;

    public Customers(String namn, String personNummer, LocalDate localDate) {
        this.name = namn;
        this.personalNumber = personNummer;
        this.DatePayment = localDate;
    }

    public String getName() {
        return name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public LocalDate getDatePayment() {
        return DatePayment;
    }
}
