package Todo;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Stdlib {


    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("Input ist keine Nummer.");
            scan.nextLine();
        }
        BigInteger number = scan.nextBigInteger();
        System.out.println("Dein Wert: " + number);
    }

}
