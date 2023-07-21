package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class CORR {

    public static void main(String[] args) {


        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\stronghold\\rosalind_corr.txt");

        Map<String, Integer> map = new LinkedHashMap<>();
        Set<String> oriSet = new HashSet<>();
        for(SeqDomain seq : seqList.getList()){

            String fseq = seq.getSequence();
            oriSet.add(fseq);
            String rseq = Seqtools.GetReverseComplement(fseq);

            map.merge(fseq, 1, Integer::sum);
            if(fseq!=rseq)
                map.merge(rseq, 1, Integer::sum);
        }

        List<String> correct = new ArrayList<>();
        List<String> incorrect = new ArrayList<>();

        for(String seq : map.keySet()){
            if(map.get(seq)>1){
                correct.add(seq);
            }else{
                if(oriSet.contains(seq)) incorrect.add(seq);
            }
        }
        System.out.println(correct.size());
        System.out.println(incorrect.size());
        for(String seq : incorrect){
            for(String cSeq : correct){
//                System.out.println(getHammingDistance(seq, cSeq) + ":"+seq+":"+cSeq);
                if(getHammingDistance(seq, cSeq) == 1){
                    System.out.println(seq + "->" + cSeq);
                    break;
                }
            }
        }

    }

    private static int getHammingDistance(String seq1, String seq2){
        int cnt = 0;
        for(int i=0; i<seq1.length(); i++) {
            char c1 = seq1.charAt(i);
            char c2 = seq2.charAt(i);
            if(c1!=c2) cnt++;
        }
        return cnt;
    }


}
