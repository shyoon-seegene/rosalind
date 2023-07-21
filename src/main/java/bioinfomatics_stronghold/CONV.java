package bioinfomatics_stronghold;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CONV {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_conv.txt"));

        String[] s1 = lines.get(0).split("\\s+");
        String[] s2 = lines.get(1).split("\\s+");

        List<Float> d1 = new ArrayList<>();
        List<Float> d2 = new ArrayList<>();

        for(String s : s1) d1.add(Float.valueOf(s));
        for(String s : s2) d2.add(Float.valueOf(s));

        List<Float> diff = new ArrayList<>();
        for(Float dd1 : d1) for(Float dd2 : d2){
            diff.add(Math.abs(dd1-dd2));
        }
        System.out.println(diff);
        Map<Float, List<Float>> map = new HashMap<>();
        for(Float f : diff){
            Float ff = Math.round(f*1000)/1000.0f;
            map.computeIfAbsent(ff, v -> new ArrayList<>()).add(f);
        }
        System.out.println(map);
        int maxSize = 0;
        for(Float ff : map.keySet()){
            if(map.get(ff).size()>maxSize) maxSize=map.get(ff).size();
        }
        for(Float ff : map.keySet()){
            if(map.get(ff).size()==maxSize){
                System.out.println(maxSize);
                System.out.println(map.get(ff));

            }
        }


    }

}
