import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        int horizontal = 0;
        int aim = 0;
        int depth = 0;
        ArrayList<String> commands = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("TXT/day2Input.txt"));
            while (scanner.hasNext()) {
                commands.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < commands.size(); i++) {
            String[] splitter = commands.get(i).split(" ");
            switch (splitter[0]) {
                case "forward":
                    horizontal = horizontal + Integer.valueOf(splitter[1]);
                    depth = depth + (aim * Integer.valueOf(splitter[1]));
                    break;
                case "down":
                    aim = aim + Integer.valueOf(splitter[1]);
                    break;
                case "up":
                    aim = aim - Integer.valueOf(splitter[1]);
                    break;
            }
        }
        System.out.println("Horizontal distance: " + horizontal);
        System.out.println("Depth: " + depth);
        System.out.println("Multiplied: " + (horizontal * depth));
    }
}
