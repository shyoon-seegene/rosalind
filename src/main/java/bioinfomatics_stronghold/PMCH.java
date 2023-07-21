package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;

import java.math.BigDecimal;

public class PMCH {

    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\stronghold\\rosalind_pmch.txt");

        String seq = seqList.get(0).getSequence();

        int AU = 0;
        int GC = 0;
        for(char c : seq.toCharArray()){
            if(c=='A') AU++;
            if(c=='G') GC++;
        }

        System.out.println(AU);
        System.out.println(GC);

        BigDecimal pAU = BigDecimal.ONE;
        for(long i=0; i<AU; i++){
            pAU = pAU.multiply(BigDecimal.valueOf(i).add(BigDecimal.ONE));
        }
        BigDecimal pGC = BigDecimal.ONE;
        for(long i=0; i<GC; i++){
            pGC = pGC.multiply(BigDecimal.valueOf(i).add(BigDecimal.ONE));
        }

        BigDecimal res = pAU.multiply(pGC);
        System.out.println(pAU);
        System.out.println(pGC);
        System.out.println(res.toString());

    }

}
