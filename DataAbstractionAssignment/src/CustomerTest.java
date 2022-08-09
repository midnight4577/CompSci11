import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class CustomerTest {
    Customer test;

    @Before
    public void setup() {
        test = new Customer("jerry", 23, 200.0, 1000.0);
    }

    @Test
    public void testDeposit() {
        assertEquals(212.2, test.deposit(12.2, new Date(122, 4, 20), Customer.CHECKING), 0); //normal
        assertEquals(0.0, test.deposit(-12.2, new Date(122, 4, 20), Customer.CHECKING), 0); //negative val
        assertEquals(1010.0, test.deposit(10.0, new Date(122, 4, 20), Customer.SAVING), 0); //different account
        assertEquals(0.0, test.deposit(10.0, new Date(122, 4, 20), "fesf"), 0); //invalid account
    }

    @Test
    public void testWithdraw(){
        assertEquals(180.0, test.withdraw(20,  new Date(), Customer.CHECKING), 0); //normal
        assertEquals(0.0, test.withdraw(1000,  new Date(), Customer.CHECKING), 0); //past overdraft
        assertEquals(-70.0, test.withdraw(250,  new Date(), Customer.CHECKING), 0); //within overdraft
        assertEquals(0.0, test.withdraw(-1000,  new Date(), Customer.CHECKING), 0); //negative value
        assertEquals(500.0, test.withdraw(500,  new Date(), Customer.SAVING), 0); //diff account
        assertEquals(0.0, test.withdraw(500,  new Date(), "dwadwa"), 0); //invalid account
    }
}