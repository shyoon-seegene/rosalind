package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

public class KMP {

    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\rosalind_kmp.txt");
        int[] arr = getPi(seqList.get(0).getSequence());

        for(int i : arr){
            System.out.print(" "+i);
        }

    }


    static int[] getPi(String p){
        int m = p.length(), j=0;
        int[] pi = new int[m];
        for(int i = 1; i< m ; i++){
            while(j > 0 && p.charAt(i) !=  p.charAt(j))
                j = pi[j-1];
            if(p.charAt(i) == p.charAt(j))
                pi[i] = ++j;
        }
        return pi;
    }

}
