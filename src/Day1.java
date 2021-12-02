import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("TXT/day1Input.txt"));
            while (scanner.hasNext()) {
                numbers.add(Integer.valueOf(scanner.nextLine()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int tally = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) < numbers.get(i + 1)) {
                tally++;
            }
        }
        System.out.println(tally);
        tally = 0;
        for (int i = 0; i < numbers.size() - 3; i++) {
            if (numbers.get(i) + numbers.get(i + 1) + numbers.get(i + 2) < numbers.get(i + 1) + numbers.get(i + 2) + numbers.get(i + 3)) {
                tally++;
            }
        }
        System.out.println(tally);
    }
}
