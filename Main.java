// Wayne Pinnock, CSCI 212, Spring Semester 2023
import java.io.File;  // to read filenames
import java.io.FileNotFoundException;  // to see where an error occured
import java.util.Scanner;  // to read user input

public class Main {
  public static void main(String[] args) {  // Main Method
    try {  // Try this block of code for any errors
      ///////////////////////////*  Storing Words  *///////////////////////////
      // Create an instance of the file class, create an instance of the scanner class. We'll be storing the words from the words.txt file into a String array called words. Since there are 279496 words in the text file, declare the array with a capacity of 279496.
      File wordsFile = new File("words.txt");
      Scanner fileReader = new Scanner(wordsFile);  
      String[] words = new String[279496];  
      
      // While there is a next line in the text file, store the word into the current element of the words array, continue to do so until the while loop condition returns false.
      while(fileReader.hasNextLine()) {  
        for(int i = 0; i < 279496; i++){
          words[i] = fileReader.nextLine();
        }  // Words are in Array
      }
      //////////*  How many players, Current Player, Current Letter *//////////
      // Print to the screen for the user to enter the number of players, then store user's integer input into the int variable numOfPlayers.
      Scanner input = new Scanner(System.in);
      System.out.print("Enter the number of players: ");
      int numOfPlayers = input.nextInt();

      // Create an integer array currentPlayer to keep track of the current player with its capacity being the number of players, then label each current player 1, 2, 3, ... if numOfPlayers was 3, then currentPlayer[3] = {1, 2, 3};
      int[] currentPlayer = new int[numOfPlayers];
      for(int i = 0; i < numOfPlayers; i++) {
        currentPlayer[i] = i + 1;
      }

      // Create a String array currentLetters to tell what the current letter is, and initialize it with nothing surrounded in double quotes.
      String currentLetters = "";
      /////////////////////*  The Game and its Conditions */////////////////////
      // Before the game begins, outside of the game's do while loop, first declare a variable that will represent the index position of the current player in the elements of the array currentPlayer, and initialize it with the value of 0 for the game to begin asking for player 1's input first. Then create a String variable to store the Player's input, this will work for any of the player's input. Declare two boolean variables, one for if the current letters match, and another for if a challenge has been made, and declare both of these to be false at the start.
      int cP = 0;
      String playerInput;
      boolean lettersMatch = false;
      boolean challengeLost = false;
      // Start the game with a do while loop where it will first run the code block, and then check its condition to see if it should run it again. First check if an asterisk is entered, or a letter is entered. If an asterisk is entered, check to see if the substring of the current letters matches that of the substring of an actual word, if it does, then the player who challenged loses, if it doesn't for any of the words, the last player to have entered a letter loses, the game is over. If the user does not enter an asterisk, however, Foolproof system ensures whatever the user enters is a character by using the .next method to input the user's next word into the playerInput variable, if it is not the same length as the length of a character then shrink it down to its first letter, if the letter is not uppercase then capitalize the letter, if the letter is a number than convert it to a an upper-case letter, then add it to currentLetters string, else if it is a special character which is not a letter or number then turn it into the character A, then add it to the currentLetters string. If a letter is entered, check to see if the current letters form a word, if they do then the player who entered the letter loses.
      do {
        System.out.println("Player " + currentPlayer[cP] + ", it's your turn. The letters are " + currentLetters + ". Enter a letter or enter * to challenge.");

        playerInput = input.next();
       
        // Asterisk entered
        if(playerInput.equals("*")) {
          for(int i = 0; i < 279496; i++) {
            if(words[i].length() < currentLetters.length()) {
              continue;
            }
            String wordsSubstring = words[i].substring(0, currentLetters.length());
            if(currentLetters.equals(wordsSubstring)) {
              System.out.print(words[i] + " begins with those letters. Player " + currentPlayer[cP] + " loses!"); challengeLost = true; break;
            }
          }
          if (cP > 0 && challengeLost == false) System.out.print("No word begins with those letters. Player " + currentPlayer[cP - 1] + " loses!"); else if (cP == 0 && challengeLost == false) System.out.print("No word begins with those letters. Player " + numOfPlayers + " loses!"); break;
        }

        // Letter Entered
        // Foolproof system
        if (playerInput.length() > 1) playerInput = playerInput.substring(0, 1);
        if (!playerInput.equals(playerInput.toUpperCase())) playerInput = playerInput.toUpperCase();
        switch(playerInput) {  // In the case playerInput is "0" to "9", set it to its respective value
          case "0":
            playerInput = "A";
            break;
          case "1":
            playerInput = "B";
            break;
          case "2":
            playerInput = "C"; 
            break;
          case "3":
            playerInput = "D";
            break;
          case "4":
            playerInput = "E";
            break;
          case "5":
            playerInput = "F";
            break;
          case "6":
            playerInput = "G";
            break;
          case "7":
            playerInput = "H";
            break;
          case "8":
            playerInput = "I";
            break;
          case "9":
            playerInput = "J"; 
            break;
        }
        if ((!playerInput.equals("A") && !playerInput.equals("B") &&   !playerInput.equals("C") && !playerInput.equals("D") && !playerInput.equals("E") && !playerInput.equals("F") && !playerInput.equals("G") && !playerInput.equals("H") && !playerInput.equals("I") && !playerInput.equals("J") && !playerInput.equals("K") && !playerInput.equals("L") && !playerInput.equals("M") && !playerInput.equals("N") && !playerInput.equals("O") && !playerInput.equals("P") && !playerInput.equals("Q") && !playerInput.equals("R") && !playerInput.equals("S") && !playerInput.equals("T") && !playerInput.equals("U") && !playerInput.equals("V") && !playerInput.equals("W") && !playerInput.equals("X") && !playerInput.equals("Y") && !playerInput.equals("Z")) && !playerInput.equals("0") && !playerInput.equals("1") && !playerInput.equals("2") && !playerInput.equals("3") && !playerInput.equals("4") && !playerInput.equals("5") && !playerInput.equals("6") && !playerInput.equals("7") && !playerInput.equals("8") && !playerInput.equals("9")) playerInput = "A";
        
        currentLetters += playerInput;  // Add player's letter to currentLetters

        for(int i = 0; i < 279496; i++) {  // Loop through each word in the words array to see if it is the same as the current letters
          if(currentLetters.equalsIgnoreCase(words[i])) {
            System.out.print(words[i] + " is a word. Player " + currentPlayer[cP] + " Loses!");
            lettersMatch = true; break;
          }
        }

        if(cP == (numOfPlayers - 1)) {  // Cycle back to first player
          cP = 0;
        } else cP++;
      } while(lettersMatch == false && challengeLost == false);
      
      fileReader.close();  // close Scanner instances to prevent resource leaks
      input.close();
      
    } catch (FileNotFoundException e) {
      System.out.println("An error occured.");
      e.printStackTrace();
    }
  }
}