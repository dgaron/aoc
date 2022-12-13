package src.day9;

import java.util.List;

import src.FileUtil;

public class Day9 {

    private static int moveRope(List<String> data, int length) {
        Rope rope = new Rope(length);
        for (String s : data) {
            try {
               rope.moveHead(s); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rope.squaresTailVisited();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(1);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);

        int squaresVisited = moveRope(fileContents, 2);
        System.out.printf("The tail has visited %d squares.%n", squaresVisited);

        int squaresVisited2 = moveRope(fileContents, 10);
        System.out.printf("The tail has visited %d squares.%n", squaresVisited2);
    }
}
