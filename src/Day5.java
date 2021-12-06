import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        Day5 program = new Day5();
        program.run();
    }

    public void run() {
        int[][] grid = new int[1000][1000];
        try {
            Scanner scanner = new Scanner(new File("TXT/day5Input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineSplit = line.split(",");
                String[] lineSplit2 = lineSplit[1].split("->");
                int x1 = Integer.parseInt(lineSplit[0].trim());
                int y1 = Integer.parseInt(lineSplit2[0].trim());
                int x2 = Integer.parseInt(lineSplit2[1].trim());
                int y2 = Integer.parseInt(lineSplit[2].trim());
                System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                if (x1 == x2) {
                    if (y1 < y2) {
                        for (int i = y1; i <= y2; i++) {
                            grid[x1][i]++;
                        }
                    }
                    if (y2 < y1) {
                        for (int i = y2; i <= y1; i++) {
                            grid[x1][i]++;
                        }
                    }
                } else if (y1 == y2) {
                    if (x1 < x2) {
                        for (int i = x1; i <= x2; i++) {
                            grid[i][y1]++;
                        }
                    }
                    if (x2 < x1) {
                        for (int i = x2; i <= x1; i++) {
                            grid[i][y1]++;
                        }
                    }
                }
                //part 2 addendum, comment it out for part 1 response
                else {
                    while (x1 != x2 && y1 != y2) {
                        grid[x1][y1]++;
                        if (x1 < x2) {
                            x1++;
                        }
                        if (x1 > x2) {
                            x1--;
                        }
                        if (y1 < y2) {
                            y1++;
                        }
                        if (y1 > y2) {
                            y1--;
                        }
                    }
                    grid[x1][y1]++;
                }
                //end of part 2 code
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        int tally2 = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[i][x] >= 2) {
                    tally2++;
                }
            }
        }
        System.out.println(tally2);
    }
}
