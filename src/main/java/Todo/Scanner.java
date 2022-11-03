package Todo;

import java.io.IOException;
import java.math.BigInteger;

public class Scanner {

    public static void main(String[] args) throws IOException {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("Input ist keine Nummer.");
            scan.nextLine();
        }
        BigInteger number = scan.nextBigInteger();
        System.out.println("Dein Wert: " + number);
    }

}
