import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.util.*;

public class CAT {


    public static Map<Integer, Integer> catalanNumMap = new HashMap<>();

    public static void main(String[] args) {



        String testseq1 = "AAAUUUAUAUGCGCGUAUAC";
//        String testseq2 = "UAUA UGCGCGAUAC";

        String testseq2 = "UAUAGUUAAUACAGCGCGGCCUU";
        String testseq3 = "AAAUACGUAUGUACUAUUAUGGAUCCGCUAUAGCUCAUAUGGCAUAUAUAGCGCCUGCUAUAAGCACGUGGAUGCAUAUAUCGGCCAUCCGUCGCCGGGCAGCCGCGGCGUAAUUUAAUAAAUUACCGUAGCUUGCAAAUGUAGCGCUAGCGCGCGCGCGAUCCGAUCUCUCGCGACGGGCACGGAAUUGAUCGCUAGAUCCGAUUGCAGAGGCCUCGGCGGCCAUGCCUAUAGCUAAGCGUACUAUUAACGCGUUUAAUGAUCGUACGUAC";

        List<Integer> catalanNodesList = new ArrayList<>();

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\rosalind_cat.txt");

        int a = getNum(testseq3);
//        int a = getNum("UA");
        System.out.println(a);
        System.out.println(catalanNum(1));
        System.out.println(catalanNum(2));
        System.out.println(catalanNum(3));
        System.out.println(catalanNum(4));
//        System.out.println(catalanNum(20));


    }

    private static int getNum(String seq) {
        char prevStartChar = '-';
        Integer[] nAUCG = new Integer[]{0,0,0,0};
        StringBuilder sb = new StringBuilder();
        int res = 1;
        int nCatalan = 0;
        for(int i = 0; i<seq.length(); i++){
            char c = seq.charAt(i);
            if(i==0) prevStartChar = c;
            if(sb.length() == 0) {
                System.out.println("start with " + seq);
                sb.append(c);
                nAUCG = calN(nAUCG, c);
                continue;
            }
            if(pair(sb.charAt(0)) == c){
                sb.append(c);
//                System.out.println(sb);
                nAUCG = calN(nAUCG, c);
//                System.out.println(new ArrayList<>(Arrays.asList(nAUCG)));
                if(nAUCG[0]==nAUCG[1] && nAUCG[2]==nAUCG[3]){
                    if(prevStartChar == sb.charAt(0)){
                        nCatalan++;
                    }else{
                        prevStartChar = sb.charAt(0);
                        res *= catalanNum(nCatalan);
                        res = res%1000000;
                    }
                    String sub = sb.toString();
                    System.out.println(prevStartChar + "\t" + sub + "\t" + nCatalan);
                    if(sub.length()>2 && sub.length()<seq.length()){
                        res *= getNum(sub.substring(1, sub.length()-1));
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
        if(nCatalan>0) res *= nCatalan;
        System.out.println("r:"+res);
        return res%1000000;
    }

    public static int catalanNum(int n){
        if(catalanNumMap.containsKey(n)){
            return catalanNumMap.get(n);
        }
        if(n==0) return 1;
        int res = 0;
        for(int i=1; i<=n; i++){
            res += catalanNum(i-1)*catalanNum(n-i);
        }
//        System.out.println(res);
        res = res%1000000;
        catalanNumMap.put(n, res);
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
