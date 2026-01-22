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
        Stage 3:
            Dismiss stage 2, but give the player another feedback:
            - if a digit is present and on the correct spot, display a star
            - if a digit is present and not on the correct spot, display a circle
            - otherwise dont display anything
            e.g.
             secret number: 1234
             guess:         1572 => *o
             guess:         7243 => *oo
            Warning: As a coding constraints: DO NOT USE STRING OPERATIONS
        Stage 4:
            Let the player choose a difficulty level between 3 and 7, which
            represents the digits of the secret number.
     */

    private static boolean areDoubleDigitsPresent(int number, int totalNumberOfDigits) {
        int numberOfDigitsFound = 0;
        for (int digit = 0; digit <= 9; digit++) {
            if (isDigitInNumber(digit, number)) numberOfDigitsFound++;
        }
        return numberOfDigitsFound != totalNumberOfDigits;
    }
    
    // java coding style: constants (finals) are written all upper case 
    private static final int STANDARD_NUMBER_OF_DIGITS = 4; // INFO, a magic number

    // info: 
    // STANDARD_NUMBER_OF_DIGITS is called Snake case
    // generateSecretNumber is called Camel case
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's play a game!");

        int numberOfAttempts = 0;
        int secretNumber = generateSecretNumber(STANDARD_NUMBER_OF_DIGITS);

        while (true) {
            System.out.print("Please enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == 0) {
                System.out.println("Thank you for playing");
                System.out.println("The secret number was " + secretNumber);
                break;
            }
            else if (guess == -1) {
                System.out.println("WARNING: secret number is " + secretNumber);
                continue;
            }
            else if (guess == -2) {
                System.out.println("Rerolling the secret number");
                // this is WET, it's not DRY
                // do {
                //     secretNumber = (int) (Math.random() * 9000) + 1000;
                // } while ( areDoubleDigitsPresent(secretNumber) );
                secretNumber = generateSecretNumber(STANDARD_NUMBER_OF_DIGITS);
            }

            numberOfAttempts++;
            if (guess == secretNumber) {
                System.out.println("Congratulations! You've guessed the number.");
                break;
            }

            // New feedback mechanism with * and o
            System.out.print("Your guess ");
            int numberOfCorrectDigits = determineCorrectDigits(guess, secretNumber);
            int numberOfPresentDigits = determinePresentDigits(guess, secretNumber) - numberOfCorrectDigits;
            for (int i=0; i<numberOfCorrectDigits; i++) System.out.print("*");
            for (int i=0; i<numberOfPresentDigits; i++) System.out.print("o");
            System.out.println();
        }
        // DRY - Don't repeat yourself, do not WET (Write everything twice)
        System.out.println("You needed " + numberOfAttempts + " number of attempts.");
    }

    private static int generateSecretNumber(int totalNumberOfDigits) {
        int secretNumber;
        do {
            secretNumber = (int) (Math.random() * 9000) + 1000;
        } while ( areDoubleDigitsPresent(secretNumber, totalNumberOfDigits) );
        return secretNumber;
    }

    // Attention: the parameter is called "guess",
    // the method is called with a variable called "guess"
    // there are not the same => CbV (Call by Value)
    // calling arguments are copied into new version for the method parameter
    // CbV is working for all primitive data types
    // primitive data types are: boolean, byte, short, char, int, long, float, double
    private static int determineCorrectDigits(int guess, int secret) {
        int numberOfCorrectDigits = 0;
        while (secret > 0 && guess > 0) {
            if (guess % 10 == secret % 10) numberOfCorrectDigits++;
            guess /= 10;
            secret /= 10;
        }
        return numberOfCorrectDigits;
    }

    private static int determinePresentDigits(int guess, int secret) {
        int numberOfPresentDigits = 0;
        while (guess > 0) {
            int currentDigitOfGuess = guess % 10;
            if ( isDigitInNumber(currentDigitOfGuess, secret) ) numberOfPresentDigits++;
            guess /= 10;
        }
        return numberOfPresentDigits;
    }

    private static boolean isDigitInNumber(int digit, int number) {
        while (number > 0) {
            if (digit == number % 10)
                return true;
            number /= 10;
        }
        return false;
    }
}

/* Binary Search
    1) Use a value in the middle of the search space
    2) check the number, if it is the searched number, quit
    3) if the number is lower than the searched one use the right hand part (higher values)
    4) otherwise use the left hand part (lower values)
    5) goto step 1
 */