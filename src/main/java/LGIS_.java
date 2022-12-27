import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LGIS_ {

    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_lgis.txt"));

        String[] nums = lines.get(1).split("\\s+");
        Integer[] numArr = Arrays.stream(nums).map(Integer::valueOf).toArray(Integer[]::new);

//        Integer[] numArr = {5,1,4,2,3};

        List<List<Integer>> isub = new ArrayList<>();
        List<List<Integer>> dsub = new ArrayList<>();

        int imaxlen = 0;
        String imaxFirst = null;
        int dmaxlen = 0;
        String dmaxFirst = null;

        int cnt = 1;
        for(int num : numArr){
            if(isub.isEmpty()){
                isub.add(new ArrayList<>(Arrays.asList(num)));
                dsub.add(new ArrayList<>(Arrays.asList(num)));
                continue;
            }
            int isubSize = isub.size();
            boolean on = true;
            for(int i=0; i<isubSize; i++){
                int last = isub.get(i).get(isub.get(i).size()-1);
                if(num > last){
                    List<Integer> ii = new ArrayList<>(isub.get(i));
                    ii.add(num);
//                    isub.get(i).add(num);
                    isub.add(ii);
                } else if (on) {
                    on = false;
                    isub.add(new ArrayList<>(Arrays.asList(num)));
                }
            }
            Map<Integer, List<String>> imap = new TreeMap<>();
            for(List<Integer> i : isub){
                StringBuilder sb = new StringBuilder();
                for(int ii : i){
                    sb.append(" " + ii);
                }
                imap.computeIfAbsent(i.size(), v -> new ArrayList<>()).add(sb.substring(1));
            }
            List<List<Integer>> newisub = new ArrayList<>();
            for(int len : imap.keySet()){
                List<String> list = imap.get(len);
                Collections.sort(list, (o1, o2) -> {
                    String[] s1 = o1.split("\\s+");
                    String[] s2 = o2.split("\\s+");
                    return Integer.valueOf(s1[s1.length-1]) - Integer.valueOf((s2[s2.length-1]));
                });
                String[] ll = list.get(0).split("\\s+");
                if(ll.length > imaxlen){
                    imaxlen = ll.length;
                    imaxFirst = list.get(0);
                }
                List<Integer> nv = new ArrayList<>();
                for(String l : ll){
                    nv.add(Integer.valueOf(l));
                }
                newisub.add(nv);
            }
            isub = newisub;

            on = true;
            int dsubSize = dsub.size();
            for(int i=0; i<dsubSize; i++){
                int last = dsub.get(i).get(dsub.get(i).size()-1);
                if(num < last){
                    List<Integer> dd = new ArrayList<>(dsub.get(i));
                    dd.add(num);
//                    dsub.get(i).add(num);
                    dsub.add(dd);
                } else if (on) {
                    on = false;
                    dsub.add(new ArrayList<>(Arrays.asList(num)));
                }
            }
            Map<Integer, List<String>> dmap = new TreeMap<>();
            for(List<Integer> d : dsub){
                StringBuilder sb = new StringBuilder();
                for(int dd : d){
                    sb.append(" " + dd);
                }
                dmap.computeIfAbsent(d.size(), v -> new ArrayList<>()).add(sb.substring(1));
            }
            List<List<Integer>> newdsub = new ArrayList<>();
            for(int len : dmap.keySet()){
                List<String> list = dmap.get(len);
                Collections.sort(list, (o1, o2) -> {
                    String[] s1 = o1.split("\\s+");
                    String[] s2 = o2.split("\\s+");
                    return Integer.valueOf(s2[s2.length-1]) - Integer.valueOf((s1[s1.length-1]));
                });
                String[] ll = list.get(0).split("\\s+");
                if(ll.length > dmaxlen) {
                    dmaxlen = ll.length;
                    dmaxFirst = list.get(0);
                }
                List<Integer> nv = new ArrayList<>();
                for(String l : ll){
                    nv.add(Integer.valueOf(l));
                }
                newdsub.add(nv);
            }
            dsub = newdsub;

//            System.out.println(cnt++ + "\t" + num);
//            System.out.println(isub);
//            System.out.println(dsub);
        }

        System.out.println("-----");
        System.out.println(imaxFirst);
        System.out.println(dmaxFirst);
        System.out.println("-----");

        int imax = 0;
        List<Integer> imaxList = new ArrayList<>();
        for(List<Integer> i : isub){
            if(i.size() > imax) {
                imax = i.size();
                imaxList=i;
            }
        }

        int dmax = 0;
        List<Integer> dmaxList = new ArrayList<>();
        for(List<Integer> d : dsub){
            if(d.size() > dmax) {
                dmax = d.size();
                dmaxList=d;
            }
        }

        for(int i=0; i<imaxList.size(); i++){
            System.out.print(i==0 ? imaxList.get(i) : " " + imaxList.get(i));
        }
        System.out.print("\n");
        for(int i=0; i<dmaxList.size(); i++){
            System.out.print(i==0 ? dmaxList.get(i) : " " + dmaxList.get(i));
        }

    }

}
