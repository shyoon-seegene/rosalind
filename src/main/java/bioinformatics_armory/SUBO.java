package bioinformatics_armory;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SUBO {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\armory\\rosalind_subo_res.txt"));

        int cntS = 0;
        int cntT = 0;
        int[] sArr = new int[2000];
        int[] tArr = new int[2000];
        for(String line : lines){

            if(line.contains("identity")){
                String[] s1 = line.split(" nt overlap ");

                String[] s1s = s1[0].split("\\s+");
                int len = Integer.valueOf(s1s[s1s.length-1]);
                if(len >50 || len < 32) continue;

                String posS = s1[1].replace("(", "").replace(")", "").split(":")[0];
                String posT = s1[1].replace("(", "").replace(")", "").split(":")[1];
                System.out.println(posS + "\t"+posT);
                int ss = Integer.valueOf(posS.split("-")[0]);
                int se = Integer.valueOf(posS.split("-")[1]);

                boolean sNew = true;
                for(int i=ss; i<se; i++){
                    if(sArr[i]>0) sNew=false;
                    else sArr[i] = 1;
                }
                if(sNew) cntS++;

                int ts = Integer.valueOf(posT.split("-")[0]);
                int te = Integer.valueOf(posT.split("-")[1]);

                boolean tNew = true;
                for(int i=ts; i<te; i++){
                    if(tArr[i]>0) tNew=false;
                    else tArr[i] = 1;
                }
                if(tNew) cntT++;
            }
        }
        System.out.println(cntS + " " + cntT);


    }

}
