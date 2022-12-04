package src.day3;

import java.util.List;    
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
                                                                                                    
import src.FileUtil;

public class Day3 {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(0);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);

        int part1 = calculatePart1(fileContents);
        System.out.printf("Part 1 Sum: %d%n", part1);

        int part2 = calculatePart2(fileContents);
        System.out.printf("Part 2 Sum: %d%n", part2);
    }

    private static int calculatePart1(List<String> data) {
        List<Set<Character>> found = new ArrayList<>();
        for (String s : data) {
            int mid = s.length() / 2;

            Set<Character> leftCharset = getUniqueChars(s.substring(0,mid));
            Set<Character> rightCharset = getUniqueChars(s.substring(mid));

            Set<Character> shared = getSetIntersection(leftCharset, rightCharset);
            found.add(shared);
        }
        int sum = calculateSum(found);
        return sum;
    }

    private static int calculatePart2(List<String> data) {
        List<Set<Character>> found = new ArrayList<>();
        for (int i = 0; i < data.size(); i += 3) {
            Set<Character> charset1 = getUniqueChars(data.get(i));
            Set<Character> charset2 = getUniqueChars(data.get(i+1));
            Set<Character> charset3 = getUniqueChars(data.get(i+2));

            Set<Character> shared12 = getSetIntersection(charset1, charset2);
            Set<Character> shared123 = getSetIntersection(shared12, charset3);
            found.add(shared123);
        }
        int sum = calculateSum(found);
        return sum;
    }

    private static Set<Character> getUniqueChars(String s) {
        Set<Character> uniqueChars = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            uniqueChars.add(s.charAt(i));
        }
        return uniqueChars;
    }

    private static Set<Character> getSetIntersection(Set<Character> s1, Set<Character> s2) {
        Set<Character> intersection = new HashSet<>(s1);
        intersection.retainAll(s2);
        return intersection;
    }

    private static int calculateSum(List<Set<Character>> found) {
        int sum = 0;
        for (int i = 0; i < found.size(); ++i) {
            for (Character c : found.get(i)) {
                sum += c - 38;
                if (Character.isLowerCase(c)) {
                    sum -= 58;
                }
            }
        }
        return sum;
    }
}
