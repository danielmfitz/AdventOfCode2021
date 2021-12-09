import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Day7 day7 = new Day7();
        try {
            Scanner scanner = new Scanner(new File("TXT/day7Input.txt"));
            String num = scanner.nextLine();
            String[] numSplit = num.split(",");
            for (String number : numSplit) {
                numbers.add(Integer.parseInt(number));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int bestDepth = Integer.MAX_VALUE;
        int highestDepth = Collections.max(numbers);
        for (int i = 0; i < highestDepth; i++) {
            int sumOfCurrentDepth = 0;
            for (int y = 0; y < numbers.size(); y++) {
                sumOfCurrentDepth = sumOfCurrentDepth + (Math.abs(numbers.get(y) - i));
            }
            if (sumOfCurrentDepth < bestDepth) {
                bestDepth = sumOfCurrentDepth;
            }
        }
        System.out.println(bestDepth);
        //part 2
        bestDepth = Integer.MAX_VALUE;
        highestDepth = Collections.max(numbers);
        for (int i = 0; i < highestDepth; i++) {
            int sumOfCurrentDepth = 0;
            for (int y = 0; y < numbers.size(); y++) {
                sumOfCurrentDepth = sumOfCurrentDepth + day7.getDiffPt2(numbers.get(y), i);
            }
            if (sumOfCurrentDepth < bestDepth) {
                bestDepth = sumOfCurrentDepth;
            }
        }
        System.out.println(bestDepth);

    }
    public int getDiffPt2(int number, int currDepth) {
        int cost = 1;
        int totalCost = 0;
        while (number != currDepth) {
            totalCost = totalCost + cost;
            cost = cost + 1;
            if (number < currDepth) {
                number++;
            }
            if (number > currDepth) {
                number--;
            }
        }
        return totalCost;
    }
}
