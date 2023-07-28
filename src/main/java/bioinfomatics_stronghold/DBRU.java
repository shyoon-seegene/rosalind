package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.io.Seqtools;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DBRU {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_dbru.txt"));


        Set<String> seqSet = new HashSet<>();
        List<String[]> resultList = new ArrayList<>();
        for(String line : lines){

            String reverse = Seqtools.GetReverseComplement(line);
            if(!seqSet.contains(line)){
                seqSet.add(line);
                String[] pair = {line.substring(0, line.length()-1), line.substring(1)};
                resultList.add(pair);
            }
            if(!seqSet.contains(reverse)){
                seqSet.add(reverse);
                String[] pair = {reverse.substring(0, reverse.length()-1), reverse.substring(1)};
                resultList.add(pair);
            }
        }

        Collections.sort(resultList, (o1, o2) -> {
            int res = o1[0].compareTo(o2[0]);
            if(res == 0) return o1[1].compareTo(o2[1]);
            else return res;
        });



        for(String[] res : resultList){
            System.out.println("(" + res[0] + ", " + res[1] + ")");
        }

    }

    static void fisrt(String[] lines){
        Set<String> seqSet = new HashSet<>();
        Set<String> kmerSet = new TreeSet<>();
        for(String line : lines){
            seqSet.add(line);
            String prefix = line.substring(0, line.length()-1);
            String suffix = line.substring(1);

            kmerSet.add(prefix);
            kmerSet.add(suffix);
        }

        for(String kmer : kmerSet){
            String suffix = kmer.substring(1);
            String[] bases = {"A", "C", "G", "T"};
            for(String base : bases){
                String kmer2 = suffix+base;
                if(kmerSet.contains(kmer2)) {
                    System.out.println("(" + kmer + ", " + kmer2 + ")");
                }
            }
        }
    }
}
