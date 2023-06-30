package bioinformatics_armory;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class INI {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:\\dev\\rosalind\\armory\\rosalind_ini.txt"));

        String seq = lines.get(0);

        int nA = 0;
        int nC = 0;
        int nG = 0;
        int nT = 0;
        for(char c : seq.toCharArray()){
            switch (c){
                case 'A': nA++; break;
                case 'C': nC++; break;
                case 'G': nG++; break;
                case 'T': nT++; break;
                default: break;
            }
        }

        System.out.println(nA + " " + nC + " " + nG + " " + nT);

    }
}
