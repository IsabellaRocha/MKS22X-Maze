import java.util.*;
import java.io.*;
public class Maze {
  private char[][] maze;
  private boolean animate;
  private int[][] poss;
  private int moves;

  public Maze(String fileName) throws FileNotFoundException{
    animate = false;
    moves = 0;
    makeMaze(fileName);
    int[][] poss2 = {{0, 1},
                    {1, 0},
                    {0, -1},
                    {-1, 0}};
    poss = poss2;
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
  private void makeMaze(String x) throws FileNotFoundException{
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
    int checkS = 0;
    int checkE = 0;
    for (int i = 0; i < r; i++) {
      String line = in.nextLine();
      for (int idx = 0; idx < line.length(); idx++) {
        maze[i][idx] = line.charAt(idx);
        if (maze[i][idx] == 'S') checkS++;
        if (maze[i][idx] == 'E') checkE++;
      }
    }
    if (checkS != 1 || checkE != 1) {
      throw new IllegalStateException();
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
          maze[idx][x] = ' ';
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
      wait(50);
    }
    if (maze[row][col]== 'E') {
      return 1;
    }
    if (maze[row][col] == ' ') {
      maze[row][col] = '@';
      moves++;
      for (int idx = 0; idx < 4; idx++) {
        if (solve(row + poss[idx][0], col + poss[idx][1]) != -1) {
          return moves;
        }
      }
      maze[row][col] = '.';
      moves--;
    }
    return -1;
  }
  public static void main(String args[]) {
    try {
      Maze test = new Maze("Maze1.txt");
      Maze test1 = new Maze("data1.dat");
      Maze test2 = new Maze("data2.dat");
      Maze test3 = new Maze("data3.dat");
  //    test2.setAnimate(true);
      System.out.println(test.solve());
      System.out.println(test1.solve());
      System.out.println(test2.solve());
      System.out.println(test3.solve());
      System.out.println(test);
      System.out.println(test1);
      System.out.println(test2);
      System.out.println(test3);
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }
}
