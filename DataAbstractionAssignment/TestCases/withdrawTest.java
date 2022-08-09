import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class withdrawTest {
    Withdraw test1;
    Withdraw test2;
    Withdraw test3;


    @Before
    public void setup() {
        test1 = new Withdraw(10.0, new Date(122, 04, 20, 6, 30, 54), "Savings");
        test2 = new Withdraw(45.4, new Date(122, 11, 6, 23, 56, 54), "Illegal Tax-evasion Account");
        test3 = new Withdraw(250.0, new Date(), "Deposits");
    }

    @Test
    public void testWithdraw() {
        assertEquals("Withdrawal of: $10.0 Date: Fri May 20 06:30:54 PDT 2022 from account: Savings", test1.toString());
        assertEquals("Withdrawal of: $45.4 Date: Tue Dec 06 23:56:54 PST 2022 from account: Illegal Tax-evasion Account", test2.toString());
        assertEquals("Withdrawal of: $250.0 Date: "+new Date()+" from account: Deposits", test3.toString());
    }
}