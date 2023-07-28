package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FULL {

    static Map<String, Float> baseMassMap = new HashMap<>();
    static Map<Float, String> massBaseMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        loadTable();

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_full.txt"));

        for(int i=0; i<lines.size(); i++){



        }


    }

    static void loadTable() throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/glossary/monoisotopic-mass-table.txt"));

        for(String line : lines){
            String[] s = line.split("\\s+");
            baseMassMap.put(s[0], Float.valueOf(s[1]));
            massBaseMap.put(Float.valueOf(s[1]), s[0]);
        }

    }

}
