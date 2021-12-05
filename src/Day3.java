import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        ArrayList<String> numbers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("TXT/day3Input.txt"));
            while (scanner.hasNext()) {
                numbers.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> column = new ArrayList<>();
        //for each letter in the row...
        for (int i = 0; i < numbers.get(0).length(); i++) {
            column = new ArrayList<>();
            //for each binary string in the input, add the number from the letter we're on in loop above (blimey)
            for (int x = 0; x < numbers.size(); x++) {

                column.add(Integer.valueOf(numbers.get(x).charAt(i)));
            }
            //initialise tallys
            int tally0 = 0;
            int tally1 = 0;
            //for each number in the column we've created, tally if it's 0 or 1 respectively
            for (int y = 0; y < column.size(); y++) {
                if (column.get(y) == 48) {
                    tally0++;
                }
                if (column.get(y) == 49) {
                    tally1++;
                }
            }
            //append 0/1 to the string if it's the most common number in that bit
            if (tally0 > tally1) {
                sb.append("0");
            }
            if (tally1 > tally0) {
                sb.append("1");
            }

        }
        System.out.println("Gamma (binary): " + sb.toString());
        String gammaBinary = sb.toString();
        //string build epsilon number by inverting gamma (possibly more efficient way, but I am new!! :) )
        StringBuilder sbEpsilon = new StringBuilder();
        for (int i = 0; i < sb.toString().length(); i++) {
            char comparator = sb.toString().charAt(i);
            char zero = '0';
            char one = '1';
            if (comparator == zero) {
                sbEpsilon.append("1");
            }
            if (comparator == one) {
                sbEpsilon.append("0");
            }
        }
        System.out.println("Epsilon (binary): " + sbEpsilon.toString());
        String epsilonBinary = sbEpsilon.toString();

        //convert binary to decimal
        int multiplier = 1;
        int gammaDec = 0;
        int bit = 0;
        for (int i = gammaBinary.length() - 1; i > -1; i--) {
            if (gammaBinary.charAt(i) != '0') {
                bit = 1;
            } else {
                bit = 0;
            }
            gammaDec = gammaDec + (bit * multiplier);
            multiplier = multiplier * 2;
        }

        multiplier = 1;
        int epsilonDec = 0;
        bit = 0;
        for (int i = epsilonBinary.length() - 1; i > -1; i--) {
            if (epsilonBinary.charAt(i) != '0') {
                bit = 1;
            } else {
                bit = 0;
            }
            epsilonDec = epsilonDec + (bit * multiplier);
            multiplier = multiplier * 2;
        }
        //final prints
        System.out.println("Gamma (decimal): " + gammaDec);
        System.out.println("Epsilon (decimal): " + epsilonDec);
        System.out.println("Multiplied: " + (gammaDec * epsilonDec));
        //^^^ part 1 done ^^^

        //part 2
        ArrayList<String> numbersOxygen = new ArrayList<String>(numbers);
        String oxygenNum = null;
        char compChar = ' ';
        for (int i = 0; i < gammaBinary.length(); i++) {
            int tally0 = 0;
            int tally1 = 0;
            for (int x = 0; x < numbersOxygen.size(); x++) {
                if (numbersOxygen.get(x).charAt(i) == '1') {
                    tally1++;
                } else {
                    tally0++;
                }
            }
            if (tally0 > tally1) {
                compChar = '0';
            } else {
                compChar = '1';
            }
            System.out.println(numbersOxygen);
            if (numbersOxygen.size() == 2) {
                if (numbersOxygen.get(0).charAt(i) == '1') {
                    oxygenNum = numbersOxygen.get(0);
                    break;
                }
                oxygenNum = numbersOxygen.get(1);
                break;
            }
            if (numbersOxygen.size() == 1) {
                oxygenNum = numbersOxygen.get(0);
            }
            Iterator<String> oxyItr = numbersOxygen.iterator();
            while (oxyItr.hasNext()) {
                String currentNum = oxyItr.next();
                if (currentNum.charAt(i) != compChar) {
                    oxyItr.remove();
                }
            }
        }
        System.out.println(oxygenNum);

        ArrayList<String> numbersCo2 = new ArrayList<String>(numbers);
        String co2Num = null;
        for (int i = 0; i < epsilonBinary.length(); i++) {
            int tally0 = 0;
            int tally1 = 0;
            for (int x = 0; x < numbersCo2.size(); x++) {
                if (numbersCo2.get(x).charAt(i) == '1') {
                    tally1++;
                } else {
                    tally0++;
                }
            }
            if (tally0 > tally1) {
                compChar = '1';
            } else {
                compChar = '0';
            }
            System.out.println(numbersCo2);
            if (numbersCo2.size() == 2) {
                if (numbersCo2.get(0).charAt(i) == '0') {
                    co2Num = numbersCo2.get(0);
                    break;
                }
                co2Num = numbersCo2.get(1);
                break;
            }
            Iterator<String> co2Itr = numbersCo2.iterator();
            while (co2Itr.hasNext()) {
                String currentNum = co2Itr.next();
                if (currentNum.charAt(i) != compChar) {
                    co2Itr.remove();
                }
            }
        }
        System.out.println(co2Num);
        //convert binary to decimal
        multiplier = 1;
        int oxyDec = 0;
        bit = 0;
        for (int i = gammaBinary.length() - 1; i > -1; i--) {
            if (oxygenNum.charAt(i) != '0') {
                bit = 1;
            } else {
                bit = 0;
            }
            oxyDec = oxyDec + (bit * multiplier);
            multiplier = multiplier * 2;
        }

        multiplier = 1;
        int co2Dec = 0;
        bit = 0;
        for (int i = epsilonBinary.length() - 1; i > -1; i--) {
            if (co2Num.charAt(i) != '0') {
                bit = 1;
            } else {
                bit = 0;
            }
            co2Dec = co2Dec + (bit * multiplier);
            multiplier = multiplier * 2;
        }
        System.out.println("oxygen num: " + oxyDec);
        System.out.println("co2 num: " + co2Dec);
        System.out.println("part 2 answer: " + (oxyDec * co2Dec));
    }
}



