package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;

import java.util.ArrayList;
import java.util.List;

public class EDIT {


    static int matchScore = 10;
    static int mismatchScore = -2;
    static int gapPenalty = -5;

    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:/dev/rosalind/rosalind_edit.txt");

//        String protein1 = "PLEASANTLY";
//        String protein2 = "MEANLY";

        String protein1 = seqList.get(0).getSequence();
        String protein2 = seqList.get(1).getSequence();

        List<String> aln = needlemanWunsch(protein1, protein2);

        System.out.println(aln.get(0));
        System.out.println(aln.get(1));
        int diff = 0;
        for(int i=0 ;i<aln.get(0).length(); i++){
            if(aln.get(0).charAt(i) != aln.get(1).charAt(i)) diff++;
        }
        System.out.println(diff);
        System.out.println(protein1.length()+protein2.length()-(longestCommonSubsequence(protein1,protein2).length()*2));

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


    public static List<String> needlemanWunsch(String protein1, String protein2) {
        int m = protein1.length();
        int n = protein2.length();
        int[][] scoreMatrix = new int[m+1][n+1];

        // Initialize the score matrix
        for (int i = 0; i <= m; i++) {
            scoreMatrix[i][0] = i * gapPenalty;
        }
        for (int j = 0; j <= n; j++) {
            scoreMatrix[0][j] = j * gapPenalty;
        }

        // Fill the score matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int match = scoreMatrix[i-1][j-1] + score(protein1.charAt(i-1), protein2.charAt(j-1));
                int delete = scoreMatrix[i-1][j] + gapPenalty;
                int insert = scoreMatrix[i][j-1] + gapPenalty;
                scoreMatrix[i][j] = Math.max(Math.max(match, delete), insert);
            }
        }

        // Traceback
        StringBuilder alignment1 = new StringBuilder();
        StringBuilder alignment2 = new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && scoreMatrix[i][j] == scoreMatrix[i-1][j-1] + score(protein1.charAt(i-1), protein2.charAt(j-1))) {
                // Diagonal move (match or mismatch)
                alignment1.append(protein1.charAt(i-1));
                alignment2.append(protein2.charAt(j-1));
                i--;
                j--;
            } else if (i > 0 && scoreMatrix[i][j] == scoreMatrix[i-1][j] + gapPenalty) {
                // Up move (deletion)
                alignment1.append(protein1.charAt(i-1));
                alignment2.append("-");
                i--;
            } else {
                // Left move (insertion)
                alignment1.append("-");
                alignment2.append(protein2.charAt(j-1));
                j--;
            }
        }

        // The optimal alignment is the reverse of the constructed strings
        List<String> res = new ArrayList<>();
        res.add(alignment1.reverse().toString());
        res.add(alignment2.reverse().toString());
        return res;
    }

    public static int score(char a, char b) {
        return (a == b) ? matchScore : mismatchScore;
    }

}
