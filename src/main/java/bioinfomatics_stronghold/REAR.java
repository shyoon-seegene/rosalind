package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class REAR {

//    public static void main(String[] args) {
////        int[] arr1 = {1, 2, 3, 4, 5}; // First array
////        int[] arr2 = {5, 4, 3, 2, 1}; // Second array
//
//        int[] arr1 = {1, 2, 3, 4, 5, 6 ,7, 8, 9, 10}; // First permutation
//        int[] arr2 = {3, 1, 5, 2, 7, 4, 9, 6, 10, 8}; // Second permutation
//
////        int[] arr1 = {3, 10, 8, 2, 5, 4 ,7, 1, 6, 9}; // First permutation
////        int[] arr2 = {5, 2, 3, 1, 7, 4, 10, 8, 6, 9}; // Second permutation
//
//        System.out.println("Minimum number of reversals: " + calculateReversalDistance2(arr1, arr2));
//    }
//
//    public static int calculateReversalDistance2(int[] start, int[] target) {
//        Queue<int[]> queue = new LinkedList<>();
//        Map<String, Integer> distance = new HashMap<>();
//
//        queue.add(start);
//        distance.put(Arrays.toString(start), 0);
//
//        while (!queue.isEmpty()) {
//            int[] current = queue.poll();
//            System.out.println(queue.size());
//            if (Arrays.equals(current, target)) {
////                System.out.println(Arrays.toString(current));
//                return distance.get(Arrays.toString(current));
//            }
//
//            for (int i = 0; i < current.length; i++) {
//                for (int j = i + 2; j <= current.length; j++) {
//                    int[] next = reverse2(current, i, j);
//                    String nextStr = Arrays.toString(next);
//                    if (!distance.containsKey(nextStr)) {
//                        queue.add(next);
//                        distance.put(nextStr, distance.get(Arrays.toString(current)) + 1);
//                    }
//                }
//            }
//        }
//
//        return -1; // If no sequence of reversals can transform start into target
//    }
//
//    private static int[] reverse2(int[] arr, int i, int j) {
//        int[] reversed = arr.clone();
//        while (i < j) {
//            int tmp = reversed[i];
//            reversed[i] = reversed[j - 1];
//            reversed[j - 1] = tmp;
//            i++;
//            j--;
//        }
//        return reversed;
//    }
//
//    public static int calculateReversalDistance(int[] arr1, int[] arr2) {
//        int[] position = new int[arr2.length + 1];
//        for (int i = 0; i < arr2.length; i++) {
//            position[arr2[i]] = i;
//        }
//
//        int distance = 0;
//        for (int i = 0; i < arr1.length; i++) {
//            while (true) {
//                int posInArr2 = position[arr1[i]];
//                if (posInArr2 == i) {
//                    break;
//                }
//
//                System.out.println(i + " " + posInArr2);
//                System.out.println(Arrays.toString(arr1));
//                System.out.println(Arrays.toString(arr2));
//                // Reverse arr1[i] to arr1[posInArr2]
//                reverse(arr1, i, posInArr2);
//                System.out.println(i + " " + posInArr2);
//                System.out.println(Arrays.toString(arr1));
//                System.out.println(Arrays.toString(arr2));
//                // Update positions
//                for (int j = i; j <= posInArr2; j++) {
//                    position[arr1[j]] = j;
//                }
//
//                distance++;
//            }
//        }
//
//        return distance;
//    }
//
//    private static void reverse(int[] arr, int start, int end) {
//        while (start < end) {
//            int tmp = arr[start];
//            arr[start] = arr[end];
//            arr[end] = tmp;
//            start++;
//            end--;
//        }
//    }

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_rear.txt"));

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
                    reversals.add(performReversal(sequence, breakpoints.get(startIndex), breakpoints.get(endIndex)));
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
        return reversals;
    }

}
