package atm;

import org.jetbrains.annotations.NotNull;

public class ATM {
    public static void process(int amount, @NotNull Handler handler) {
        handler.process(amount);
    }
    public static void main(String[] args) {
        Handler handler5 = new Handler5();
        Handler handler20 = new Handler20();
        Handler handler50 = new Handler50();
        handler50.setNext(handler20);
        handler20.setNext(handler5);

        ATM.process(175, handler50);
        System.out.println("\n");
        ATM.process(95, handler50);
        System.out.println("\n");
        ATM.process(10, handler50);
    }
}
