package src.day11;

import java.util.List;
import java.util.ArrayList;

import src.FileUtil;

public class Day11 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(1);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);
        
        List<Monkey> barrel = initializeMonkeys(fileContents);

        // Part 1: 20 rounds
        // Part 2: 10'000 rounds
        monkeyRound(barrel, 20);
        
        for (Monkey m : barrel) {
            m.printItems();
        }

        long monkeyBusiness = getMonkeyBusiness(barrel);

        System.out.printf("Monkey business: %d%n", monkeyBusiness);

    }

    private static List<Monkey> initializeMonkeys(List<String> data) {
        List<Monkey> barrel = new ArrayList<>();
        String[] monkeyInitializer = new String[6];

        for (int i = 0; i < data.size(); ++i) {
            String s = data.get(i);
            int index = i % 7;
            if (index == 6) {
                barrel.add(new Monkey(barrel, monkeyInitializer));
            } else {
                monkeyInitializer[index] = s.trim();
            }
        }
        // Maybe I'm just dumb but I could not get this last monkey added in the loop
        barrel.add(new Monkey(barrel, monkeyInitializer));
        return barrel;
    }

    private static void monkeyRound(List<Monkey> barrel, int numRounds) {
        for (int i = 0; i < numRounds; ++i) {
            for (Monkey m : barrel) {
                m.inspectItems();
            }
        }
    }

    private static long getMonkeyBusiness(List<Monkey> barrel) {
        long topDog = 0;
        long consiglieri = 0;
        for (int i = 0; i < barrel.size(); ++i) {
            long num = barrel.get(i).getInspected();
            System.out.printf("Monkey %d inspected items %d times%n", i, num);
            if (num > topDog) {
                consiglieri = topDog;
                topDog = num;
            } else if (num > consiglieri) {
                consiglieri = num;
            }
        }
        return topDog * consiglieri;
    }
}
