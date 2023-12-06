import java.util.*;
import java.io.*;
import java.math.*;

class day05 {
  static BigInteger seedToLocation(BigInteger i, ArrayList<ArrayList<BigInteger>> maps)
  {
    // iterate through each map in maps and convert from
    // source number to destination number for each 3 bigints in each map
    for (ArrayList<BigInteger> map: maps)
    {
      for (int j = 0; j < map.size(); j += 3)
      {
        // in first sub arraylist get source range start and range
        // if i >= sourceStart && i < sourceStart + range
        // then i += destStart - sourceStart
        // if i isn't mapped to any of the of the ranges, i is unchanged
        // the 3 nums in each line are the destination range start, the source range start, and the range length 
        BigInteger dS = map.get(j);
        BigInteger sS = map.get(j + 1);
        BigInteger r = map.get(j + 2);
        if (i.compareTo(sS) >= 0 && i.compareTo(sS.add(r)) < 0)
        {  
          i = i.add(dS.subtract(sS));
          j = map.size();
        }
      }
    }
    return i;
  }
  public static void main(String[] args) throws FileNotFoundException 
  {
    Scanner s = new Scanner(new File("input"));
    ArrayList<BigInteger> seeds = new ArrayList<>();
    // get seeds from input into arraylist 
    s.next(); // "seeds: "
    while(s.hasNextBigInteger())
      seeds.add(s.nextBigInteger());    
  
    ArrayList<ArrayList<BigInteger>> maps = new ArrayList<ArrayList<BigInteger>>();
    // insert maps into 2d arraylist of big ints
    while(s.hasNextLine())
    {
      s.nextLine(); s.nextLine(); s.nextLine(); // clear up whitespace
      // add to list
      ArrayList<BigInteger> a = new ArrayList<BigInteger>();
      while (s.hasNextBigInteger())
        a.add(s.nextBigInteger());
      maps.add(a);
    }
    // get location number for each seed
    BigInteger min = seeds.get(0);
    for (int I = 0; I < seeds.size(); I++)
    {
      BigInteger i = seedToLocation(seeds.get(I), maps);
      if (min.compareTo(i) > 0)
        min = i;
    }
    System.out.println(min);
    // part 2: What is the lowest location number that corresponds to any of the initial seed numbers?
    BigInteger start, end, J, i;
    
    for (int I = 0; I < seeds.size(); I += 2)
    {
      start = seeds.get(I);
      end = start.add(seeds.get(I+1));
      for (J = start; J.compareTo(end) < 0 ; J.add(BigInteger.ONE))
      {
        i = seedToLocation(J, maps);
        if (min.compareTo(i) > 0)
          min = i;
      }  
    }
    System.out.println(min);   
  }  
}
