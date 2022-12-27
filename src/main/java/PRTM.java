import java.util.HashMap;
import java.util.Map;

public class PRTM {

    public static void main(String[] args) {

        /*
        A   71.03711
        C   103.00919
        D   115.02694
        E   129.04259
        F   147.06841
        G   57.02146
        H   137.05891
        I   113.08406
        K   128.09496
        L   113.08406
        M   131.04049
        N   114.04293
        P   97.05276
        Q   128.05858
        R   156.10111
        S   87.03203
        T   101.04768
        V   99.06841
        W   186.07931
        Y   163.06333
         */

        Map<Character, Long> map = new HashMap<>();
        map.put('A', 7103711L);
        map.put('C', 10300919L);
        map.put('D', 11502694L);
        map.put('E', 12904259L);
        map.put('F', 14706841L);
        map.put('G', 5702146L);
        map.put('H', 13705891L);
        map.put('I', 11308406L);
        map.put('K', 12809496L);
        map.put('L', 11308406L);
        map.put('M', 13104049L);
        map.put('N', 11404293L);
        map.put('P', 9705276L);
        map.put('Q', 12805858L);
        map.put('R', 15610111L);
        map.put('S', 8703203L);
        map.put('T', 10104768L);
        map.put('V', 9906841L);
        map.put('W', 18607931L);
        map.put('Y', 16306333L);

        long sum = 0;
        String a = "LRYVGHVDHWVIDVQSCLMTVWMHAIHTDYWDRFSRHKSFPMCAEQWLLEQIGDGCWYNCKCFTKQDHCEHAGLRWWLPLVLYKCVDVWITRVQLQKGDHTINKTLPPKPFTSKGNFLGIYRQATPPYYWFGGCGTRPPRFFNRMCRGMSAIMFPGQWPGQQEMWLDTQWCSKLNSNAVSAICEMFDRVRKWNDMNFISNYLIHFQCRICSWCMDGDCTWGTEHKLKAQCHPITCPNRIMRMKFTHRGIQLSCPMKEAQHIQDSIQCMGPVSSVTWEGREGVFVYRMYHQFILFWSSRLFQITEYMPICSAVQMIGFPRSSANKCTINDESMWPKGLFAGCKFLDSNLLNANSVLNQPGKVGEDFTFNKAANDHPMRDYSEDCIFRNLEWPMQMWDRMYCVHWCHPAYNGPMPQHVRQPVSGGQMKLYHKWREPIWQGWCFGVCAQLYCAAYPDPYRGLTNLGWYAAEIWRDSYYWIKRNHHLRTIHIPFSTQRDFQFMLQCRRWMAHLGYTHTQTHNGVIDELHILTVMIKNMRDSYKLRQASICTWTAPTRLTICLSWDTHLFGKVKNYCAMISAWIVVMCEGPCSEQKFEYSPQYLHERLKYWNVQWLRSHVGREPFQLDLDGAVAQACVDVKKGTFRAWPDICNGKTDTDQFQNDRERQNESNGWEFFMGCMEGWYMIAVNLNCDIPSLYMNGKSEKLMKFLGVDVMNLTTHRIWMLLFATGEMHIAMCHSAACEITIIELYNGISNSICGNNWHAQDWYKEWRLWNEIRVNKVSCRIGLGCMWRMKQMYGQDSQSPPHWCCEYKRFVQGWTPSPMEYKKVKQDCGWPQCRMIYMICSYSTDYRFGMKLVDVGHTYHCRVRVRRRQEWNMSSIPQLWPKMDYMAFEVRAQTISDRAMFPDNTITGPSIQCNVPQIFYYEFQEHINIMQNPFPATQGNTKFGFQVEWAN";
        for(char c : a.toCharArray()){
            sum += map.get(c);
        }
        System.out.println(String.format("%.3f", sum/100000d));

    }
}
