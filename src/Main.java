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
        String guessOrPowerUp;
        int numGuesses = 0;
        int randomLetter;
        boolean matchFound = false;
        String yesOrNo = "y";
        boolean powerUpWorked = false;

        // Welcome the user to the software and prompt them to pick a category
        do {

            numGuesses = 0;
            System.out.println("Welcome to the word guessing game :)\n" +
                    "Please select a topic \n" +
                    "1. General\n" +
                    "2. Lord of the Rings\n" +
                    "3. Star Trek");

            String[][] general = {{"enigma","mysterious..."}, {"tranquil","very calm"}, {"vanguard","defender"}, {"flummoxed","What ???"}, {"secret","Shhhhhhh"}, {"practice","how do you get better?"}};
            String[][] lotr = {{"frodo","he carries the weight"}, {"samwise","the real hero"}, {"gondor","the country of men"}, {"balrog","it shall not pass"}, {"mithrandir","elf I think"}, {"mithril","cool metal"}};
            String[][] starTrek = {{"janeway","hint1"}, {"chakotay","hint2"}, {"doctor","heals people"}, {"torres","hint3"}, {"paris","capital of the french"}, {"neelix","netflix spelled poorly"}};

            byte choice = input.nextByte();
            input.nextLine();

            String[][] words = switch (choice) {

                case 1 -> general;
                case 2 -> lotr;
                default -> starTrek;

            };

            // Pick a word from the chosen list and puts it in a char array

            brokenDownWord = words[r.nextInt(words.length)][0].toCharArray();
            hiddenWord = new char[brokenDownWord.length];
            Arrays.fill(hiddenWord, '*');

            // Play the game
            byte powerUp1 = 1;
            byte powerUp2 = 1;
            do {
                System.out.println(
                        "Please guess a letter in the word or use a power up <" + new String(hiddenWord) + ">" +
                        "\n1. Reveal a random letter X"+powerUp1 +
                        "\n2. Give a hint X"+powerUp2

                );
                // Checks if the player guessed a letter or a powerUp
                guessOrPowerUp = input.nextLine();
                if(guessOrPowerUp.equals("1")){
                    if(powerUp1 == 1){
                        System.out.println("You revealed a random letter");

                        powerUp1 = 0;
                        powerUpWorked = false;

                        do {
                            randomLetter = r.nextInt(hiddenWord.length);
                            if (brokenDownWord[randomLetter] != hiddenWord[randomLetter]) {
                                hiddenWord[randomLetter] = brokenDownWord[randomLetter];
                                powerUpWorked = true;
                            }
                        }while(!powerUpWorked);
                    }


                    else {
                        System.out.println("You don't have a reveal anymore");
                    }
                // Checks if user used powerUp2
                }else if(guessOrPowerUp.equals("2")){
                    if(powerUp2 == 1){
                        System.out.println("you used a hint");
                        powerUp2 = 0;
                    }else{
                        System.out.println("You don't have a hint anymore");
                    }
                }else{

                    // You get here if the player guesses a letter
                    guess = guessOrPowerUp.trim().toLowerCase().charAt(0);

                    matchFound = false;
                    for (int i = 0; i < brokenDownWord.length; i++) {

                        if (brokenDownWord[i] == guess) {
                            hiddenWord[i] = guess;
                            matchFound = true;
                        }
                    }
                    if (!matchFound) numGuesses++;
                    System.out.println(matchFound ? "Nice one !" : "Try again");
                }
            } while (!Arrays.equals(brokenDownWord, hiddenWord));

            System.out.println("Good job ! The word was: " + new String(brokenDownWord));
            System.out.println("You missed " + numGuesses + " times");
            System.out.println("You want to play again ? y or n");
            yesOrNo = input.nextLine().toLowerCase();
        }while(yesOrNo.equals("y"));
        input.close();
    }
}