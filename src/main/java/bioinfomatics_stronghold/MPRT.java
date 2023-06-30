package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MPRT {


    public static void main(String[] args) throws IOException {


        MPRT main = new MPRT();
        main.run("C:\\dev\\rosalind\\rosalind_mprt.txt");


    }

    public void run(String inFileName) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader(new File(inFileName)));

//        List<String> lines = new ArrayList<>();
//        lines.add("A2Z669");
//        lines.add("B5ZC00");
//        lines.add("P07204_TRBM_HUMAN");
//        lines.add("P20840_SAG1_YEAST");

        FileWriter fw = new FileWriter(new File("rosalind_mprt_res.txt"));

        for(String line : lines){
            SeqDomain seq = getSeq(line.split("_")[0]);
            String res = getMotifPos(seq.getSequence());
            if(res!=null){
                System.out.println(line.strip());
                System.out.println(res);
                fw.write(line.strip() + "\n");
                fw.write(res + "\n");
            }
        }
        fw.close();


    }

    private SeqDomain getSeq(String uniprotId){

        SeqDomain seq = null;

        try{
            URL url = new URL("https://rest.uniprot.org/uniprotkb/" + uniprotId + ".fasta");
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            FastSeqReader fsr = new FastSeqReader();
            fsr.loadBufferedReader(br);
            seq = fsr.nextSeq();
//            seq.printAsLine();
            fsr.close();
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return seq;
    }

    private String getMotifPos(String seq){

        StringBuilder sb = new StringBuilder();

//        String pattern = "N[^P][ST][^P]";
//        Matcher m = Pattern.compile(pattern).matcher(seq);
//
//        while(m.find()){
//            sb.append(" " + (m.start()+1));
//        }


        int s = seq.indexOf("N");
        while(s>=0){
            if(s+2 >= seq.length()) break;
            if(seq.charAt(s+1) != 'P'){
                if(seq.charAt(s+2) == 'S' || seq.charAt(s+2) == 'T'){
                    if(s+3>seq.length()){
                        sb.append(" " + (s+1));
                    } else if(seq.charAt(s+3) != 'P'){
                        sb.append(" " + (s+1));
                    }
                }
            }
            s = seq.indexOf("N", s+1);
        }

        if(sb.length()<1) return null;
        return sb.substring(1);
    }



}
