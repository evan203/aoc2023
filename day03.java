import java.util.*;
import java.io.*;

class day03 {
  final static int SIZE = 140;
  static int numDigits(int i, int j, char[][] schematic)
  {
    // if first c is not a number
    if (!Character.isDigit(schematic[i][j]))
      return 0;
    // first char is digit
    // if it's last char in row or next char is not a number it can only be 1 digit long
    if (j == SIZE-1 || !Character.isDigit(schematic[i][j+1]))
      return 1;
    // first and second chars are digits
    // if second to last or 3rd char is not number it can only be 2 long
    if (j == SIZE-2 || !Character.isDigit(schematic[i][j+2]))
      return 2;
    // third char is digit
    return 3;
  }
  static boolean symbol(int i, int j, char[][] schematic)
  {
    return schematic[i][j] != '.' && !Character.isDigit(schematic[i][j]);
  }
  static boolean adjacentSymbol(int i, int j, char[][] schematic)
  {
    int iMin = (i == 0) ? i : i - 1;
    int iMax = (i == SIZE - 1) ? i : i + 1;
    int jMin = (j == 0) ? j : j - 1;
    int jMax = (j == SIZE - 1) ? j : j + 1;
    Boolean b = false;
    for (int row = iMin; row <= iMax; row++)
      for (int col = jMin; col <= jMax; col++)
        b = b || symbol(row, col, schematic);
    return b;
  }
  static int gearRatio(int i, int j, char[][] schematic)
  {
    // if not a symbol
    if (schematic[i][j] == '.')
      return 0;
    // is a symbol:
    // if adjacent to only two part numbers
    ArrayList<Integer> dA = digitsAdjacent(i, j, schematic);
    if (dA.size() == 2)
      return dA.get(0) * dA.get(1);
    
    return 0;
  }
  static ArrayList<Integer> digitsAdjacent(int i, int j, char[][] schematic)
  { 
    ArrayList<Integer> digits = new ArrayList<>(9);
    int iMin = (i == 0) ? i : i - 1;
    int iMax = (i == SIZE - 1) ? i : i + 1;
    // it's possible that a 3 digit number is adjacent to [i][j] in a diagonal, so the jMin should be j-3 if possible, but if j is 1, for example, it has to be 0.
    // if j is SIZE-1, jMax has to be SIZE-1; if j is SIZE-2, jMax has to be SIZE-1 to keep anything out of bounds, but if difference between j and SIZE-1 or j and 0 is greater than 3 (?) than jMin can be j - 3 and jMax can be j + 3
    int jMin = (j < 3) ? 0 : j - 3;
    int jMax = (j == SIZE - 1) ? j : j + 1;
    for (int row = iMin; row <= iMax; row++)
    {
      for (int col = jMin; col <= jMax;)
      {
        int numDigits = numDigits(row, col, schematic);
        switch (numDigits) {
          case 0:
            col++;
            break;
          case 1:
            // if it's not diagonal (jMin can be 3 left at times in case of 3 digit number) it isn't adjacent so only add if diag
            if (col >= j - 1)
              digits.add(Character.getNumericValue(schematic[row][col]));
            break;
          case 2:
            // same thing: if the digit isn't adjacent it can't be added. col represents the j value of the first digit in a number.
            if (col >= j - 2)
              digits.add(10 * Character.getNumericValue(schematic[row][col]) + Character.getNumericValue(schematic[row][col+1]));
            break;
          case 3:
            digits.add(100 * Character.getNumericValue(schematic[row][col]) + 10 * Character.getNumericValue(schematic[row][col+1]) + Character.getNumericValue(schematic[row][col+2]));
            break;
        }
        col += numDigits;
        if (digits.size() > 2)
          return digits;
      }
    }
    return digits;
  }
  public static void main(String[] args) throws FileNotFoundException
  {
      Scanner scn = new Scanner(new File ("input"));
      char[][] schematic = new char[SIZE][SIZE];
      for (int i =0; scn.hasNext(); i++)
        schematic[i] = scn.nextLine().toCharArray();
      int sum = 0;
      int sum2 = 0;
      // loop through each row
      for (int i = 0; i < SIZE; i++)
      {
        // loop through each "number" 
        for (int j = 0; j < SIZE;)
        {
          int numDigits = numDigits(i, j, schematic);
          switch (numDigits) {
            case 0:
              sum2 += gearRatio(i, j, schematic);
              j++;
              break;
            case 1: 
              sum += (adjacentSymbol(i, j, schematic)) ? Character.getNumericValue(schematic[i][j]) : 0;
              break;
            case 2:
              sum += (adjacentSymbol(i, j, schematic) || adjacentSymbol(i, j + 1, schematic)) ? 10 * Character.getNumericValue(schematic[i][j]) + Character.getNumericValue(schematic[i][j+1]) : 0;
              break;
            case 3:
              sum += (adjacentSymbol(i, j, schematic) || adjacentSymbol(i, j + 1, schematic) || adjacentSymbol(i, j + 2, schematic)) ? 100 * Character.getNumericValue(schematic[i][j]) + 10 * Character.getNumericValue(schematic[i][j+1]) + Character.getNumericValue(schematic[i][j+2]) : 0;
              break;
          }
          j += numDigits;
        }
      }
      System.out.println(sum);
      System.out.println(sum2);
  }
}
