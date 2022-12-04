package src.day4;

import java.util.List;

import src.FileUtil;

public class Day4 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(0);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);

        int part1 = calculatePart1(fileContents);
        System.out.printf("Contained intervals: %s%n", part1);

        int part2 = calculatePart2(fileContents);
        System.out.printf("Overlapping intervals: %s%n", part2);
    }

    private static int calculatePart1(List<String> data) {
        int overlappingPairs = 0;
        for (String s : data) {
            int[] intervals = parseIntervalString(s);
            if (containedInterval(intervals)) {
                    ++overlappingPairs;
            }

        }
        return overlappingPairs;
    }

    private static boolean containedInterval(int[] intervals) {
        int leftStart = intervals[0];
        int leftEnd = intervals[1];
        int rightStart = intervals[2];
        int rightEnd = intervals[3];
        if ((leftStart <= rightStart && leftEnd >= rightEnd) ||
            (leftStart >= rightStart && leftEnd <= rightEnd)) {
            return true;
        }
        return false;
    }

    private static int calculatePart2(List<String> data) {
        int overlappingPairs = 0;
        for (String s : data) {
            int[] intervals = parseIntervalString(s);
            if (overlappingInterval(intervals)) {
                ++overlappingPairs;
            }

        }
        return overlappingPairs;
    }

    private static boolean overlappingInterval(int[] intervals) {
        int leftStart = intervals[0];
        int leftEnd = intervals[1];
        int rightStart = intervals[2];
        int rightEnd = intervals[3];
        if ((leftStart >= rightStart && leftStart <= rightEnd) ||
            (rightStart >= leftStart && rightStart <= leftEnd)) {
            return true;
        }
        return false;
    }

    private static int[] parseIntervalString(String s) {
        String[] pair = s.split(",");
        String[] left = pair[0].split("-");
        String[] right = pair[1].split("-");

        int[] intervalEnds = new int[4];

        intervalEnds[0] = Integer.valueOf(left[0]);
        intervalEnds[1] = Integer.valueOf(left[1]);
        intervalEnds[2] = Integer.valueOf(right[0]);
        intervalEnds[3] = Integer.valueOf(right[1]);

        return intervalEnds;
    }
}
