package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LEXV {

    public static List<List<Integer>> generateCombinations(int[] numbers) {
        List<List<Integer>> combinations = new ArrayList<>();
        generateCombinations(numbers, numbers.length-1, new ArrayList<Integer>(), combinations);
        return combinations;
    }

    private static void generateCombinations(int[] numbers, int index, List<Integer> currentCombination, List<List<Integer>> combinations) {
        if (index == -1) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        // Don't pick the current number
        generateCombinations(numbers, index - 1, currentCombination, combinations);

        // Pick the current number
        currentCombination.add(numbers[index]);
        generateCombinations(numbers, index - 1, currentCombination, combinations);
        currentCombination.remove(currentCombination.size() - 1);

    }

    static void getComb1(){
        int[] numbers = {1, 2, 3};
        List<List<Integer>> combinations = generateCombinations(numbers);
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_lexv.txt"));
        getComb2(lines.get(0), Integer.valueOf(lines.get(1)));
    }

    static void getComb2(String input, int num) {

        String[] arr = input.split("\\s+");
        List<List<String>> result = new ArrayList<>();
        for (int len = 1; len <= num; len++) {
            getCombinations(arr, result, new ArrayList<>(), len, 0);
        }

        List<String> orderList = Arrays.asList(arr);

        Collections.sort(result, (o1, o2) -> {
            int res = 0;
            int i=0;
            while(res==0){
                int n1 = i>=o1.size() ? -1 : orderList.indexOf(o1.get(i));
                int n2 = i>=o2.size() ? -1 : orderList.indexOf(o2.get(i));
                res = n1 - n2;
                i++;
            }
            return res;
        });

        for (List<String> combination : result) {
            StringBuilder sb = new StringBuilder();
            for(String s : combination) sb.append(s);
            System.out.println(sb.toString());
        }
    }

    public static void getCombinations(String[] arr, List<List<String>> result, List<String> combination, int len, int startPosition) {
        if (len == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }
        for (int i = startPosition; i < arr.length; i++) {
            combination.add(arr[i]);
            getCombinations(arr, result, combination, len - 1, 0); // Note: startPosition is always 0
            combination.remove(combination.size() - 1);
        }
    }
}
