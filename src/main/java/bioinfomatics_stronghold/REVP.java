package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class REVP {

    public static void main(String[] args) throws IOException {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\rosalind_revp.txt");
        String fseq = seqList.get(0).getSequence();
//        String fseq = "TTTAAATTTAAA";

        String rseq = Seqtools.GetReverseComplement(fseq);

        System.out.println(fseq);
        System.out.println(rseq);
        int cnt = 0;
        for(int i=0; i<fseq.length(); i++){

            for(int len=4; len<=12; len++){
                if(i+len > fseq.length()) continue;
                String fsub = fseq.substring(i, i+len);
                if(Seqtools.GetReverseComplement(fsub).equals(fsub)) System.out.println((i+1) + " " + len);
            }

        }

    }
}
