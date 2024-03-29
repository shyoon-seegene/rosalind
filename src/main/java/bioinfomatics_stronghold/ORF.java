package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.io.Seqtools;

import java.util.HashSet;
import java.util.Set;

public class ORF {

    public static void main(String[] args) {

        Set<String> res = new HashSet<>();

        String a = "GAACCCATATCTTCGAAGCTGGGCCCGTGTGGTAAAATGACTGCGGGATGATGATATCTCCATCCCTTCGGCCTCTTTAGTTTGTGAAGTCCTGTCTGTATGCCGTTAGACCCCCTCCTCTTGTCGCCTAACTGTAGCAGTCCGACGAATATTTCCCCAAAATTGGTTTATATCGGGACTTAGCCCGACACCACGGCACTATCGATCAGCGTGCTCTTAAATTGGGCCGGACGATTCTTATACAGGAAATAATTCACTAGTGCGGGTGTCGCTCTCAGTGAATCCTTATATACACCATAATTGCAGCGGCCCCGAAGGGTTCGTCGGGTGATGCCGGCGAACTCTGGGGGCACAGCCGGTCGGGATGATTAGGCTACTTTCCTACACCCCATACCATAAGCAAGGTTCGTTACGGCCTTCTATGATTAATGTAAGAGCTAGGTGAGAATACGTTTAGGCCATAGCTATGGCCTAAACGTATTCTCACCTAGCTCTTACATGGTAGTACCATATCAGGCATGACGAGTTTGTGGGGACTGACTCTGGCGCTATAATGTTATGTCATTCCCCCTACGAGTCGGTGCGTCTTCGGAAACCATATCATTAGACCGTGTCCGCCGGATTGCGCGATCGGCAACCATGTAGCTCACATAACATCCGCAAGGTCGCGTGGCCTGTGTGACCCGGTCAAAATTTGTGCAACTTGGTGTAAACGGGTCTATTCTACTACTGCAACCTAAGTACTTGTAGAACAATTAGTCCTTACACAGGGGCACAAATCGAGACAGAGAAATATAGGTCCTGTTCTCGAGGATAAGAGGTACCTCATTAATTGACGGTCGAAAGGTCTGTTATACACTGACCGGCCGACTGGCGAGAGAAACATGCCATGCGATCAGCCAGGGGAAGCTGCTGCGTCACGCGGCAG";

        int index = a.indexOf("ATG");
        while(index>-1){

            String sub = a.substring(index);
            String tr = Seqtools.translate_universal(sub);
            String[] s = tr.split("\\*");
            if(s.length>1) res.add(s[0]);
            index = a.indexOf("ATG", index+1);
        }

        String b = Seqtools.GetReverseComplement(a);
        index = b.indexOf("ATG");
        while(index>-1){

            String sub = b.substring(index);
            String tr = Seqtools.translate_universal(sub);
            String[] s = tr.split("\\*");
            if(s.length>1) res.add(s[0]);
            index = b.indexOf("ATG", index+1);
        }

        for(String r : res){
            System.out.println(r);
        }

    }


}
