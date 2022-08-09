import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class depositTest {
    Deposit test1;
    Deposit test2;
    Deposit test3;


    @Before
    public void setup() {
        test1 = new Deposit(10.0, new Date(122, 04, 20, 6, 30, 54), "Savings");
        test2 = new Deposit(45.4, new Date(122, 11, 6, 23, 56, 54), "Illegal Tax-evasion Account");
        test3 = new Deposit(250.0, new Date(), "Deposits");
    }

    @Test
    public void testWithdraw() {
        assertEquals("Deposit of: $10.0 Date: Fri May 20 06:30:54 PDT 2022 into account: Savings", test1.toString());
        assertEquals("Deposit of: $45.4 Date: Tue Dec 06 23:56:54 PST 2022 into account: Illegal Tax-evasion Account", test2.toString());
        assertEquals("Deposit of: $250.0 Date: "+new Date()+" into account: Deposits", test3.toString());
    }
}