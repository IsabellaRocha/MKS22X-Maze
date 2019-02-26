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
  public static void main(String args[]) {
    try {
      System.out.println(readFile("Maze1.txt"));
    }
    catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    }
  }
}
