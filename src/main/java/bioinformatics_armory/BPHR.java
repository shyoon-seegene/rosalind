package bioinformatics_armory;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BPHR {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\armory\\rosalind_bphr.txt"));

        int qCutOff = Integer.valueOf(lines.get(0));

        SeqDomainList seqList = new SeqDomainList();
        for(int i=1; i<lines.size(); i++){
            SeqDomain seq = new SeqDomain(lines.get(i), lines.get(i+1));
            seq.setQuality(lines.get(i+3));
            i+=3;
            seqList.add(seq);
        }
        int cnt = 0;
        int[] sumQarr = new int[seqList.get(0).getSequence().length()];
        for(SeqDomain seq : seqList.getList()){
            int[] qArr = Seqtools.ascII2qual(seq.getQuality());
            for(int i=0; i<qArr.length; i++){
                sumQarr[i] += qArr[i];
            }
        }

        for(int q : sumQarr){
            if( ((double)q / seqList.getListSize()) < qCutOff ) cnt++;
        }

        System.out.println(cnt);

    }
}
