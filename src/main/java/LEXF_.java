import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LEXF_ {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_lexf.txt"));

        String[] words = lines.get(0).split("\\s+");
        int n = Integer.valueOf(lines.get(1));


        Set<String> set = new TreeSet<>(Arrays.asList(words));
        set = getKmer(words, n, 1, set);

        for(String kmer : set){
            System.out.println(kmer);
        }
    }

    static Set<String> getKmer(String[] words, int n, int i, Set<String> set){

        if(i==n) return set;
        System.out.println(i + "\t" + set);
        Set<String> newset = new TreeSet<>();
        for(String s : set){
            for(String w : words){
                newset.add(s.concat(w));
            }
        }
        i++;
        return getKmer(words, n, i, newset);
    }
}
