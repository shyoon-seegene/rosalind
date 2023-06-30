package bioinformatics_armory;

import com.seegene.insilico.platform.core.bioutil.io.Seqtools;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ORFR {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\armory\\rosalind_orfr.txt"));
//        String seq = "AGCCATGTAGCTAACTCAGGTTACATGGGGATGACCCCGCGACTTGGATTAGAGTCTCTTTTGGAATAAGCCTGAATGATCCGAGTAGCATCTCAG";

        String seq = lines.get(0);

        List<String> translList = new ArrayList<>();

        translList.add(Seqtools.translate_CDS(seq, 1));
        translList.add(Seqtools.translate_CDS(seq.substring(1), 1));
        translList.add(Seqtools.translate_CDS(seq.substring(2), 1));
        translList.add(Seqtools.translate_CDS(Seqtools.GetReverseComplement(seq), 1));
        translList.add(Seqtools.translate_CDS(Seqtools.GetReverseComplement(seq).substring(1), 1));
        translList.add(Seqtools.translate_CDS(Seqtools.GetReverseComplement(seq).substring(2), 1));

        int maxLen = 0;
        String res = null;
        for(String t : translList){
            int idx = 0;
            while(idx!=-1 && idx!=t.length()-1){
                idx = t.indexOf("M", idx);
                int idx2 = t.indexOf("*",idx+1);
                if(idx2==-1) break;

                if(idx > t.length()-1 || idx==-1) break;
                if(idx2 > t.length()-1 || idx2==-1) break;
                String subSeq = t.substring(idx, idx2);
                idx = idx2;

                if(subSeq.length()>maxLen){
                    res = subSeq;
                    maxLen = subSeq.length();
                }
            }
        }
        System.out.println(res);

//        for(int i=0; i<30; i++){
//            System.out.println(Seqtools.translate_CDS(seq, i));
//            System.out.println(Seqtools.translate_CDS(seq.substring(1), i));
//            System.out.println(Seqtools.translate_CDS(seq.substring(2), i));
////            System.out.println(Seqtools.translate_CDS(Seqtools.GetReverseComplement(seq), i));
//        }


    }
}
