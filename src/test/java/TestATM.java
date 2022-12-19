import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import atm.*;


public class TestATM {
    private static Handler handler50;

    @BeforeAll
    public static void setUp() {
        Handler handler5 = new Handler5();
        Handler handler20 = new Handler20();
        handler50 = new Handler50();

        handler50.setNext(handler20);
        handler20.setNext(handler5);
    }

    @org.junit.jupiter.api.Test
    public void testProcess() {
        ATM.process(235, handler50);
        System.out.println("\n");
        ATM.process(85, handler50);
        System.out.println("\n");
        ATM.process(5, handler50);
    }

    @org.junit.jupiter.api.Test
    void testUnsolvableProcess() {
        assertThrows(IllegalArgumentException.class, () -> ATM.process(1, handler50));
    }
}