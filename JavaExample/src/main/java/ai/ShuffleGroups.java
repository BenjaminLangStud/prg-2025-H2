package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleGroups {
    public static void main(String[] args) {
        // Create a list to hold the characters A through H
        List<Character> letters = new ArrayList<>();

        for (char c = 'A'; c <= 'H'; c++) {
            letters.add(c);
        }

        // Use the built-in shuffle method
        Collections.shuffle(letters);

        // Display the results
        System.out.println("Shuffled Letters A-H:");
        for (char c : letters) {
            System.out.print(c + " ");
        }
    }
}