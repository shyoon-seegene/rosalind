package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.util.*;

public class CAT {


    public static Map<Long, Long> catalanNumMap = new HashMap<>();


    public static void main(String[] args) {



        String testseq1 = "AAAUUUAUAUGCGCGUAUAC";
//        String testseq2 = "UAUA UGCGCGAUAC";
        String newTSeq = "AGCGCGCAUAUAUU";
//        String newTSeq2 = "AAAUUUAUAU";
        String newTSeq2 = "AAAUUUAUAUGCGCGUAUACAU";
        // UAGCGUGAUCAC
        String testseq2 = "UAUAGUUAAUACAGCGCGGCCUU";
        String testseq3 = "AAAUACGUAUGUACUAUUAUGGAUCCGCUAUAGCUCAUAUGGCAUAUAUAGCGCCUGCUAUAAGCACGUGGAUGCAUAUAUCGGCCAUCCGUCGCCGGGCAGCCGCGGCGUAAUUUAAUAAAUUACCGUAGCUUGCAAAUGUAGCGCUAGCGCGCGCGCGAUCCGAUCUCUCGCGACGGGCACGGAAUUGAUCGCUAGAUCCGAUUGCAGAGGCCUCGGCGGCCAUGCCUAUAGCUAAGCGUACUAUUAACGCGUUUAAUGAUCGUACGUAC";
        String testseq4 = "UAUGAGCGCGCAUAUAUUGCCUACAUGAUGAGUCUAGUACGACCGCGUAGCCGUAUUACAUCACGAUCUAGUAUAUUAUGCUAAUAUAGCGUCGCUAGUAAAGGGCCCUUAAUAUCGAUAAUAUUAAUCGUGCAUACGGCCGGCGCGCACCGCCGCCGGCGGCGGUAAUCGGCCGUCGAGUUAGCGAUCAUACGAUAUCGUACUUCAUGAUACUAGUACGUACACCGGCAUGCUAGUAUAGCUACAUAUGUGCACCGCCGGGGCUCCGCGCGGGAAGCUUAUUA";
        String testseq5 = "UCGUACGACCAUGGGUUACGACCUAGACGAUCGUAUAUCGCCUAGCACGCCGGUACGUAGCUAGUAAUCUUAAUACAUGAUUGAUCGGCUCGAACGUGCGUCAAUAUUGUACGAAUACGUCCAUGAUAAUUUAUAGUAUGCGAUAUCGAGCAUUCGACGCGGGCCUUACGUACUCGAGUUGCGCAACGCCGGCUGUGCUGCUUAUAAAGCUAACCAAGCUUGCGCUAGAAUUAAGCAUUGUCGGCCCGGGCACAAU";

        List<Integer> catalanNodesList = new ArrayList<>();

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\rosalind_cat_4_dataset.txt");

//        long a = getNum(testseq1, 0);
//        long a = getNum(seqList.get(0).getSequence());
//        int a = getNum("UA");
//        System.out.println("a:"+a);
//        long b = getNum(newTSeq, 0);
//        System.out.println("b:"+b);
        System.out.println("c:"+getNum(newTSeq2, 0));
        System.out.println(catalanNum(1));
        System.out.println(catalanNum(2));
        System.out.println(catalanNum(3));
        System.out.println(catalanNum(4));
        System.out.println(catalanNum(5));
//        System.out.println(catalanNum(20));

        System.out.println(gg(testseq5));

    }

    private static Map<String, Integer> map = new HashMap<>();

    private static int gg (String seq){

        Integer prev = map.get(seq);
        if(prev!=null){
            System.out.println(seq + ":" + prev);
            return prev;
        }
        Integer[] nAUCG = new Integer[]{0,0,0,0};
        StringBuilder sb = new StringBuilder();

        char fEnd = seq.charAt(0);
        char pair = pair(fEnd);
        int sum = 0;
        nAUCG = calN(nAUCG, fEnd);
        for(int i=1; i<seq.length(); i++){
            char c = seq.charAt(i);
            sb.append(c);
            nAUCG = calN(nAUCG, c);
            if(c==pair && nAUCG[0]==nAUCG[1] && nAUCG[2]==nAUCG[3]){
                String left = seq.substring(1,i);
                String right = seq.substring(i+1);
                int nLeft = left.length()==0 ? 1 : gg(left);
                int nRight = right.length()==0 ? 1 : gg(right);
                long res = ((long) nLeft * (long) nRight)%1000000L;
                if(res == 0) continue;
                else {
                    sum += res;
                    sum = sum%1000000;
                }

            }
            if(seq.length()==272) System.out.println(i + " / " + seq.length());
        }
        if(sum==0) return 0;
        int value = sum%1000000;
        map.put(seq, value);
        return value;

    }

    private static long getNum(String seq, int depth) {
        char prevStartChar = '-';
        Integer[] nAUCG = new Integer[]{0,0,0,0};
        StringBuilder sb = new StringBuilder();
        long res = 1;
        long nCatalan = 0;
        for(int i = 0; i<seq.length(); i++){
            char c = seq.charAt(i);
            if(i==0) prevStartChar = c;
            if(sb.length() == 0) {
//                System.out.println("start with " + seq);
                sb.append(c);
                nAUCG = calN(nAUCG, c);
                continue;
            }
            if(pair(sb.charAt(0)) == c){
                sb.append(c);
                nAUCG = calN(nAUCG, c);

                if(nAUCG[0]==nAUCG[1] && nAUCG[2]==nAUCG[3]){
                    System.out.println(new ArrayList<>(Arrays.asList(nAUCG)));
                    System.out.println("seq:" + depth + ":" + sb.toString());
                    if(prevStartChar == sb.charAt(0)){
                        nCatalan++;
                        System.out.println("m01:"+res+":"+i+":"+nCatalan+":"+prevStartChar);
                    }else{
                        prevStartChar = sb.charAt(0);
                        System.out.println("m02:"+res+":"+i+":"+nCatalan);
                        res = ((res%1000000) * (catalanNum(nCatalan)%1000000))%1000000;
                    }
                    String sub = sb.toString();
//                    System.out.println(prevStartChar + "\t" + sub + "\t" + nCatalan);
                    if(sub.length()>2 && sub.length()<=seq.length()){
                        System.out.println("m03:"+res+":"+i+":"+nCatalan);
                        res = (res%1000000) * getNum(sub.substring(1, sub.length()-1), depth+1);
                        res = res%1000000;
//                        System.out.println("m3:"+res);
                    }
//                    res *= getNum(sub);

                    //todo sub
                    sb = new StringBuilder();
                    nAUCG = new Integer[]{0,0,0,0};
                }
                continue;
            }else{
                nAUCG = calN(nAUCG, c);
                sb.append(c);
            }
        }
        if(nCatalan>0){
            res *= nCatalan;
        }
        return res%1000000;
    }

    public static long catalanNum(long n){
        if(catalanNumMap.containsKey(n)){
            return catalanNumMap.get(n);
        }
        if(n==0) return 1;
        long res = 0;
        for(int i=1; i<=n; i++){
            res += catalanNum(i-1)*catalanNum(n-i);
            res = res%1000000;
        }
//        System.out.println(res);
        res = res%1000000;
        catalanNumMap.put(n, res);
        if(res==0) System.out.println("cc:"+n+"\t"+res);
        return res;
    }

    public static char pair(char c){
        switch (c){
            case 'A': return 'U';
            case 'U': return 'A';
            case 'G': return 'C';
            case 'C': return 'G';
            default: return '-';
        }
    }

    public static Integer[] calN(Integer[] nAUCG, char c){
        switch (c){
            case 'A': {
                nAUCG[0]++;
                break;
            }
            case 'U': {
                nAUCG[1]++;
                break;
            }
            case 'C': {
                nAUCG[2]++;
                break;
            }
            case 'G': {
                nAUCG[3]++;
                break;
            }
        }
        return nAUCG;
    }


}
