package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SIGN {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_sign.txt"));
        int n = Integer.valueOf(lines.get(0));

//        int n = 3;

        List<Integer> nums = new ArrayList<>();
        for(int i=1; i<=n; i++){
            nums.add(i);
        }
        List<List<Integer>> list = getAllPermutations(nums, 0, new ArrayList<>());
        List<List<Integer>> combinations = new ArrayList<>();
        combinations = getCominations(n, 0, new ArrayList<>(), combinations);

        List<List<Integer>> finalList = new ArrayList<>();
        for(List<Integer> numSet : list){
            finalList.add(numSet);
            for(List<Integer> com : combinations){
                List<Integer> numSet2 = new ArrayList<>(numSet);
                for(int i : com){
                    numSet2.set(i, -numSet.get(i));
                }
                finalList.add(numSet2);
            }
        }
        System.out.println(list);
        System.out.println(finalList.size());
        for(List<Integer> l : finalList){
            for(int i=0; i<l.size(); i++){
                System.out.print(i==0 ? l.get(i) : " " + l.get(i));
            }
            System.out.print("\n");
        }


    }

    public static List<List<Integer>> getCominations(int n, int i, List<Integer> currentCombination, List<List<Integer>> combinations){

        if(i==n){
            if(currentCombination.size()>0) combinations.add(new ArrayList<>(currentCombination));
            return combinations;
//            return combinations;
        }

//        int startNum = 0;
//        if(currentCombination.size() != 0){
//            startNum = currentCombination.get(currentCombination.size()-1) + 1;
//        }
//
//        for(int j = startNum; j<n; j++){
//            currentCombination.add(j);
//            getCominations(n, i-1, currentCombination, combinations);
//            currentCombination.remove(currentCombination.size()-1);
//        }

        combinations = getCominations(n, i+1, currentCombination, combinations);
        currentCombination.add(i);
        combinations = getCominations(n, i+1, currentCombination, combinations);
        currentCombination.remove(currentCombination.size()-1);

        return combinations;
    }

    public static List<List<Integer>> getAllPermutations(List<Integer> nums, int start, List<List<Integer>> list){

        if(start == nums.size()-1){
            list.add(nums);
            return list;
        }else{
            for(int i = start; i<nums.size(); i++){
                nums = swap(nums, start, i);
                list = getAllPermutations(nums, start + 1, list);
                nums = swap(nums, start, i);
            }
        }
        return list;
    }
    public static List<Integer> swap(List<Integer> nums, int i, int j){
        List<Integer> res = new ArrayList<>();
        for(int n=0; n<nums.size(); n++){
            res.add(n==i ? nums.get(j) : n==j ? nums.get(i) : nums.get(n));
        }
        return res;
    }


}
