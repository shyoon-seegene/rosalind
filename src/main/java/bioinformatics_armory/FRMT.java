package bioinformatics_armory;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FRMT {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\armory\\rosalind_frmt.txt"));

        String[] s = lines.get(0).split("\\s+");


        int minLen = Integer.MAX_VALUE;
        SeqDomain res = null;
        for(String acc : s){
            SeqDomain seq = loadWithEutils(acc, 5);
            if(seq.length()<minLen){
                res = seq;
                minLen = seq.getSequence().length();
            }
        }

        System.out.println(">"+res.getTitle());
        for(int i=0; i<res.getSequence().length(); i++){
            System.out.print(res.getSequence().charAt(i));
            if((i+1)%70==0) System.out.print("\n");
        }


    }

    public static SeqDomain loadWithEutils(String accgi, int numOfTry) {
        SeqDomain seq = null;
        FastSeqReader fsr = new FastSeqReader();
        URL url = null;
        String urlStr = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nucleotide&id=" + accgi + "&retmode=text&rettype=fasta";
        try {
            System.out.println(urlStr);
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        boolean loadFailed = true;
        for (int i = 0; i < numOfTry; i++) {
            try {
                InputStream is = url.openStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                fsr.loadBufferedReader(br);
                seq = fsr.nextSeq();
            } catch (IOException e) {
                continue;
            }
            loadFailed = false;
            break;
        }
        return seq;
    }

}
