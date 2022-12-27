import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PROB {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_prob.txt"));

        String seq = lines.get(0);

        String[] s = lines.get(1).split("\\s+");

        int cnt = 0;
        for(String ss : s){
            double gcRatio = Double.valueOf(ss);
            double gc = gcRatio / 2d;
            double at = (1d-gcRatio) / 2d;

            double v = 1d;
            for(char c : seq.toCharArray()){
                if(c=='A' || c=='T') v *= at;
                if(c=='G' || c=='C') v *= gc;
            }

            System.out.print(cnt==0 ? String.format("%.3f", Math.log10(v)) : " " + String.format("%.3f", Math.log10(v)));
            cnt++;
        }

    }
}
