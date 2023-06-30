package bioinformatics_armory;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;

public class RVCO {


    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\armory\\rosalind_rvco.txt");
        int cnt = 0;
        for(SeqDomain seq : seqList.getList()){

            String rev = Seqtools.GetReverseComplement(seq.getSequence());
            if(seq.getSequence().equals(rev)){
                cnt++;
            }

        }
        System.out.println(cnt);

    }

}
