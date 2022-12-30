import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TREE {

    public static void main(String[] args) throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_tree.txt"));
        int n = Integer.valueOf(lines.get(0));

        Map<Integer, Integer> memberIndexMap = new HashMap<>();
        Map<Integer, Set<Integer>> groupMap = new HashMap<>();

        int groupNum = 0;

        for(int i=1; i<lines.size(); i++){
            String[] s = lines.get(i).split("\\s+");
            int n1 = Integer.valueOf(s[0]);
            int n2 = Integer.valueOf(s[1]);

            if(memberIndexMap.get(n1)==null && memberIndexMap.get(n2)==null){
                memberIndexMap.put(n1, groupNum);
                memberIndexMap.put(n2, groupNum);
                Set<Integer> set = new TreeSet<>();
                set.add(n1);
                set.add(n2);
                groupMap.put(groupNum, set);
                groupNum++;
            }else if(memberIndexMap.get(n1)!=null && memberIndexMap.get(n2)==null){
                int prevGroupNum = memberIndexMap.get(n1);
                memberIndexMap.put(n2, prevGroupNum);
                Set<Integer> set = groupMap.get(prevGroupNum);
                set.add(n2);
                groupMap.put(prevGroupNum, set);
            }else if(memberIndexMap.get(n1)==null && memberIndexMap.get(n2)!=null){
                int prevGroupNum = memberIndexMap.get(n2);
                memberIndexMap.put(n1, prevGroupNum);
                Set<Integer> set = groupMap.get(prevGroupNum);
                if(set==null){
                    System.out.println(memberIndexMap.get(n1));
                    System.out.println(memberIndexMap.get(n2));
                    System.out.println(lines.get(i));
                    System.out.println(memberIndexMap);
                    System.out.println(groupMap);
                    System.out.println(prevGroupNum);
                }
                set.add(n1);
                groupMap.put(prevGroupNum, set);
            }else if(memberIndexMap.get(n1) < memberIndexMap.get(n2)){
                int groupNum1 = memberIndexMap.get(n1);
                int groupNum2 = memberIndexMap.get(n2);
                Set<Integer> set = groupMap.get(groupNum1);
                for(Integer nn : groupMap.get(groupNum2)) memberIndexMap.put(nn, groupNum1);
                set.addAll(groupMap.get(groupNum2));
                groupMap.put(groupNum1, set);
                groupMap.remove(groupNum2);
            }else if(memberIndexMap.get(n1) > memberIndexMap.get(n2)){
                int groupNum1 = memberIndexMap.get(n1);
                int groupNum2 = memberIndexMap.get(n2);
                Set<Integer> set = groupMap.get(groupNum2);
                for(Integer nn : groupMap.get(groupNum1)) memberIndexMap.put(nn, groupNum2);
                set.addAll(groupMap.get(groupNum1));
                groupMap.put(groupNum2, set);
                groupMap.remove(groupNum1);
            }else continue;

        }

        System.out.println("g:"+groupMap.size());
        System.out.println("m:"+memberIndexMap.size());
        System.out.println(n - memberIndexMap.size() + groupMap.size() -1);


    }

    public static void failedLogic1() throws IOException {

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_tree.txt"));

        int n = Integer.valueOf(lines.get(0));

        //        List<Set<Integer>> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Set<Integer>> member = new HashMap<>();
        Set<Integer> checkSet = new TreeSet<>();
        int groupNum = 0;

        boolean debug = false;
        for(int i=1; i<lines.size(); i++){

            String[] s = lines.get(i).split("\\s+");
            int n1 = Integer.valueOf(s[0]);
            int n2 = Integer.valueOf(s[1]);

            if(n1==270 || n2==270) debug = true;
            else debug = false;

            checkSet.add(n1);
            checkSet.add(n2);
            if(!map.containsKey(n1) && !map.containsKey(n2)){
                if(debug) System.out.println("d0:" + n1 + " " + n2 + " " + groupNum);
                map.put(n1, groupNum);
                map.put(n2, groupNum);
                member.put(groupNum, new HashSet<>(Arrays.asList(n1, n2)));
                groupNum++;
                continue;
            }

            Integer e1 = map.get(n1);
            Integer e2 = map.get(n2);

            if(e1!=null && e2==null){
                if(debug) System.out.println("d1:" + n1 + " " + n2 + " " + e1 + " " + e2);
                map.put(n2, e1);
                member.merge(e1, new HashSet<>(Arrays.asList(n2)), (v1, v2) -> {
                    Set<Integer> set = new HashSet<>(v1);
                    set.addAll(v2);
                    return set;
                });
            }else if(e1==null && e2!=null){
                if(debug) System.out.println("d2:" + n1 + " " + n2 + " " + e1 + " " + e2);
                map.put(n1, e2);
                member.merge(e2, new HashSet<>(Arrays.asList(n1)), (v1, v2) -> {
                    Set<Integer> set = new HashSet<>(v1);
                    set.addAll(v2);
                    return set;
                });
            }else{
                if(e1.equals(e2)){
                    continue;
                }else if(e1<e2){
                    if(debug) System.out.println("d3:" + n1 + " " + n2 + " " + e1 + " " + e2);
                    map.put(n2, e1);
                    member.merge(e1, member.get(e2), (v1, v2) -> {
                        Set<Integer> set = new HashSet<>(v1);
                        set.add(n2);
                        set.addAll(v2);
                        return set;
                    });
                    map.remove(e2);
                }else{
                    if(debug) System.out.println("d4:" + n1 + " " + n2 + " " + e1 + " " + e2);
                    map.put(n1, e2);
                    member.merge(e2, member.get(e1), (v1, v2) -> {
                        Set<Integer> set = new HashSet<>(v1);
                        set.add(n1);
                        set.addAll(v2);
                        return set;
                    });
                    map.remove(e1);
                }
            }
        }
        int cnt=0;
        for(int i=1; i<=n; i++){
            if(!map.containsKey(i)) cnt++;
        }

        System.out.println(map);
        System.out.println(member);
        System.out.println(cnt);
        System.out.println(member.size());
        System.out.println(n-checkSet.size());
//        for(int key : checkSet){
//            if(map.get(key)==null) System.out.println(key);
//        }
        System.out.println(cnt + member.size()-1);

    }

    public static void failedLogic2() throws IOException {
        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/rosalind_tree.txt"));

        int n = Integer.valueOf(lines.get(0));

        List<int[]> arrList1 = new ArrayList<>();
        List<int[]> arrList2 = new ArrayList<>();
        for(int j=1; j<lines.size(); j++){
            String[] s = lines.get(j).split("\\s+");
            int n1 = Integer.valueOf(s[0]);
            int n2 = Integer.valueOf(s[1]);
            int[] arr = new int[2];
            arr[0] = n1;
            arr[1] = n2;
            arrList1.add(arr);
            arrList2.add(arr);
        }
        Collections.sort(arrList1, Comparator.comparingInt(o -> o[0]));
        Collections.sort(arrList2, Comparator.comparingInt(o -> o[1]));

        List<int[]> arrList = new ArrayList<>();
        arrList.addAll(arrList1);
        arrList.addAll(arrList2);
        int groupNum = 0;

        Set<Integer> checked = new HashSet<>();

        List<Set<Integer>> sset = new ArrayList<>();
        int gCnt = 0;
        for(int i=1; i<=n; i++){
            Set<Integer> set = new LinkedHashSet<>();
            if(checked.contains(i)){
                continue;
            }else{
                set.add(i);
//                checked.add(i);
            }
//            for(int j=1; j<lines.size(); j++){
            for(int[] arr : arrList){
//                String[] s = lines.get(j).split("\\s+");
//                int n1 = Integer.valueOf(s[0]);
//                int n2 = Integer.valueOf(s[1]);
                int n1 = arr[0];
                int n2 = arr[1];

                if(checked.contains(n1)) continue;
                if(checked.contains(n2)) continue;
                if(set.contains(n1) || set.contains(n2)){
                    set.add(n1);
//                    checked.add(n1);
                    set.add(n2);
//                    checked.add(n2);
                }
            }
            checked.addAll(set);
            sset.add(set);
        }
        System.out.println(n);
        System.out.println(sset);
        int setSum = 0;
        for(Set<Integer> set : sset){
            setSum+=set.size();
        }
        System.out.println(setSum);
        System.out.println(sset.size());

//        Map<Integer, Integer> checkMap = new HashMap<>();
//        for(int i=0; i<sset.size(); i++){
//            Set<Integer> set = sset.get(i);
//            for(int num : set){
//                if(checkMap.containsKey(num)) System.out.println("Error With " + num);
//                checkMap.put(num, i);
//            }
//        }
//
//        FileWriter fw = new FileWriter(new File("C:/dev/rosalind/rosalind_tree_check.txt"));
//        for(int j=1; j<lines.size(); j++) {
//            String[] s = lines.get(j).split("\\s+");
//            int n1 = Integer.valueOf(s[0]);
//            int n2 = Integer.valueOf(s[1]);
//            if(checkMap.get(n1) != checkMap.get(n2)){
//                System.out.println("Error With " + lines.get(j));
//            }else{
//                fw.write(lines.get(j) + "\t" + checkMap.get(n1) + "\n");
//            }
//        }
//        fw.close();
    }
}
