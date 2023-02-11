import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

public class StrikeEdgeZeroGame {

    public static void Part2_A(String input, String output) {

        //Prepares an array of each game, represented as a string of all the values
        String[] games = processInput(input);

        int amountOfGames = games.length;
        int[] results = new int[amountOfGames];

        //Initiates a recursion for each instance of the game
        for (int i = 0; i < amountOfGames; i++) {

            //tempArr now contains all the game values split up, tempArr[0] has the size N of the game
            String[] tempArr = games[i].split(" ");

            //tempArr's important values are pushed into a new array in the correct order
            int[] gameArr = new int[Integer.parseInt(tempArr[0])];
            for (int j = 0; j < gameArr.length; j++)
                gameArr[j] = Integer.parseInt(tempArr[gameArr.length-j]);

            //alreadyVisited is an empty array whose values can change to 1 if an index is visited,
            //if an index is revisited, the 1 can be recognized and we return 0 since we are stuck in a pattern
            int[] alreadyVisited = new int[gameArr.length];

            results[i] = StrikeEdgeZero(gameArr, alreadyVisited, 0);

        }

        writeOut(results, output);
    }

    //Reads the main input file and returns an array of all the games to be played
    public static String[] processInput(String input) {

        try {
            BufferedReader read = new BufferedReader(new FileReader(input));
            String line;

            //reads the first line
            line = read.readLine();

            //this line contains a number corresponding to the amount of games to be run
            int amountOfGames = Integer.parseInt(line);
            String[] arr = new String[amountOfGames];

            //fill an array with the lines
            for (int i = 0; i < amountOfGames; i++)
                arr[i] = read.readLine();

            read.close();
            return arr;
        }
        catch(IOException e){
            System.out.println(e);
        }

        //in case an error occurs and we cannot read in.txt, an empty array is returned
        String[] emptyArr = new String[0];
        return emptyArr;
    }

    //takes an array of integers (results of all the games) and prints them out into a new out.txt file
    public static void writeOut(int[] results, String out) {

        try {
            PrintWriter write = new PrintWriter(out);

            for (int i = 0; i < results.length; i++)
                write.println(results[i]);

            write.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    //checks that a left jump is possible, and that the target index hasn't been visited yet
    public static boolean checkLeft(int current, int leap, int[] alreadyVisited) {

        //if jumping left would return to the starting point or go into negative index
        //if alreadyVisited at i = 1, it means we have already visited this index
        if(!((current-leap) < 1))
            if(alreadyVisited[current-leap] != 1)
                return true;

        return false;
    }

    //checks that a right jump is possible, and that the target index hasn't been visited yet
    public static boolean checkRight(int[] input, int current, int leap, int[] alreadyVisited) {

        //if jumping right would either pass the last index and/or we have already visited
        //an index, we return false
        if(!((current+leap)>input.length-1))
            if(alreadyVisited[current+leap] != 1)
                return true;

        return false;
    }

    //Main mechanism of this whole recursion
    public static int StrikeEdgeZero(int[] input, int[] alreadyVisited, int currentIndex) {

        //if a right jump is possible
        if(checkRight(input, currentIndex, input[currentIndex], alreadyVisited)) {

            //checks if we've reached the winning 0
            if(input[currentIndex+input[currentIndex]] == 0)
                return 1;

            alreadyVisited[currentIndex + input[currentIndex]] = 1;
            return StrikeEdgeZero(input, alreadyVisited, currentIndex + input[currentIndex]);
        }
        //if a left jump is possible
        else if(checkLeft(currentIndex, input[currentIndex], alreadyVisited)) {

            alreadyVisited[currentIndex - input[currentIndex]] = 1;
            return StrikeEdgeZero(input, alreadyVisited, currentIndex - input[currentIndex]);
        }
        return 0;
    }

    public static void main(String[] args) {

        /*
        a)  Time Complexity: O(n^2 + n2^n)
            Space Complexity: O(n)

        b)  This is tail recursion as the check if the game is over (whether or not 0 has been reached) happens before the recursive call
            The recursive call is the last thing that happens after things are checked. Tail recursions are always better since they are terminated in
            function stack over the course of the execution.

        c)  By using an empty array of the same size as the main game array, we can track all the indexes visited during the recursion.
            The array starts filled with 0s and when an index is visited, that 0 is changed to a 1. This works because if we ever revisit an index,
            we can automatically know that this path is unavailable since when that index was visited, a left and right recursion was initiated.
            If we are back at this index, it means that there was no solution found in those recursions, or we are in a loop that can be broken through
            the functionality of this array.
         */
        Part2_A("in.txt", "out.txt");

    }
}