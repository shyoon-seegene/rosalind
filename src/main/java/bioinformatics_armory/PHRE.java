package bioinformatics_armory;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PHRE {

    public static void main(String[] args) throws IOException {

//        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\armory\\rosalind_phre.txt");
        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\armory\\rosalind_phre.txt"));

        int cutoff = Integer.valueOf(lines.get(0));

        SeqDomainList seqList = new SeqDomainList();
        for(int i=1; i<lines.size(); i++){
            SeqDomain seq = new SeqDomain(lines.get(i), lines.get(i+1));
            seq.setQuality(lines.get(i+3));
            i+=3;
            seqList.add(seq);
        }
        int count = 0;
        for(SeqDomain seq : seqList.getList()){
            int[] qualArr = Seqtools.ascII2qual(seq.getQuality());

            int sumq = 0;
            for(int q : qualArr) sumq += q;
            double avg = (double) sumq/ (double) qualArr.length;
            System.out.println(avg);
            if(avg<cutoff) count++;
        }
        System.out.println("cnt:" + count);


    }
}
