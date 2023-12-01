import java.util.*;
import java.io.*;

class Main {
  private static void checkLine(int start, String line, String num, int n, HashMap<Integer, Integer> hm)
  {
    int index = line.indexOf(num, start);
    if (index != -1)
    {
      hm.put(index, n);
      checkLine(index+1 , line, num, n, hm);
    }
  }
  public static void main(String[] args) throws FileNotFoundException
  {
      String[] nums = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
      Scanner scn = new Scanner(new File ("input"));
      int sum = 0;
      int sum2 = 0;
      while(scn.hasNext())
      {
        int i = 0; // part 1, tens place
        int j = 0; // part 1, ones place
        int k = 0;
        int l = 0;
        HashMap<Integer, Integer> hm = new HashMap<>(10);
        String line = scn.next();
        for (int b = 0; b < nums.length; b++)
          checkLine(0, line, nums[b], b, hm);
        for (int b = 0; b < line.length(); b++)
        {
          Character c = line.charAt(b);
          if (c < 58 && c > 47 ) 
          {
            if (i == 0)
              i = 10 * (c - 48);
            if (k == 0)
              k = 10 * (c - 48);
            
            j = c - 48;
            l = c - 48;
          }
          if (hm.containsKey(b))
          {
            if (k == 0)
              k = 10 * hm.get(b);
            l = hm.get(b);
          }  
        }
        sum += i + j;
        sum2 += k+l;
      }
    System.out.println(sum);
    System.out.println(sum2);

  }
}
