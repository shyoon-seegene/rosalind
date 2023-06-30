package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class RSTR {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_rstr.txt"));

        String seq = lines.get(1);

        String[] s = lines.get(0).split("\\s+");
        double gcRatio = Double.valueOf(s[1]);
        double gc = gcRatio / 2d;
        double at = (1d-gcRatio) / 2d;

        double v = 1d;
        for(char c : seq.toCharArray()){
            if(c=='A' || c=='T') v *= at;
            if(c=='G' || c=='C') v *= gc;
        }

        double count = Double.valueOf(s[0]);
        double sum = 1;
        for(int i=0; i<count; i++){
            sum *= 1d-v;
        }
        System.out.println(String.format("%.3f", 1-sum));



    }
}
