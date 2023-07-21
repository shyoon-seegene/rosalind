package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SCSP {

    public static void main(String[] args) throws IOException {

        String seq1 = "ATCTGAT";
        String seq2 = "TGCATA";

        String lcs = longestCommonSubsequence(seq1, seq2);
        System.out.println(lcs);

        String scs = shortestCommonSupersequence(seq1, seq2, lcs);
        System.out.println(scs);

        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\stronghold\\rosalind_scsp.txt"));
        System.out.println(shortestCommonSupersequence(lines.get(0), lines.get(1), longestCommonSubsequence(lines.get(0), lines.get(1))));

    }

    public static String shortestCommonSupersequence(String s1, String s2, String lcs){

        StringBuilder sb = new StringBuilder();

        int i1 = 0;
        int i2 = 0;

        for(int i=0; i<lcs.length(); i++){
            char c = lcs.charAt(i);
            while(c!=s1.charAt(i1)){
                sb.append(s1.charAt(i1));
//                System.out.println(sb.toString() + "\t1 " + i);
                i1++;
            }
            i1++;
            while(c!=s2.charAt(i2)){
                sb.append(s2.charAt(i2));
//                System.out.println(sb.toString() + "\t2 " + i);
                i2++;
            }
            i2++;
            sb.append(c);
//            System.out.println(sb.toString() + "\tc " + i);
        }
        if(i1<s1.length()){
            for(int i=i1; i<s1.length(); i++){
                sb.append(s1.charAt(i));
            }
        }
        if(i2<s2.length()){
            for(int i=i2; i<s2.length(); i++){
                sb.append(s2.charAt(i));
            }
        }
        return sb.toString();
    }


    public static String longestCommonSubsequence(String s1, String s2) {
        int[][] lengths = new int[s1.length()+1][s2.length()+1];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                } else {
                    lengths[i+1][j+1] = Math.max(lengths[i][j+1], lengths[i+1][j]);
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int x = s1.length(), y = s2.length(); x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y]) {
                x--;
            } else if (lengths[x][y] == lengths[x][y-1]) {
                y--;
            } else {
                assert s1.charAt(x-1) == s2.charAt(y-1);
                sb.append(s1.charAt(x-1));
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }
}
