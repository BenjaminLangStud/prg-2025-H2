package extra;

import java.util.Scanner;

public class Collatz {
    /*
        Q1 : Will this program terminate for every input of n ?
        Q2:  Determine the loop count for some input of n
    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter n: ");
        int n = scanner.nextInt();

        while (n != 1) {
            if (n % 2 == 0) {
                n = n/2;
            }
            else {
                n = 3*n + 1;
            }
        }

        System.out.println("Terminated");
    }
}
