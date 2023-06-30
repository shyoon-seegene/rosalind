package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EVAL {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_eval.txt"));

        int len = Integer.valueOf(lines.get(0));
        String seq = lines.get(1);
        String[] s = lines.get(2).split("\\s+");
        double[] gcArr = new double[s.length];
        for(int i=0; i<s.length; i++){
            gcArr[i] = Double.valueOf(s[i]);
        }

        double[] res = new double[s.length];
        for(int i=0; i<s.length; i++){
            double gc = gcArr[i] / 2d;
            double at = (1d-gcArr[i]) / 2d;
            double v=1;
            for(char c : seq.toCharArray()){
                if(c=='A' || c=='T') v *= at;
                if(c=='G' || c=='C') v *= gc;
            }
            res[i] = v*(len-seq.length()+1);
        }

        for(int i=0; i<s.length; i++){
            System.out.print(" " + String.format("%.3f", res[i]));
        }




    }
}
