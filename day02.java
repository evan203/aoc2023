import java.util.*;
import java.io.*;

class day02 {
  public static Boolean valid(String s, int[] rgb)
  {
    // valid if only 12 red cubes, 13 green cubes, and 14 blue cubes (maximum)
    String[] cubes = s.split(", ");
    Boolean r = true;
    for (String  c : cubes)
    {
      String[] str = c.split(" ");
      int i = Integer.parseInt(str[0]);
      switch (str[1])
      {
        case "red":
          if (i > rgb[0])
            rgb[0] = i;
          if (i > 12)
            r = false;
          break;
        case "green":
          if (i > rgb[1])
            rgb[1] = i;
          if (i > 13)
            r = false;
          break;
        case "blue":
          if (i > rgb[2])
            rgb[2] = i;
          if (i > 14)
            r = false;
          break;
      }
    }
    return r;
  }

  public static void main(String[] args) throws FileNotFoundException
  {
      Scanner scn = new Scanner(new File ("input"));
      int sum = 0; // add up the IDs of the games that would have been possible
      int sum2 = 0; // add the power of the minimum set of cubes
      for (int game = 1; scn.hasNextLine(); game++)
      {
        int[] rgb = new int[3];
        String s = scn.nextLine();
        Scanner scn2 = new Scanner (s);
        scn2.useDelimiter(": ");
        scn2.next(); 
        scn2.useDelimiter("; ");
        String cubes = scn2.next().substring(2);
        Boolean v = valid(cubes, rgb);
        if (v)
        do
        {
          cubes = scn2.next();
          v = valid(cubes, rgb);
        }
        while (v && scn2.hasNext());
        sum += v ? game : 0;
        while (scn2.hasNext())
          valid(scn2.next(), rgb);
        sum2 += rgb[0]*rgb[1]*rgb[2];
      }
      System.out.println(sum);
      System.out.println(sum2);
  }
}
