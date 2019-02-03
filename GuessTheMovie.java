import java.io.FileReader;
import java.io.*;
import java.util.*;

class GuessTheMovie
{
    static String movieName;
    public static void main(String[] args)
    {
        movieName = new String();
        welcomeNote();
        movieName = getMovieName();

        if(!movieName.equals("Not Found"))
            playGame();
    }   

    public static void welcomeNote() 
    {
        System.out.println("~~~Java GuessTheMovie~~~\n");
        System.out.println("** RULES:");
        System.out.println("-> Guess one letter at time keeping in mind the length of the movie.");
        System.out.println("-> If a correct letter is guessed, it will appear at it's correct position.");
        System.out.println("-> If a wrong letter is guessed, it will increase your wrong count.");
        System.out.println("-> Once a letter is correctly or wrongly guessed, guessing it again will make no difference.");
        System.out.println("-> For a movie name with multiple names, you are supposed to guess the spaces as well.");
        System.out.println("-> If your wrong count reaches 10, the game is over.\n\n");
    }

    public static String getMovieName() 
    {
        try 
        {
            movieName = getRandomMovie(new File("movieList.txt"));
        } 
        catch (FileNotFoundException fe) 
        {
            System.out.println("File not found"); 
            movieName = "Not Found";
            System.exit(1);
        }

        return movieName;
    }

    public static String getRandomMovie(File file) throws FileNotFoundException
    {
        String randomMovieName = null;
        Random rand = new Random(System.currentTimeMillis());
        int count = 0;

        for(Scanner sc = new Scanner(file); sc.hasNext(); )
        {
            count = rand.nextInt(25) + 1;
            String line = sc.nextLine();
            if(rand.nextInt(count) == 0)
                randomMovieName = line;         
        }
        
        return randomMovieName;      
    }

    public static void printWiningNote()
    {
        System.out.println("\nYou Win!");
        System.out.println("You have guessed '" + movieName + "' correctly.");
    }

    public static void printLosingNote()
    {
        System.out.println("\nYou Lose!");
        System.out.println("You failed to guess '" + movieName + "'.");
    }

    public static void playGame()
    {
        Game guessTheMovie = new Game(movieName);

        while(guessTheMovie.wrongGuess < 10 && guessTheMovie.correctGuess < movieName.length())
        {
            guessTheMovie.getGuessLetter();

            if(!guessTheMovie.isCorrectGuess())
            {
                guessTheMovie.wrongGuessBuffer[guessTheMovie.wrongGuess] = guessTheMovie.guess;
                guessTheMovie.wrongGuess++;
            }

            if(guessTheMovie.correctGuess < movieName.length())
            {
                guessTheMovie.printPart1();
                guessTheMovie.printPart2();
            }
        }
     
        if(guessTheMovie.correctGuess == movieName.length())
            printWiningNote();
        else
            printLosingNote();
    }
}