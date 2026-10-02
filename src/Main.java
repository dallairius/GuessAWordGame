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
        String hint;
        String yesOrNo = "y";

        int chosenWord;
        int numGuesses;

        int randomLetter;

        boolean matchFound = false;
        boolean powerUpWorked = false;

        byte choice;
        byte powerUp1,powerUp2;


        // Welcome the user to the software and prompt them to pick a category
        do {
            // Resets numGuess in case the user is playing again
            numGuesses = 0;

            System.out.println("Welcome to the word guessing game :)\n" +
                    "Please select a topic \n" +
                    "1. General\n" +
                    "2. Lord of the Rings\n" +
                    "3. Star Trek");

            // Threes different categories to pick from with associated hints for each word
            String[][] general = {{"enigma","mysterious..."}, {"tranquil","very calm"}, {"vanguard","defender"}, {"flummoxed","What ???"}, {"secret","Shhhhhhh"}, {"practice","how do you get better?"}};
            String[][] lotr = {{"frodo","he carries the weight"}, {"samwise","the real hero"}, {"gondor","the country of men"}, {"balrog","it shall not pass"}, {"mithrandir","elf I think"}, {"mithril","cool metal"}};
            String[][] starTrek = {{"janeway","hint1"}, {"chakotay","hint2"}, {"doctor","heals people"}, {"torres","hint3"}, {"paris","capital of the french"}, {"neelix","netflix spelled poorly"}};

            // What category the user picks gets put in the "words" array
            choice = input.nextByte();
            input.nextLine();

            String[][] words = switch (choice) {

                case 1 -> general;
                case 2 -> lotr;
                default -> starTrek;

            };

            // Picks a random word in the list and puts it in an array and saves the hint
            chosenWord = r.nextInt(words.length);

            hint = words[chosenWord][1];
            brokenDownWord = words[chosenWord][0].toCharArray();

            // Create an equivalent array with the word hidden
            hiddenWord = new char[brokenDownWord.length];
            Arrays.fill(hiddenWord, '*');

            //
            // At this point we are ready to actually play the game
            //

            // Reset the power ups for the upcoming game
            powerUp1 = 1;
            powerUp2 = 1;

            // User gets sent here if they have solved the word yet
            do {
                System.out.println(
                        "Please guess a letter in the word or use a power up <" + new String(hiddenWord) + ">" +
                        "\n1. Reveal a random letter X"+powerUp1 +
                        "\n2. Give a hint X"+powerUp2

                );

                // Saves the user input in this variable, it's either a letter or a power up choice.
                guessOrPowerUp = input.nextLine();



                // Reveal power up gets checked and applied here
                if(guessOrPowerUp.equals("1")){
                    // powerUp1 is equal to one when you have a reveal left
                    if(powerUp1 == 1){
                        System.out.println("You revealed a random letter");

                        powerUp1 = 0;
                        // This is used to keep the user in the next loop until the reveal has worked
                        powerUpWorked = false;

                        do {
                            // picks a random letter
                            randomLetter = r.nextInt(hiddenWord.length);

                            // Is the letter already revealed ?
                            if (brokenDownWord[randomLetter] != hiddenWord[randomLetter]) {

                                // Reveal the letter
                                hiddenWord[randomLetter] = brokenDownWord[randomLetter];
                                powerUpWorked = true;
                            }
                        }while(!powerUpWorked);
                    }


                    else {
                        System.out.println("You don't have a reveal anymore");
                    }// end of reveal power up



                // Checks if user used powerUp2 and reveal the hint
                }else if(guessOrPowerUp.equals("2")){
                    if(powerUp2 == 1){
                        System.out.println("Here your hint: "+hint);
                        powerUp2 = 0;
                    }else{
                        System.out.println("You don't have a hint anymore");
                    }
                }else{ //end of hint power up



                    // You get here if the player guessed a letter
                    guess = guessOrPowerUp.trim().toLowerCase().charAt(0);

                    // Resets this variable for later use
                    matchFound = false;

                    // Compare every letter to the users guess
                    for (int i = 0; i < brokenDownWord.length; i++) {

                        // User gets in here if their guess is correct
                        if (brokenDownWord[i] == guess) {
                            hiddenWord[i] = guess;
                            matchFound = true;
                        }
                    }

                    // Increments numGuess if the user is wrong
                    if (!matchFound) numGuesses++;

                    System.out.println(matchFound ? "Nice one !" : "Try again");
                } // end of letter check

                // Checks if the user has solved the word, makes them try again if they haven't
            } while (!Arrays.equals(brokenDownWord, hiddenWord));

            // End of game message
            System.out.println("Good job ! The word was: " + new String(brokenDownWord));
            System.out.println("You missed " + numGuesses + " times");
            System.out.println("You want to play again ? y or n");
            yesOrNo = input.nextLine().toLowerCase();

            // Resets the whole game if the user wants to play again
        }while(yesOrNo.equals("y"));
        input.close();
    }
}