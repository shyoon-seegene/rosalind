package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

public class TRAN {

    public static void main(String[] args) {


        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\rosalind_tran.txt");


        String seq1 = seqList.get(0).getSequence();
        String seq2 = seqList.get(1).getSequence();

        int transitions = 0;
        int transversions = 0;
        for(int i=0; i<seq1.length(); i++){

            if(seq1.charAt(i) != seq2.charAt(i)){

                if(seq1.charAt(i)=='G' && seq2.charAt(i)=='A' || seq1.charAt(i)=='A' && seq2.charAt(i)=='G'
                || seq1.charAt(i)=='C' && seq2.charAt(i)=='T' || seq1.charAt(i)=='T' && seq2.charAt(i)=='C'){
                    transitions++;
                }else{
                    transversions++;
                }

            }

        }

        double ratio = transitions*1d / transversions*1d;
        System.out.println(ratio);
        System.out.println(String.format("%.11f",ratio));



    }
}
