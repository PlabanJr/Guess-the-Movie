import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class Game
{
        int wrongGuess;
        char[] wrongGuessBuffer;
        int correctGuess;
        char[] correctGuessBuffer;
        String movieName;
        char guess;
        
        public Game(String movieName)
        {
            this.movieName = movieName;
            this.wrongGuess = 0;
            this.correctGuess = 0;
            correctGuessBuffer = new char[this.movieName.length()];
            wrongGuessBuffer = new char[10];
            Arrays.fill(wrongGuessBuffer,' ');
            Arrays.fill(correctGuessBuffer, '_');

            printPart1();
            printPart2();
        }

        public void getGuessLetter()
        {
            System.out.print("Guess a letter: ");
            Scanner input = new Scanner(System.in);
            String temp = input.nextLine();
            if(temp.length() > 1 || (temp.charAt(0) != '\'' && !Character.isLetter(temp.charAt(0)) && !Character.isDigit(temp.charAt(0)) && !Character.isWhitespace(temp.charAt(0))))
            {
                System.out.println("\nInvalid guess! Enter a valid guess!\n");
                getGuessLetter();
            }
            else
            {
                guess = temp.charAt(0);
            }
        }

        public boolean isCorrectGuess()
        {
            int wrongCount = 0;
            for(int idx = 0;idx < movieName.length();idx++)
            {
                if(guess == movieName.charAt(idx))
                {
                    if(correctGuessBuffer[idx] != guess)
                    {
                        correctGuessBuffer[idx] = guess;
                        correctGuess++;
                    }    
                }
                else 
                {
                    int flag = 0;
                    for(int j = 0;j < wrongGuess;j++)
                    {
                        if(guess == wrongGuessBuffer[j])
                        {
                            flag = 1;
                            break;
                        }
                    }
                    if(flagcorrectGuessBuffer == 0)
                        wrongCount++;
                }
            }
 
            if(wrongCount == movieName.length())
                return false;
            else
                return true;
        }

        

        public void printPart2()
        {
            System.out.print("You have guessed (" + wrongGuess + ") wrong letters:");
            for(int i = 0;i < wrongGuess;i++)
            {
                System.out.print(' ');
                System.out.print(wrongGuessBuffer[i]);
            }
            System.out.println();
        }

        public void printPart1()
        {
            System.out.print("\nYou are guessing: ");
            for(int i = 0;i < movieName.length();i++)
            {
                System.out.print(correctGuessBuffer[i]);
            }
            System.out.println();
        }
}