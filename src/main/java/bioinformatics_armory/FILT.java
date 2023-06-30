package bioinformatics_armory;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FILT {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\armory\\rosalind_filt.txt"));

        int qCutOff = Integer.valueOf(lines.get(0).split("\\s+")[0]);
        int qCov = Integer.valueOf(lines.get(0).split("\\s+")[1]);

        SeqDomainList seqList = new SeqDomainList();
        for(int i=1; i<lines.size(); i++){
            SeqDomain seq = new SeqDomain(lines.get(i), lines.get(i+1));
            seq.setQuality(lines.get(i+3));
            i+=3;
            seqList.add(seq);
        }

        int res = 0;
        for(SeqDomain seq : seqList.getList()){

            String qual = seq.getQuality();
            int[] qArr = Seqtools.ascII2qual(qual);
            int passCnt = 0;
            for(int q : qArr){
                if(q>=qCutOff) passCnt++;
            }
            System.out.println(seq.getTitle() + "\t" + ((double) passCnt/qual.length())*100);
            if( ((double) passCnt/qual.length())*100 >= qCov ) res++;

        }
        System.out.println(res);

    }
}
