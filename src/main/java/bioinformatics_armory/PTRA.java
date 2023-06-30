package bioinformatics_armory;

import com.seegene.insilico.platform.core.bioutil.io.Seqtools;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PTRA {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\armory\\rosalind_ptra.txt"));

        String dna = lines.get(0);
        for(int i=1; i<30; i++){
            String transl = Seqtools.translate_CDS(dna, i);
            if(lines.get(1).equals(transl)) System.out.println(i + "\t" + transl);
        }



    }

}
