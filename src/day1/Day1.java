package src.day1;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import src.FileUtil;
                                                                                                    
public class Day1 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(0);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);

        List<Integer> sums = calculateTotals(fileContents);

        Collections.sort(sums);
        Collections.reverse(sums);

        int part1 = sums.get(0);
        System.out.printf("Max Calories: %s%n", part1);
        
        int part2 = 0;
        for (int i = 0; i < 3; ++i) {
            part2 += sums.get(i);
        }
        System.out.printf("Top Three Calories Sum: %d%n", part2);
    }

    private static List<Integer> calculateTotals(List<String> inputData) {
        List<Integer> sums = new ArrayList<>();
        int currentSum = 0;
        for (String s : inputData) {
            if (s.isEmpty()) {
                sums.add(currentSum);
                currentSum = 0;
            } else {
                currentSum += Integer.valueOf(s);
            }
        }
        return sums;
    }

}