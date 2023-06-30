package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

import java.util.ArrayList;
import java.util.List;

public class LCSQ {


    public static void main(String[] args) {


        String seq1 = "AACCTTGG";
        String seq2 = "ACACTGTGA";

        System.out.println(longestCommonSubsequence(seq1, seq2));
        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\rosalind_lcsq.txt");

        System.out.println(longestCommonSubsequence(seqList.get(0).getSequence(), seqList.get(1).getSequence()));

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
