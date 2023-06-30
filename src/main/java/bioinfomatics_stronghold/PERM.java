package bioinfomatics_stronghold;

import java.util.ArrayList;
import java.util.List;

public class PERM {

    public static List<String> generatePermutations(String s) {
        List<String> permutations = new ArrayList<>();
        generatePermutations("", s, permutations);
        return permutations;
    }

    private static void generatePermutations(String prefix, String s, List<String> permutations) {
        int n = s.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutations(prefix + " " + s.charAt(i), s.substring(0, i) + s.substring(i+1, n), permutations);
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            sb.append(i);
        }
        List<String> permutations = generatePermutations(sb.toString());
        System.out.println(permutations.size());
        for(String p : permutations){
            System.out.println(p.substring(1));
        }
    }

}
