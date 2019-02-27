import java.util.*;
import java.io.*;
public class Maze {
  public static String readFile(String x) throws FileNotFoundException{
    File text = new File(x);
    Scanner Maze = new Scanner(text);
    String output = "";
    while (Maze.hasNextLine()) {
      output += Maze.nextLine() + "\n";
    }
    return output;
  }
  public static char[][] readFileArray(String x) throws FileNotFoundException{
    File text = new File(x);
    Scanner Maze = new Scanner(text);
    int r = 0;
    int c = 0;
    while (Maze.hasNextLine()) {
      r++;
      String line = Maze.nextLine();
      c = line.length();
    }
    char[][] output = new char[r][c];
    Maze = new Scanner(text);
    while (Maze.hasNextLine()) {
      int i = 0;
      for (int idx = 0; idx < Maze.nextLine().length(); idx++) {
        output[i][idx] = Maze.nextLine().charAt(idx);
      }
      i++;
    }
    return output;
  }
  public static void main(String args[]) {
    try {
      System.out.println(readFile("Maze1.txt"));
    }
    catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    }
  }
}
