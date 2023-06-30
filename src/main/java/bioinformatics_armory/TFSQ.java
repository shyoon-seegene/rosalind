package bioinformatics_armory;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

import java.io.File;

public class TFSQ {

    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\armory\\rosalind_tfsq.txt");

        seqList.writeAsFasta(new File("C:\\dev\\rosalind\\armory\\rosalind_tfsq.fa"));


    }
}
