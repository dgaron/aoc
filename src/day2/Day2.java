package src.day2;
                                                                        
import java.util.List;                                                                             
                                                                                                    
import src.FileUtil;
                                                                                                    
public class Day2 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(1);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);
        
        int part1 = calculateScore(fileContents, 1);
        System.out.printf("Score: %d%n", part1);

        int part2 = calculateScore(fileContents, 2);
        System.out.printf("Score: %d%n", part2);
    }

    private static int calculateScore(List<String> data, int part) {

        int[][] resultMatrix = new int[3][3];

        if (part == 1) {
            int[][] part1 = { {4, 8, 3},
                              {1, 5, 9},
                              {7, 2, 6} };
            resultMatrix = part1;
        } else if (part == 2) {
            int[][] part2 = { {3, 4, 8},
                              {1, 5, 9},
                              {2, 6, 7} };
            resultMatrix = part2;
        }

        int score = 0;
        for (String s : data) {
            int opp = s.charAt(0) - 65; // ASCII Offset 65 -> 0
            int me = s.charAt(2) - 88;  // ASCII Offset 88 -> 0
            int result = resultMatrix[opp][me];
            score += result;
        }
        return score;
    }

}