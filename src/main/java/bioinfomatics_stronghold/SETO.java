package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SETO {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\stronghold\\rosalind_seto.txt"));

        int n = Integer.valueOf(lines.get(0));
        String arrStr1 = lines.get(1).replaceAll("\\{", "").replaceAll("\\}","");
        String arrStr2 = lines.get(2).replaceAll("\\{", "").replaceAll("\\}","");

//        System.out.println(n);
//        System.out.println(arrStr1);
//        System.out.println(arrStr2);

        Set<Integer> set1 = new HashSet<>();
        for(String s1 : arrStr1.split(",")){
            set1.add(Integer.valueOf(s1.trim()));
        }
        Set<Integer> set2 = new HashSet<>();
        for(String s2 : arrStr2.split(",")){
            set2.add(Integer.valueOf(s2.trim()));
        }

        Set<Integer> fullSet = getFullSet(n);
        System.out.println(getSum(set1, set2));
        System.out.println(getIntersection(set1, set2));
        System.out.println(getSet1ExceptSet2(set1, set2));
        System.out.println(getSet1ExceptSet2(set2, set1));
        System.out.println(getSet1ExceptSet2(fullSet, set1));
        System.out.println(getSet1ExceptSet2(fullSet, set2));

    }

    static String getSum(Set<Integer> set1, Set<Integer> set2){

        Set<Integer> sum = new HashSet<>();
        sum.addAll(set1);
        sum.addAll(set2);
        StringBuilder sb = new StringBuilder();
        for(Integer i : sum){
            sb.append(", " + i);
        }
        return "{"+sb.substring(2)+"}";
    }

    static String getIntersection(Set<Integer> set1, Set<Integer> set2){

        StringBuilder sb = new StringBuilder();
        for(Integer i : set1){
            if(set2.contains(i)) sb.append(", " + i);
        }
        return "{"+sb.substring(2)+"}";

    }

    static String getSet1ExceptSet2(Set<Integer> set1, Set<Integer> set2){

        StringBuilder sb = new StringBuilder();
        for(Integer i : set1){
            if(!set2.contains(i)) sb.append(", " + i);
        }
        return "{"+sb.substring(2)+"}";

    }

    static Set<Integer> getFullSet(int n){
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++){
            set.add(i+1);
        }
        return set;
    }

}
