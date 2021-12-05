import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        Day4 day4 = new Day4();
        String numInput = null;
        //putting everything into an arraylist just in case Collections comes in handy
        ArrayList<Integer> numbersToMark = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("TXT/day4Input.txt"));
            numInput = scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        String[] numbers = numInput.split(",");
        for (String number : numbers) {
            numbersToMark.add(Integer.valueOf(number));
        }
        //make bingo cards and boolean grids, store in arraylists
        ArrayList<int[][]> bingoCards = new ArrayList<>();
        ArrayList<boolean[][]> markers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("TXT/day4Input.txt"));
            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextInt()) {
                int[][] bingoCard = new int[5][5];
                for (int i = 0; i < 5; i++) {
                    for (int x = 0; x < 5; x++) {
                        bingoCard[i][x] = scanner.nextInt();
                    }
                }
                bingoCards.add(bingoCard);
                markers.add(new boolean[5][5]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //mark numbers based on input (numbersToMark arraylist)
        boolean winner = false;
        int[][] winningCard = bingoCards.get(0);
        boolean[][] markerWinningCard = markers.get(0);
        int winningNumber = 0;
        int index = 0;
        for (Integer number : numbersToMark) {
            if (winner == true) {
                winningNumber = numbersToMark.get(index - 1);
                break;

            }
            for (int i = 0; i < bingoCards.size(); i++) {
                if (winner == true) {
                    break;
                }
                for (int x = 0; x < bingoCards.get(i).length; x++) {
                    if (winner == true) {
                        break;
                    }
                    for (int y = 0; y < bingoCards.get(i).length; y++) {
                        System.out.println("Checking " + number + "...");
                        if (number == bingoCards.get(i)[x][y]) {
                            markers.get(i)[x][y] = true;
                        }
                        if (day4.isAllTrue(markers.get(i))) {
                            winner = true;
                            winningCard = bingoCards.get(i);
                            markerWinningCard = markers.get(i);
                            break;
                        }
                    }
                }
            }
            index++;
        }
        System.out.println(Arrays.deepToString(winningCard));
        System.out.println(winningNumber);

        int unmarkedSum = 0;
        for (int i = 0; i < winningCard.length; i++) {
            for (int x = 0; x < 5; x++) {
                if (markerWinningCard[i][x] == false) {
                    unmarkedSum = unmarkedSum + winningCard[i][x];
                }
            }
        }
        System.out.println(unmarkedSum);

        //answer part 1
        System.out.println("part 1 answer: " + unmarkedSum * winningNumber);

        //part 2 (dear god)
        ArrayList<int[][]> cardsWinning = new ArrayList<>();
        ArrayList<Integer> winningNums = new ArrayList<>();
        ArrayList<boolean[][]> marksWinning = new ArrayList<>();
        for (Integer number : numbersToMark) {
            for (int i = 0; i < bingoCards.size(); i++) {
                for (int x = 0; x < bingoCards.get(i).length; x++) {
                    for (int y = 0; y < bingoCards.get(i).length; y++) {
                        System.out.println("Checking " + number + "...");
                        if (number == bingoCards.get(i)[x][y]) {
                            markers.get(i)[x][y] = true;
                        }
                        if (day4.isAllTrue(markers.get(i))) {
                            int[][] card = new int[5][5];
                            boolean[][] bool = new boolean[5][5];
                            for (int b = 0; b < 5; b++) {
                                for (int c = 0; c < 5; c++) {
                                    card[b][c] = bingoCards.get(i)[b][c];
                                    bool[b][c] = markers.get(i)[b][c];
                                }
                            }
                            cardsWinning.add(card);
                            marksWinning.add(bool);
                            winningNums.add(number);
                            for (int z = 0; z < bingoCards.get(i).length; z++) {
                                for (int a = 0; a < bingoCards.get(i).length; a++) {
                                    bingoCards.get(i)[z][a] = -1;
                                    markers.get(i)[z][a] = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(cardsWinning.get(cardsWinning.size()-1)));
        int[][] losingCard = cardsWinning.get(cardsWinning.size() - 1);
        boolean[][] markerLosingCard = marksWinning.get(marksWinning.size() - 1);
        int losingNumber = winningNums.get(winningNums.size() - 1);

        //sum up and print part 2 answer
        int pt2Sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int x = 0; x < 5; x++) {
                if (markerLosingCard[i][x] == false) {
                    pt2Sum = pt2Sum + losingCard[i][x];
                }
            }
        }
        System.out.println("part 2 answer: " + (pt2Sum * losingNumber));
    }

    public boolean isAllTrue(boolean[][] marker) {
        for (int i = 0; i < 5; i++) {
            int verifiedRow = 0;
            for (int x = 0; x < 5; x++) {
                if (marker[i][x] == true) {
                    verifiedRow++;
                }
            }
            if (verifiedRow == 5) {
                return true;
            }
        }
        for (int i = 0; i < 5; i++) {
            int verifiedColumn = 0;
            for (int x = 0; x < 5; x++) {
                if (marker[x][i] == true) {
                    verifiedColumn++;
                }
            }
            if (verifiedColumn == 5) {
                return true;
            }
        }
        return false;
    }
}


