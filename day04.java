import java.util.*;
import java.io.*;

class day04 {
  static final int SIZE = 205;
  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("input"));
    ArrayList<HashSet<Integer>> wNums = new ArrayList<HashSet<Integer>>(SIZE);
    int[][] nums = new int[SIZE][];
    // insert input data into 2d array and arraylist of hash maps
    for (int i = 0; in.hasNextLine(); i++)
    {
      String line = in.nextLine();
      String[] split = line.split(" \\| +");
      String[] wNUMS = split[0].split("\\: +")[1].split("\s+");
      String[] NUMS = split[1].split("\s+");
      wNums.add(new HashSet<Integer>(wNUMS.length));
      nums[i] = new int[NUMS.length];
      for (int j = 0; j < wNUMS.length; j++)
          wNums.get(i).add(Integer.parseInt(wNUMS[j]));
      for (int j = 0; j < NUMS.length; j++)
        nums[i][j] = Integer.parseInt(NUMS[j]);
    }
    int totalPoints = 0;
    // count up points for each score card
    int[] copies = new int[SIZE]; // number of copies for each card
    for (int i = 0; i < wNums.size(); i++)
    {
      int matches = 0;
      for (int j = 0; j < nums[i].length; j++)
      {
        if (wNums.get(i).contains(nums[i][j]))
          matches++;
      }
      // you win copies of the scratchcards below the winning card (i) equal to the number of matches
      for (int k = i + 1; k <= (i + matches) && k < SIZE; k++ )
        copies[k] += copies[i] + 1;
      totalPoints += (matches == 0) ? 0 : Math.pow(2, matches - 1);
    }
    System.out.println(totalPoints);
    int totalCards = 0; // Including the original set of scratchcards
    for (int i : copies)
      totalCards += i + 1;
    System.out.println(totalCards);
  }
}
