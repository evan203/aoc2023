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
    // if top 
    if (i == 0)
    {
      // if top left
      if (j == 0)
        return symbol(i + 1, j, schematic) || symbol(i, j + 1, schematic) || symbol(i + 1, j + 1, schematic);
      // if top right
      if (j == SIZE - 1)
        return symbol(i + 1, j, schematic) || symbol(i, j - 1, schematic) || symbol(i + 1, j - 1, schematic);
      // just top
      return symbol(i + 1, j, schematic) || symbol(i, j - 1, schematic) || symbol(i + 1, j - 1, schematic) || symbol(i + 1, j + 1, schematic) || symbol(i, j + 1, schematic);
    }
    // if bottom
    if (i == SIZE - 1)
    {
      // if bottom left
      if (j == 0)
        return symbol(i - 1, j, schematic) || symbol(i, j + 1, schematic) || symbol(i - 1, j + 1, schematic);
      // if bottom right
      if (j == SIZE - 1)
        return symbol(i - 1, j, schematic) || symbol(i, j - 1, schematic) || symbol(i - 1, j - 1, schematic);
      // just bottom
        return symbol(i - 1, j, schematic) || symbol(i, j - 1, schematic) || symbol(i - 1, j - 1, schematic) || symbol(i, j + 1, schematic) || symbol(i - 1, j + 1, schematic);
    }
    // if left
    if (j == 0)
      return symbol(i - 1, j + 1, schematic) || symbol(i - 1, j, schematic) || symbol(i + 1, j, schematic) || symbol(i, j + 1, schematic) || symbol(i + 1, j + 1, schematic);
    // if right
    if (j == SIZE - 1)
      return symbol(i + 1, j - 1, schematic) || symbol(i + 1, j, schematic) || symbol(i - 1, j, schematic) || symbol(i, j - 1, schematic) || symbol(i - 1, j - 1, schematic);
    // center
    return symbol(i - 1, j + 1, schematic) || symbol(i, j + 1, schematic) || symbol(i + 1, j + 1, schematic) || symbol(i + 1, j - 1, schematic) || symbol(i + 1, j, schematic) || symbol(i - 1, j, schematic) || symbol(i, j - 1, schematic) || symbol(i - 1, j - 1, schematic);
  }
  static int gearRatio(int i, int j, char[][] schematic)
  {
    // if not a symbol
    if (schematic[i][j] == '.')
      return 0;
    // is a symbol:
    // if adjacent to only two part numbers
    int[] dA = digitsAdjacent(i, j, schematic);
    if (dA.length == 2)
      return dA[0] * dA[1];
    
    return 0;
  }
  static int[] digitsAdjacent(int i, int j, char[][] schematic)
  { 
    int iMin = (i == 0) ? i : i - 1;
    int iMax = (i == SIZE - 1) ? i : i + 1;
    int jMin = (j == 0) ? j : j - 1;
    int jMax = (j == SIZE - 1) ? j : j + 1;
    return new int[0];
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
