import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        Day6 prog = new Day6();
        ArrayList<String> days = new ArrayList<>();
        ArrayList<Lanternfish> listOfFish = new ArrayList<>();
        String input = null;
        try {
            Scanner scanner = new Scanner(new File("TXT/day6Input.txt"));
            input = scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        String[] inputSplit = input.split(",");
        for (String number : inputSplit) {
            listOfFish.add(new Lanternfish(Integer.parseInt(number)));
        }
        for (int i = 0; i < 80; i++) {
            System.out.println(prog.theNextDay(listOfFish));
        }

        System.out.println("--- PART 2---");
        //I am going to have to rewrite stuff to get this to work lol
        //Going to keep my solution full for part 1 however
        Lanternfish2 fishPartTwo = new Lanternfish2();
        for (String number : inputSplit) {
            fishPartTwo.addNumber(number);
        }
        for (int i = 0; i < 256; i++) {
            System.out.println((fishPartTwo.nextDay().toString()));
        }
    }

    public int theNextDay(ArrayList<Lanternfish> listOfFish) {
        int newFishCount = 0;
        for (Lanternfish fish : listOfFish) {
            boolean createFish = fish.newDay();
            if (createFish == true) {
                newFishCount++;
            }
        }
        for (int i = 0; i < newFishCount; i++) {
            listOfFish.add(new Lanternfish(8));
        }
        return listOfFish.size();
    }
}

class Lanternfish {
    //part 1 lanternfish solution
    private int currentTime;

    public Lanternfish(int currentTime) {
        this.currentTime = currentTime;
    }

    public boolean newDay() {
        if (currentTime == 0) {
            this.currentTime = 6;
            return true;
        }
        this.currentTime--;
        return false;
    }
}

class Lanternfish2 {
    //part 2 lanternfish (actually works now!)
    private long nine = 0;
    private long eight = 0;
    private long seven = 0;
    private long six = 0;
    private long five = 0;
    private long four = 0;
    private long three = 0;
    private long two = 0;
    private long one = 0;
    private long zero = 0;

    public void addNumber(String number) {
        switch (number) {
            case "8":
                eight++;
                break;
            case "7":
                seven++;
                break;
            case "6":
                six++;
                break;
            case "5":
                five++;
                break;
            case "4":
                four++;
                break;
            case "3":
                three++;
                break;
            case "2":
                two++;
                break;
            case "1":
                one++;
                break;
        }
    }

    public BigInteger nextDay() {
        long newBabies = zero;
        //'newBabies' is also how many fish we need to reset
        zero = one;
        one = two;
        two = three;
        three = four;
        four = five;
        five = six;
        six = seven + newBabies;
        seven = eight;
        eight = newBabies;
        BigInteger added = new BigInteger("0");
        added = added.add(BigInteger.valueOf(zero));
        added = added.add(BigInteger.valueOf(one));
        added = added.add(BigInteger.valueOf(two));
        added = added.add(BigInteger.valueOf(three));
        added = added.add(BigInteger.valueOf(four));
        added = added.add(BigInteger.valueOf(five));
        added = added.add(BigInteger.valueOf(six));
        added = added.add(BigInteger.valueOf(seven));
        added = added.add(BigInteger.valueOf(eight));
        return added;
    }
}
