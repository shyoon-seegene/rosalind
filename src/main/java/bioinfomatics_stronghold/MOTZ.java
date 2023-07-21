package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

import java.util.HashMap;
import java.util.Map;

public class MOTZ {

    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\stronghold\\rosalind_motz.txt");

        String seq = seqList.get(0).getSequence();
//        String seq = "GCAUGCAUGCA";
        System.out.println(motz(seq));

//        System.out.println("The " + n + "th Motzkin number is " + motzkin2(n));
//
//        for(int i=0; i<300; i++){
//            System.out.println(i + " : " + motzkin2(i));
//        }

    }

    private static Map<String, Integer> map = new HashMap<>();

    public static char pair(char c){
        switch (c){
            case 'A': return 'U';
            case 'U': return 'A';
            case 'G': return 'C';
            case 'C': return 'G';
            default: return '-';
        }
    }

    private static int motz (String seq){

        if(seq.length()==1) return 1;

        Integer prev = map.get(seq);
        if(prev!=null){
            System.out.println(seq + ":" + prev);
            return prev;
        }
//        Integer[] nAUCG = new Integer[]{0,0,0,0};
        StringBuilder sb = new StringBuilder();

        char fEnd = seq.charAt(0);
        char pair = pair(fEnd);
        int sum = motz(seq.substring(1));
//        nAUCG = calN(nAUCG, fEnd);

        for(int i=1; i<seq.length(); i++){
            char c = seq.charAt(i);
            sb.append(c);
//            nAUCG = calN(nAUCG, c);
            if(c==pair){
                String left = seq.substring(1,i);
                String right = seq.substring(i+1);
                int nLeft = left.length()==0 ? 1 : motz(left);
                int nRight = right.length()==0 ? 1 : motz(right);
                long res = ((long) nLeft * (long) nRight)%1000000L;
                if(res == 0) continue;
                else {
                    sum += res;
                    sum = sum%1000000;
                }

            }
        }
        if(sum==0) return 0;
        int value = sum%1000000;
        map.put(seq, value);
        return value;

    }

    public static long motzkin(int n) {
        // Base cases
        if (n == 0 || n == 1) {
            return 1;
        }

        // Initialize the first two Motzkin numbers
        long[] motzkinNumbers = new long[n+1];
        motzkinNumbers[0] = 1;
        motzkinNumbers[1] = 1;

        // Calculate the nth Motzkin number
        for (int i = 2; i <= n; i++) {
            motzkinNumbers[i] = ((2 * i + 1) * motzkinNumbers[i-1]  + 3 * (i - 1) * motzkinNumbers[i-2]) / (i + 2);
        }

        return motzkinNumbers[n];
    }

    public static long motzkinRosalind(int n) {
        // Base cases
        if (n == 0 || n == 1) {
            return 1;
        }

        // Initialize the first two Motzkin numbers
        long[] motzkinNumbers = new long[n+1];
        motzkinNumbers[0] = 1;
        motzkinNumbers[1] = 1;

        // Calculate the nth Motzkin number
        for (int i = 2; i <= n; i++) {
            motzkinNumbers[i] = motzkinRosalind(i-1);
            for(int k=2; k<=n; k++){
                motzkinNumbers[i] += motzkinRosalind(k-2)*motzkinRosalind(n-k);
            }
        }

        return motzkinNumbers[n];
    }

    public static long motzkinMod(int n) {
        // Base cases
        if (n == 0 || n == 1) {
            return 1;
        }

        // Initialize the first two Motzkin numbers
        long[] motzkinNumbers = new long[n+1];
        motzkinNumbers[0] = 1;
        motzkinNumbers[1] = 1;

        // Calculate the nth Motzkin number
        for (int i = 2; i <= n; i++) {
            motzkinNumbers[i] = (((2 * i + 1) * motzkinNumbers[i-1])%1000000 + (3 * (i - 1) * motzkinNumbers[i-2])%1000000) / (i + 2);
        }

        return motzkinNumbers[n];
    }

    public static long motzkin2(int n) {
        // Base cases
        if (n == 0 || n == 1) {
            return 1;
        }

        // Initialize the first two Motzkin numbers
        long[] motzkinNumbers = new long[n+1];
        motzkinNumbers[0] = 1;
        motzkinNumbers[1] = 1;

        // Calculate the nth Motzkin number
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum += (motzkin2(i-2)*motzkin2(n-i)) % 1000000;
        }
        motzkinNumbers[n] = motzkin2(n-1) + sum;

        return motzkinNumbers[n];
    }
}
