package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SORT {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_sort.txt"));

        List<Integer> sequence = null;
        List<Integer> targetSequence = null;
        List<Integer> vList = new ArrayList<>();
        for(String line : lines){
            if(sequence==null) {
                String[] arr = line.split("\\s+");
                sequence = new ArrayList<>();
                for(String v : arr) sequence.add(Integer.valueOf(v));
                continue;
            } else if (targetSequence==null){
                String[] arr = line.split("\\s+");
                targetSequence = new ArrayList<>();
                for(String v : arr) targetSequence.add(Integer.valueOf(v));

                vList.add(getReversalDistance(sequence, targetSequence));
            } else{
                sequence = null;
                targetSequence = null;
            }
        }
        System.out.println(vList.stream().map(Object::toString).collect(Collectors.joining(" ")));

//        List<Integer> sequence = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
//        List<Integer> targetSequence = new ArrayList<>(Arrays.asList(3, 1, 5, 2, 7, 4, 9, 6, 10, 8));
//
//        System.out.println(getReversalDistance(sequence, targetSequence));

    }

    static Map<List<Integer>, List<String>> map = new HashMap<>();
    public static List<Integer> performReversal(List<Integer> sequence, int startIndex, int endIndex) {
        List<Integer> prefix = sequence.subList(0, startIndex);
        List<Integer> reversedSubsequence = new ArrayList<>(sequence.subList(startIndex, endIndex));
        Collections.reverse(reversedSubsequence);
        List<Integer> suffix = sequence.subList(endIndex, sequence.size());

        List<Integer> result = new ArrayList<>();
        result.addAll(prefix);
        result.addAll(reversedSubsequence);
        result.addAll(suffix);

        return result;
    }

    public static List<Integer> findBreakpoints(List<Integer> sequence, List<Integer> targetSequence) {
        List<Integer> breakpoints = new ArrayList<>();
        for (int i = 0; i < sequence.size() - 1; i++) {
            int currentElement = sequence.get(i);
            int adjacentElement = sequence.get(i + 1);
            if (Math.abs(targetSequence.indexOf(currentElement) - targetSequence.indexOf(adjacentElement)) != 1) {
//                System.out.println(targetSequence.indexOf(currentElement) + " " + targetSequence.indexOf(adjacentElement));
                breakpoints.add(i + 1);
            }
        }
        return breakpoints;
    }

    public static List<List<Integer>> findMinimumBreakpointReversals(List<List<Integer>> sequences, List<Integer> targetSequence) {
        List<List<Integer>> reversals = new ArrayList<>();
        for (List<Integer> sequence : sequences) {
            List<Integer> breakpoints = findBreakpoints(sequence, targetSequence);
            for (int startIndex = 0; startIndex < breakpoints.size() - 1; startIndex++) {
                for (int endIndex = startIndex + 1; endIndex < breakpoints.size(); endIndex++) {
                    List<Integer> reversal = performReversal(sequence, breakpoints.get(startIndex), breakpoints.get(endIndex));
                    reversals.add(reversal);
                    if(breakpoints.get(startIndex)==breakpoints.get(endIndex)-1) continue;
                    List<String> paths = new ArrayList<>();
                    List<String> prev = map.getOrDefault(sequence, new ArrayList<>());
                    if(prev.size()==0) prev.add("");
                    for(String p : prev){
                        String path = p + "\n" + breakpoints.get(startIndex) + " " + (breakpoints.get(endIndex)-1);
//                        System.out.println(reversal + path);
                        paths.add(path);
                    }
                    map.computeIfAbsent(reversal, v -> new ArrayList<>()).addAll(paths);
//                    System.out.print(reversal);
//                    System.out.println(breakpoints.get(startIndex) + " " + (breakpoints.get(endIndex)-1));
                }
            }
        }
        int minBp = targetSequence.size();
//        System.out.println(sequences.size() + "\t"+targetSequence);
        List<List<Integer>> minimumReversals = new ArrayList<>();
        for (List<Integer> reversal : reversals) {
            int numBreakpoints = findBreakpoints(reversal, targetSequence).size();
            if (numBreakpoints < minBp) {
                minBp = numBreakpoints;
                minimumReversals = new ArrayList<>();
                minimumReversals.add(reversal);
            } else if (numBreakpoints == minBp) {
                minimumReversals.add(reversal);
            }
        }
//        System.out.println(minimumReversals + " " + map.get(minimumReversals.get(0)));
//        System.out.println(reversals.get(0).stream().map(Object::toString).collect(Collectors.joining(",")));
        return minimumReversals;
    }

    public static int getReversalDistance(List<Integer> sequence, List<Integer> targetSequence) {
//        sequence.add(0);
//        sequence.add(sequence.size());
//        targetSequence.add(0);
//        targetSequence.add(targetSequence.size());
        sequence.add(0, Integer.MIN_VALUE);
        sequence.add(sequence.size(), Integer.MAX_VALUE);
        targetSequence.add(0, Integer.MIN_VALUE);
        targetSequence.add(targetSequence.size(), Integer.MAX_VALUE);

        int reversals = 0;
        List<List<Integer>> currentSequences = new ArrayList<>();
        currentSequences.add(sequence);
        while (!currentSequences.contains(targetSequence)) {
            currentSequences = findMinimumBreakpointReversals(currentSequences, targetSequence);
            reversals++;
        }
        System.out.println(reversals);
        System.out.println(map.get(currentSequences.get(0)).get(0).substring(1));
        return reversals;
    }

}
