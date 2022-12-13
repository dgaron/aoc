package src.day10;

import java.util.List;
import java.util.StringTokenizer;

import src.FileUtil;

public class Day10 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Day[n] [fileName]");
            System.exit(1);
        }
        List<String> fileContents = FileUtil.readFile(args[0]);

        CPU cpu = processInstructions(fileContents);

        int signalSum = getSignalStrength(cpu);
        System.out.printf("The sum of signal strengths is: %d%n", signalSum);

        drawStuff(cpu);
    }

    public static CPU processInstructions(List<String> data) {
        CPU cpu = new CPU();
        StringTokenizer st;
        for (String s : data) {
            st = new StringTokenizer(s);
            if (st.nextToken().equals("noop")) {
                cpu.noop();
            } else {
                cpu.addx(Integer.parseInt(st.nextToken()));
            }
        }
        return cpu;
    }

    public static int getSignalStrength(CPU cpu) {
        int sum = 0;
        for (int i = 20; i < 221; i += 40) {
            sum += cpu.getSignalStrength(i);
        }
        return sum;
    }

    public static void drawStuff(CPU cpu) {
        final int ROW_LENGTH = 40;
        int pixelOffset = 1;
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 40; ++j) {
                int cycle = pixelOffset + j;
                int spriteLocation = cpu.getxAt(cycle);
                int distance = Math.abs(spriteLocation - j);

                // System.out.printf("SPRITE: %d%n", spriteLocation);
                // System.out.printf("PIXEL: %d%n", j);
                if (distance <= 1) {
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            pixelOffset += ROW_LENGTH;
            System.out.println();
        }
    }
}
