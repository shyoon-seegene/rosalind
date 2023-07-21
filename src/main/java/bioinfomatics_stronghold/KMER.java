package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class KMER {

    public static void main(String[] args) {

        Map<String, Integer> kmerCountMap = new LinkedHashMap<>();

        generateKmers(4, "", kmerCountMap);

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\stronghold\\rosalind_kmer.txt");
        String seq = seqList.getList().get(0).getSequence();

//        String seq = "CTTCGAAAGTTTGGGCCGAGTCTTACAGTCGGTCTTGAAGCAAAGTAACGAACTCCACGGCCCTGACTACCGAACCAGTTGTGAGTACTCAACTGGGTGAGAGTGCAGTCCCTATTGAGTTTCCGAGACTCACCGGGATTTTCGATCCAGCCTCAGTCCAGTCTTGTGGCCAACTCACCAAATGACGTTGGAATATCCCTGTCTAGCTCACGCAGTACTTAGTAAGAGGTCGCTGCAGCGGGGCAAGGAGATCGGAAAATGTGCTCTATATGCGACTAAAGCTCCTAACTTACACGTAGACTTGCCCGTGTTAAAAACTCGGCTCACATGCTGTCTGCGGCTGGCTGTATACAGTATCTACCTAATACCCTTCAGTTCGCCGCACAAAAGCTGGGAGTTACCGCGGAAATCACAG";

        for(int i=0; i<seq.length()-3; i++){
            String kmer = seq.substring(i,4+i);
            System.out.println(kmer);
            kmerCountMap.merge(kmer, 1, Integer::sum);
        }

        for(String kmer : kmerCountMap.keySet()){
            System.out.print(" " + kmerCountMap.get(kmer));
        }

    }

    public static void generateKmers(int k, String prefix, Map<String, Integer> kmerCountMap) {
        if (k == 0) {
            kmerCountMap.put(prefix, 0);
            return;
        }
        for (char c : new char[]{'A', 'C', 'G', 'T'}) {
            generateKmers(k - 1, prefix + c, kmerCountMap);
        }
    }


}
