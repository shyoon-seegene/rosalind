package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;

public class SPLC {


    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:/dev/rosalind/stronghold/rosalind_splc.txt");

        String seq = seqList.getList().get(0).getSequence();
        for(int i=1; i<seqList.getList().size(); i++){
            seq = seq.replace(seqList.getList().get(i).getSequence(), "");
        }

        String aa = Seqtools.translate_universal(seq);
        System.out.println(aa);



    }

}
