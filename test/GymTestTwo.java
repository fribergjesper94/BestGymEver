import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class GymTestTwo {
    Gym gym1 = new Gym ();
    final String customerslist = "cdds.txt";

    @Test
    void loadCustomerList() {
        boolean res = gym1.loadCustomerList("customers.txt");
        assertTrue(res);
        }

    @Test
    void doesNotLoadCustomerList() {
        boolean res = gym1.loadCustomerList(customerslist);
        assertFalse(res);
    }

    @Test
    void MembershipHasExpired() {
        Customers customers1 = new Customers("Jesper Friberg", "199412171234", LocalDate.of(2014,5,10));
        boolean res1 = gym1.hasMembershipExpired(customers1);
        assertTrue(res1);
    }

    @Test
    void memberShipHasNotExpired() {
        Customers customers2 = new Customers("Anna Hansson", "199712111234", LocalDate.of(2020,5,10));
        boolean res2 = gym1.hasMembershipExpired(customers2);
        assertFalse(res2);
    }


}