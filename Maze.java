import java.util.*;
import java.io.*;
public class Maze {
  private char[][] maze;
  private String str;
  public void readFile(String x) throws FileNotFoundException{
    File text = new File(x);
    Scanner in = new Scanner(text);
    str = "";
    while (in.hasNextLine()) {
      str += in.nextLine() + "\n";
    }
  }
  public void makeArray(String x) throws FileNotFoundException{
    File text = new File(x);
    Scanner in = new Scanner(text);
    int r = 0;
    int c = 0;
    while (in.hasNextLine()) {
      r++;
      String line = in.nextLine();
      c = line.length();
    }
    maze = new char[r][c];
    in = new Scanner(text);
    for (int i = 0; i < r; i++) {
      String line = in.nextLine();
      for (int idx = 0; idx < line.length(); idx++) {
        maze[i][idx] = line.charAt(idx);
      }
    }
  }
  public String toString() {
    String output = "";
    for (int idx = 0; idx < maze.length; idx++) {
      output += "\n";
      for (int x = 0; x < maze[idx].length; x++) {
        output += maze[idx][x];
      }
    }
    return output;
  }
  public static void main(String args[]) {
    try {
      Maze m = new Maze();
      m.readFile("Maze1.txt");
      System.out.println(m.str);
      m.makeArray("Maze1.txt");
      System.out.println(m);
    }
    catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    }
  }
}
