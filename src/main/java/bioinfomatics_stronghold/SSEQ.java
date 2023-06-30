package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

public class SSEQ {

    public static void main(String[] args) {


        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\rosalind_sseq.txt");

        SeqDomain total = seqList.get(0);
        SeqDomain sub = seqList.get(1);

        int[] indices = new int[sub.length()];
        int j=0;
        for(int i=0; i<total.length(); i++){

            if(total.getSequence().charAt(i) == sub.getSequence().charAt(j)){
                indices[j] = i+1;
                j++;
                i++;
            }
            if(j==sub.length()) break;
        }

        for(int i : indices){
            System.out.print(" " + i);
        }


    }
}
