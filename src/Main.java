import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random r = new Random();
        char[] brokenDownWord, hiddenWord;
        char guess;
        int numGuesses = 0;
        boolean matchFound = false;
        String yesOrNo = "y";

        // Welcome the user to the software and prompt them to pick a category
        do {
            numGuesses = 0;
            System.out.println("Welcome to the word guessing game :)\n" +
                    "Please select a topic \n" +
                    "1. General\n" +
                    "2. Lord of the Rings\n" +
                    "3. Star Trek");

            String[] general = {"enigma", "tranquil", "vanguard", "flummoxed", "secret", "practice"};
            String[] lotr = {"frodo", "samwise", "gondor", "balrog", "mithrandir", "mithril"};
            String[] starTrek = {"janeway", "chakotay", "doctor", "torres", "paris", "neelix"};

            byte choice = input.nextByte();
            input.nextLine();

            String[] words = switch (choice) {

                case 1 -> general;
                case 2 -> lotr;
                default -> starTrek;

            };

            // Pick a word from the chosen list and puts it in a char array

            brokenDownWord = words[r.nextInt(words.length)].toCharArray();
            hiddenWord = new char[brokenDownWord.length];
            Arrays.fill(hiddenWord, '*');

            // Play the game

            do {
                System.out.println("Please guess a letter in the word " + new String(hiddenWord) + ">");
                guess = input.nextLine().trim().toLowerCase().charAt(0);

                matchFound = false;
                for (int i = 0; i < brokenDownWord.length; i++) {

                    if (brokenDownWord[i] == guess) {
                        hiddenWord[i] = guess;
                        matchFound = true;
                    }
                }
                if (!matchFound) numGuesses++;
                System.out.println(matchFound ? "Nice one !" : "Try again");

            } while (!Arrays.equals(brokenDownWord, hiddenWord));

            System.out.println("Good job ! The word was: " + new String(brokenDownWord));
            System.out.println("You missed " + numGuesses + " times");
            System.out.println("You want to play again ? y or n");
            yesOrNo = input.nextLine().toLowerCase();
        }while(yesOrNo.equals("y"));
        input.close();
    }
}