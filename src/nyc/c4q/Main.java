package nyc.c4q;

import java.util.Scanner;

public class Main{

    private final SecretWord mSecretWord;
    private static Hangman hangman;
    private static SecretWord getSecretWord;

    public Main() {
        mSecretWord = new SecretWord();
    }

    public static void main(String[] args) {

        do {
            playGame();
            hangman.promptPlayer("Do you want to play again, Y/N?");

        } while (checkUserRestart());
    }

    public static String checkUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static boolean checkUserRestart() {
        String input = checkUserInput();

        // if user enters Y or no, print
        if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {

            System.out.println("\nPlay Again!\n");

            return true;
            // game will reset

            // if user enters N, print
        } else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {

            System.out.print("Game Over");

            return false;

            // if user enters an int, string, or char != Y || N, print "Invalid Entry, please enter Y or N."
        } else {

            System.out.print("Invalid Entry. Please enter Y or N.\n");

            checkUserRestart();
        }

        return false;
    }

    public static void playGame() {

        hangman = new Hangman();
        while (hangman.getMisses() < 5) {
            hangman.printCurrentword();
            hangman.promptPlayer("Enter a letter: ");
            hangman.readLetter();
            hangman.checkLetter();

            if (hangman.guessedSuccessfully()) {
                break;
            }
            System.out.println(Drawing.get(hangman.getMisses()));
            System.out.println("Misses -> " + hangman.getMisses());
        }

        if (hangman.guessedSuccessfully()) {
            System.out.println("Success");
        } else {
            System.out.println("The answer was " + hangman.getSecretWord() + "\n");
        }
    }
}