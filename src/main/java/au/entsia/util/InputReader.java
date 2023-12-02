package au.entsia.util;

import java.util.Scanner;

public class InputReader {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String getInput() {
        return SCANNER.nextLine().toUpperCase();
    }

    public void close() {
        SCANNER.close();
    }
}
