import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Generate Pascal's Triangle
        List<List<Integer>> pyramid = new ArrayList<>();
        List<Integer> firstLine = new ArrayList<>();
        firstLine.add(1);
        pyramid.add(firstLine);

        System.out.print("Enter coefficient (koef): ");
        int koef = scanner.nextInt();

        for (int i = 1; i <= koef; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 0; j < pyramid.get(i - 1).size() - 1; j++) {
                int number = pyramid.get(i - 1).get(j) + pyramid.get(i - 1).get(j + 1);
                list.add(number);
            }
            list.add(1);
            pyramid.add(list);
        }

        for (int i = 0; i <= koef; i++) {
            for (int j = 0; j < pyramid.get(i).size(); j++) {
                System.out.print(pyramid.get(i).get(j) + " ");
            }
            System.out.println();
        }

        // Read inputs for a and b
        System.out.print("Enter a = ");
        String aInput = scanner.next();
        System.out.print("Enter b = ");
        String bInput = scanner.next();

        boolean aIsNegative = aInput.startsWith("-");
        boolean bIsNegative = bInput.startsWith("-");

        String aSymbol = aIsNegative ? aInput.substring(1) : aInput;
        String bSymbol = bIsNegative ? bInput.substring(1) : bInput;

        Integer numA = null;
        Integer numB = null;

        try {
            numA = Integer.parseInt(aSymbol); // Check if a is numeric
        } catch (Exception ignored) {}

        try {
            numB = Integer.parseInt(bSymbol); // Check if b is numeric
        } catch (Exception ignored) {}

        List<Integer> lastLine = pyramid.get(koef);

        if (numA != null && numB != null) {
            // Both `a` and `b` are numeric, compute the total result
            int totalResult = 0;
            for (int i = 0; i < lastLine.size(); i++) {
                int coefficient = lastLine.get(i);
                int termValue = coefficient * (int) Math.pow(numA, koef - i) * (int) Math.pow(numB, i);
                if (aIsNegative && (koef - i) % 2 != 0) {
                    termValue = -termValue;
                }
                if (bIsNegative && i % 2 != 0) {
                    termValue = -termValue;
                }
                totalResult += termValue;
            }
            System.out.println("Result: " + totalResult);
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < lastLine.size(); i++) {
                int coefficient = lastLine.get(i);
                int powerA = koef - i;
                int powerB = i;

                int termCoefficient = coefficient;

                if (numA != null) {
                    termCoefficient *= Math.pow(numA, powerA);
                }
                if (numB != null) {
                    termCoefficient *= Math.pow(numB, powerB);
                }

                if (aIsNegative && powerA % 2 != 0) {
                    termCoefficient = -termCoefficient;
                }
                if (bIsNegative && powerB % 2 != 0) {
                    termCoefficient = -termCoefficient;
                }

                // Append the coefficient
                if (termCoefficient == -1 && (powerA > 0 || powerB > 0)) {
                    result.append("-");
                } else if (termCoefficient != 1 || (powerA == 0 && powerB == 0)) {
                    result.append(termCoefficient);
                }

                // Append 'a' part if applicable
                if (powerA > 0 && numA == null) {
                    result.append(aSymbol);
                    if (powerA > 1) {
                        result.append("^").append(powerA);
                    }
                }

                // Append 'b' part if applicable
                if (powerB > 0 && numB == null) {
                    result.append(bSymbol);
                    if (powerB > 1) {
                        result.append("^").append(powerB);
                    }
                }

                // Append " + " unless it's the last term
                if (i < lastLine.size() - 1) {
                    result.append(" + ");
                }
            }
            String finalResult = result.toString().replaceAll("\\+ -", "- ");
            System.out.println("Result: " + finalResult);
        }
    }
}
