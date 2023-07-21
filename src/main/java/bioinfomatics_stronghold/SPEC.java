package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SPEC {


    static Map<String, Double> baseMassMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        loadTable();

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_spec.txt"));

        double prev = 0d;
        StringBuilder sb = new StringBuilder();
        for(String line : lines){
            if(prev==0d){
                prev = Double.valueOf(line);
                continue;
            }
            double diff = Double.valueOf(line) - prev;
            prev = Double.valueOf(line);

            double min = Double.MAX_VALUE;
            String minBase = "";
            for(String base : baseMassMap.keySet()){
                double baseMass = baseMassMap.get(base);
                if(Math.abs(diff-baseMass) < min) {
                    min = Math.abs(diff-baseMass);
                    minBase = base;
                }
            }
            sb.append(minBase);
        }

        System.out.println(sb);

    }

    static void loadTable() throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/glossary/monoisotopic-mass-table.txt"));

        for(String line : lines){
            String[] s = line.split("\\s+");
            baseMassMap.put(s[0], Double.valueOf(s[1]));
        }

    }

}
