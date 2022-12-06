package src.day6;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import src.FileUtil;

public class Day6 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(0);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);

        int part1 = calculatePart1(fileContents);
        System.out.printf("Characters processed: %d%n", part1);

    }

    private static int calculatePart1(List<String> data) {
        int charactersProcessed = 0;
        for (String s : data) {
            charactersProcessed += findMarker(s);
        }
        return charactersProcessed;
    }

    private static int findMarker(String s) {
        Set<Character> lastFour = new HashSet<>();
        for (int i = 0; i < 3; ++i) {
            lastFour.add(s.charAt(i));
        }
        int start = 0;
        int end = 3;
        while (lastFour.size() != 4) {
            if (lastFour.add(s.charAt(end))) {
                ++end;
            } else {
                lastFour.remove(s.charAt(start));
                ++start;
            }
        }
        return end;
    }
}
