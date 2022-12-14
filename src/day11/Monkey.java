package src.day11;

import java.util.List;
import java.util.ArrayList;

public class Monkey {

    private List<Monkey> barrel;
    private List<Long> items;

    private String operator;
    private long operand;

    private int divisor;
    private int throwTrue;
    private int throwFalse;

    private long inspected;

    public Monkey(List<Monkey> barrel, String[] initializer) {
        inspected = 0;
        this.barrel = barrel;
        items = new ArrayList<>();
        parseInitializerString(initializer);
    }

    private void parseInitializerString(String[] initializer) {
        int itemStart = initializer[1].indexOf(":") + 2;
        setItems(initializer[1].substring(itemStart));
        setOperation(initializer[2]);
        setTest(initializer[3]);
        setCatchers(initializer[4], initializer[5]);
    }

    private void setItems(String itemString) {
        // Starting items: 63, 57
        String[] itemArr = itemString.split(", ");
        for (String s : itemArr) {
            items.add(Long.parseLong(s));
        }
    }

    private void setOperation(String opString) {
        // Operation: new = old + 1
        String[] arr = opString.split(" ");
        operator = arr[4];
        if (arr[5].equals("old")) {
            operator = "**";
            operand = -1;
        } else {
            operand = Integer.parseInt(arr[5]);
        }
    }

    private void setTest(String testString) {
        // Test: divisible by 7
        String[] arr = testString.split(" ");
        divisor = Integer.parseInt(arr[3]);
    }

    private void setCatchers(String trueCatcher, String falseCatcher) {
        // If true: throw to monkey 6
        // If false: throw to monkey 2
        String[] trueArr = trueCatcher.split(" ");
        throwTrue = Integer.parseInt(trueArr[5]);
        String[] falseArr = falseCatcher.split(" ");
        throwFalse = Integer.parseInt(falseArr[5]);
    }

    public void inspectItems() {
        List<Long> results = new ArrayList<>();
        for (long n : items) {
            ++inspected;
            results.add(operate(n));
        }
        for (long n : results) {
            test(n);
        }
        items.clear();
    }

    private long operate(long num) {
        if (operator.equals("+")) {
            num += operand;
        } else if (operator.equals("*")) {
            num *= operand;
        } else if (operator.equals("**")) {
            num *= num;
        }
        // Part 1: Divide by 3, round down
        // num = Math.floorDiv(num, 3);
        // Part 2: Mod by LCM: 9'699'690
        // Too lazy to modify for worry reduction & LCM calculation based on part 1 or 2
        num %= 9699690;
        return num;
    }

    private void test(long num) {
        if (num % divisor == 0) {
            barrel.get(throwTrue).addItem(num);
        } else {
            barrel.get(throwFalse).addItem(num);
        }
    }

    public void addItem(long item) {
        items.add(item);
    }

    public void printItems() {
        for (long item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public long getInspected() {
        return inspected;
    }
}
