package extra;

import java.util.Scanner;

public class Game {
    /* Guessing game
       The computer randomly selects a number
       between 1000 and 9999.
       Stage 0:
           The player has to guess the number
           with no hints provided.
        Stage 1:
            The player can exit with 0 as input,
            and the number of attempts is counted and printed.
        Stage 2:
            The player receives feedback on whether their guess
            is too high or too low.
     */
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's play a game!");

        int secretNumber = (int) (Math.random() * 9000) + 1000;
        int numberOfAttempts = 0;

        while (true) {
            System.out.print("Please enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == 0) {
                System.out.println("Thank you for playing");
                System.out.println("The secret number was " + secretNumber);
                break;
            }

            numberOfAttempts++;
            if (guess == secretNumber) {
                System.out.println("Congratulations! You've guessed the number.");
                break;
            } else {
                System.out.println("Incorrect guess. Try again.");
            }
        }
        // DRY - Don't repeat yourself, do not WET (Write everything twice)
        System.out.println("You needed " + numberOfAttempts + " number of attempts.");
    }
}
