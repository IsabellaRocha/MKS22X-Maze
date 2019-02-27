import java.util.*;
import java.io.*;
public class Maze {
  private char[][] maze;
  private boolean animate;
  private int[][] poss;
  private int endR;
  private int endC;

  public Maze(String fileName) throws FileNotFoundException{
    animate = false;
    makeArray("Maze1.txt");
    int[][] poss2 = {{0, 1},
                    {1, 0},
                    {0, -1},
                    {-1, 0}};
    poss = poss2;
    boolean checkS = false;
    boolean checkE = false;
    for (int idx = 0; idx < maze.length; idx++) {
      for (int x = 0; x < maze[idx].length; x++) {
        if (maze[idx][x] == 'S') checkS = true;
        if (maze[idx][x] == 'E') {
          checkE = true;
          endR = idx;
          endC = x;
        }
      }
    }
    if (!checkS || !checkE) {
      throw new IllegalStateException();
    }
  }
  private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
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
  public int solve() {
    int r = 0; int c = 0;
    for (int idx = 0; idx < maze.length; idx++) {
      for (int x = 0; x < maze[idx].length; x++) {
        if (maze[idx][x] == 'S') {
          r = idx;
          c = x;
          maze[idx][x] = '@';
          break;
        }
      }
    }
    return solve(r, c);
  }
  private int solve(int row, int col) {
    if (animate) {
      clearTerminal();
      System.out.println(this);
      wait(20);
    }
    maze[row][col] = '@';
    int ans;
    for (int idx = 0; idx < 4; idx++) {
      if (maze[row + poss[idx][0]][col + poss[idx][1]] == ' ') {
        ans = solve(row + poss[idx][0], col + poss[idx][1]);
        if (ans != -1) {
          return ans + 1;
        }
      } else if (row + poss[idx][0] == endR && col + poss[idx][1] == endC) {
        return 1;
      }
    }
    maze[row][col] = '.';
    return -1;
  }
  public static void main(String args[]) {
    try {
      Maze test = new Maze("Maze1.txt");
      test.solve();
      System.out.println(test);
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }
}
