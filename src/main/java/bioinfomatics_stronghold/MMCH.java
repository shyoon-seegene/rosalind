package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

import java.math.BigInteger;

public class MMCH {

    public static void main(String[] args) {


        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\stronghold\\stronghold\\rosalind_mmch.txt");

        String seq = seqList.get(0).getSequence();
        System.out.println(seq);
        int nA = 0;
        int nU = 0;
        int nG = 0;
        int nC = 0;
        for (char c : seq.toCharArray()) {
            switch (c) {
                case 'A':
                    nA++;
                    break;
                case 'U':
                    nU++;
                    break;
                case 'G':
                    nG++;
                    break;
                case 'C':
                    nC++;
                    break;
                default:
                    break;

            }
        }

        int maxAU = Math.max(nA, nU);
        int minAU = Math.min(nA, nU);
        int maxGC = Math.max(nG, nC);
        int minGC = Math.min(nG, nC);

        System.out.println(nA + " " + nU + " " + nC + " " + nG);
        System.out.println(getPairCount(maxAU, minAU).multiply(getPairCount(maxGC, minGC)));
        System.out.println(nCr(maxAU, minAU) * nCr(minAU, minAU) * nCr(maxGC, minGC) * (minGC ^ 2));
        System.out.println(nA * nU * nC * nG);
        System.out.println(Integer.MAX_VALUE);


    }

    private static BigInteger getPairCount(int max, int min){

        BigInteger start = new BigInteger(String.valueOf(max));
        BigInteger res = start;
        for(int i=0; i<min-1; i++){
            res = res.multiply(start.subtract(new BigInteger(String.valueOf(i+1))));
//            System.out.println(max + " " + min + " " + res);
        }
        System.out.println(max + " " + min + " " + res);
        return res;
    }


    private static int nCr(int n, int r) {
        if (r > n) {
            return 0;
        }
        if (r == 0 || r == n) {
            return 1;
        }
        int[][] dp = new int[n + 1][r + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r && j <= i; j++) {
                try {
                    dp[i][j] = Math.addExact(dp[i - 1][j - 1], dp[i - 1][j]);
                } catch (ArithmeticException e) {
                    return -1;
                }
            }
        }
        return dp[n][r];

    }
}
