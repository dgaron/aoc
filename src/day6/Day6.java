package src.day6;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import src.FileUtil;

public class Day6 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(1);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);

        int part1 = countCharactersProcessed(fileContents, 4);
        System.out.printf("Characters processed: %d%n", part1);

        int part2 = countCharactersProcessed(fileContents, 14);
        System.out.printf("Characters processed: %d%n", part2);

    }

    private static int countCharactersProcessed(List<String> data, int length) {
        int charactersProcessed = 0;
        for (String s : data) {
            charactersProcessed += findMarker(s, length);
        }
        return charactersProcessed;
    }

    private static int findMarker(String s, int length) {
        Set<Character> lastFour = new HashSet<>();
        int start = 0;
        int end = 0;
        while (lastFour.size() != length && end < s.length()) {
            if (lastFour.add(s.charAt(end))) {
                ++end;
            } else {
                lastFour.remove(s.charAt(start));
                ++start;
            }
        }
        if (lastFour.size() != length) {
            return -1; // Ain't no substrings with 'length' consecutive unique characters
        }
        return end;
    }
}
