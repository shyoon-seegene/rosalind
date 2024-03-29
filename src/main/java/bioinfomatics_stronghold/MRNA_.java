package bioinfomatics_stronghold;

public class MRNA_ {

    public static void main(String[] args) {

        String p = "MQWSNRCKNPFHSRAWTKMSGVANRAIPQFIVDEKILFKFWTTLAVTARPYGGGQYYMWRSMYVFAMKPEINFQLFQQVAKHVICAWVTKFADGIHNNGGRMVFCNGSKNWSVFTQHAQPGYADRTLIAKWINIKPSQFKILYMEQWWWSFTEGCIHGFVHDDHFTCGSYGPAYGFAFCLGLWAGRHMPMTSLWLYVRDEKNRDVTNVHDSWKFQPSNPWLDMGCHRTKHVSWHMLKIGANSTRMCVVHSRNSWRAVLHDFLWFPIKYMYYMQWSYIKNQSAEMYKLMAWMYTQWTDQTCNTLVFPEGIGCHHNRRHTIGTTTFWRYTNSKAHWVERAVNILNRMDTLIRITGINPGDRFDVLRNVRMTYIIICHTTKNCIRAIRVWSCHRYMYGLHAHSEHGVVTRRSHYSEGYPPGWRQMNVTFWQDPMSAIAISLNPRSELMGGSKAMYGECYCVQSLFSHWNRNEDANCSGVILQSLNLRCGKGMDDAMTAKMGSRDSWYGDFMDHAMDAACEINVEINHNWWWTNKQKCNEHLLAFMPHQWARHWIDNVIEATDTIFEDYCFLSGNMVTYECQKCWWARRWVGGMPCFPETTAIMDHCWMTGHQIDWCVQVLRWIANKSNKNAELGHEDRFMDFFYIPCKDEDQPIECIMASNLPFVDTQRGGYRARCFNCMEVFVYPLFNFWSCIDQHCVQKKGFFSQIMDFHHVGNPYNLTLANVSQEMDWWMQKWWYNDQANWQLHRFSDWWSGYHHHHAKIQNEIDHIVDETVGLVDYLSYIMAQISQWPHSCSAMHLMMRYYDHTNNERFRSNDDDIHWRRTPANCHDIKKQNLLAWSIMDFYIDEHSKAAMFHSGYDAVPHATADHSNLQCYKNVEKMAFNEKCYVNVDRFTEKRPTQIGIIGVIDPNTTVERWMHPASDMGMLWPANQLAQSNDAQNPYNFENELHVNIRFIMSGYNTKISHPHRIDTLTSQIMCMWAAPFCAFIEDDAFDQLYKAF";

        long v = 1;
        for(char c : p.toCharArray()){
            long a = getNuclCnt(c);
            if(a==0) System.out.println(c);
            v *= getNuclCnt(c);
            if(v>1000000) v = v%1000000;
            System.out.println(v);
        }
        v *= 3;
        System.out.println(v%1000000);


    }


    private static long getNuclCnt(char aa){

        switch (aa){
            case 'A' :
            case 'G' :
            case 'P' :
            case 'T' :
            case 'V' :
                return 4;
            case 'C' :
            case 'D' :
            case 'E' :
            case 'F' :
            case 'H' :
            case 'K' :
            case 'N' :
            case 'Q' :
            case 'Y' :
                return 2;
            case 'I' : return 3;
            case 'L' :
            case 'R' :
            case 'S' :
                return 6;
            case 'M' :
            case 'W' :
                return 1;
            default : return 0;
        }

    }

}
