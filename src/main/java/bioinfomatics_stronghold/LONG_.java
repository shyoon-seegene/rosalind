package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;

public class LONG_ {

    private static int cnt = 0;

    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\stronghold\\rosalind_long.txt");

        String assemble = makeAssemble(seqList, null);

        System.out.println(assemble);

    }

    static String makeAssemble (SeqDomainList seqList, String assemble){

        System.out.println(cnt++ + "\t" + seqList.getListSize());

        SeqDomainList notAdded = new SeqDomainList();

        for(SeqDomain sd : seqList.getList()){

            if(assemble==null){
                assemble = sd.getSequence();
                continue;
            }

            if(assemble.contains(sd.getSequence())) continue;

            String seq = sd.getSequence();
            boolean added = false;
            for(int i=1; i<seq.length()/2; i++){
                String prefix = seq.substring(0, seq.length()-i);
                String suffix = seq.substring(i);
                if(assemble.endsWith(prefix)){
                    assemble += seq.substring((seq.length()-i));
                    added = true;
                    break;
                }
                if(assemble.startsWith(suffix)){
                    assemble = seq.substring(0, i) + assemble;
                    added = true;
                    break;
                }
            }
            if(!added){
                notAdded.add(sd);
            }
        }
        if(notAdded.getListSize()==0){
            return assemble;
        }else{
            return makeAssemble(notAdded, assemble);
        }
    }

    static void testCode1(){
        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\stronghold\\rosalind_long.txt");

//        SeqDomainList seqList = new SeqDomainList();
//        seqList.add("1", "ATTAGACCTG");
//        seqList.add("2", "CCTGCCGGAA");
//        seqList.add("3", "AGACCTGCCG");
//        seqList.add("4", "GCCGGAATAC");

        String assemble = null;
        SeqDomainList notAdded = new SeqDomainList();
        System.out.println("1");
        for(SeqDomain sd : seqList.getList()){

            if(assemble==null){
                assemble = sd.getSequence();
                continue;
            }

            if(assemble.contains(sd.getSequence())) continue;

            String seq = sd.getSequence();
            boolean added = false;
            for(int i=1; i<seq.length()/2; i++){
                String prefix = seq.substring(0, seq.length()-i);
                String suffix = seq.substring(i);
                if(assemble.endsWith(prefix)){
                    assemble += seq.substring((seq.length()-i));
                    added = true;
                    break;
                }
                if(assemble.startsWith(suffix)){
                    assemble = seq.substring(0, i) + assemble;
                    added = true;
                    break;
                }
            }
            if(!added){
//                System.out.println(sd.getTitle());
                notAdded.add(sd);
            }
        }

        System.out.println("2");
        SeqDomainList notAdded2 = new SeqDomainList();
        for(SeqDomain sd : notAdded.getList()){
            if(assemble.contains(sd.getSequence())) continue;

            String seq = sd.getSequence();
            boolean added = false;
            for(int i=1; i<seq.length()/2; i++){
                String prefix = seq.substring(0, seq.length()-i);
                String suffix = seq.substring(i);

                if(assemble.endsWith(prefix)){
                    assemble += seq.substring((seq.length()-i));
                    added = true;
                    break;
                }
                if(assemble.startsWith(suffix)){
                    assemble = seq.substring(0, i) + assemble;
                    added = true;
                    break;
                }
            }
            if(!added){
//                System.out.println(sd.getTitle());
                notAdded2.add(sd);
            }
        }

        System.out.println("3");
        SeqDomainList notAdded3 = new SeqDomainList();
        for(SeqDomain sd : notAdded2.getList()){
            if(assemble.contains(sd.getSequence())) continue;

            String seq = sd.getSequence();
            boolean added = false;
            for(int i=1; i<seq.length()/2; i++){
                String prefix = seq.substring(0, seq.length()-i);
                String suffix = seq.substring(i);

                if(assemble.endsWith(prefix)){
                    assemble += seq.substring((seq.length()-i));
                    added = true;
                    break;
                }
                if(assemble.startsWith(suffix)){
                    assemble = seq.substring(0, i) + assemble;
                    added = true;
                    break;
                }
            }
            if(!added){
//                System.out.println(sd.getTitle());
                notAdded3.add(sd);
            }
        }

        System.out.println("4");
        SeqDomainList notAdded4 = new SeqDomainList();
        for(SeqDomain sd : notAdded3.getList()){
            if(assemble.contains(sd.getSequence())) continue;

            String seq = sd.getSequence();
            boolean added = false;
            for(int i=1; i<seq.length()/2; i++){
                String prefix = seq.substring(0, seq.length()-i);
                String suffix = seq.substring(i);

                if(assemble.endsWith(prefix)){
                    assemble += seq.substring((seq.length()-i));
                    added = true;
                    break;
                }
                if(assemble.startsWith(suffix)){
                    assemble = seq.substring(0, i) + assemble;
                    added = true;
                    break;
                }
            }
            if(!added){
//                System.out.println(sd.getTitle());
                notAdded4.add(sd);
            }
        }
        System.out.println("5");
        SeqDomainList notAdded5 = new SeqDomainList();
        for(SeqDomain sd : notAdded4.getList()){
            if(assemble.contains(sd.getSequence())) continue;

            String seq = sd.getSequence();
            boolean added = false;
            for(int i=1; i<seq.length()/2; i++){
                String prefix = seq.substring(0, seq.length()-i);
                String suffix = seq.substring(i);

                if(assemble.endsWith(prefix)){
                    assemble += seq.substring((seq.length()-i));
                    added = true;
                    break;
                }
                if(assemble.startsWith(suffix)){
                    assemble = seq.substring(0, i) + assemble;
                    added = true;
                    break;
                }
            }
            if(!added){
//                System.out.println(sd.getTitle());
                notAdded5.add(sd);
            }
        }
        System.out.println("6");
        SeqDomainList notAdded6 = new SeqDomainList();
        for(SeqDomain sd : notAdded5.getList()){
            if(assemble.contains(sd.getSequence())) continue;

            String seq = sd.getSequence();
            boolean added = false;
            for(int i=1; i<seq.length()/2; i++){
                String prefix = seq.substring(0, seq.length()-i);
                String suffix = seq.substring(i);

                if(assemble.endsWith(prefix)){
                    assemble += seq.substring((seq.length()-i));
                    added = true;
                    break;
                }
                if(assemble.startsWith(suffix)){
                    assemble = seq.substring(0, i) + assemble;
                    added = true;
                    break;
                }
            }
            if(!added){
//                System.out.println(sd.getTitle());
                notAdded6.add(sd);
            }
        }
        System.out.println("7");

        SeqDomainList notAdded7 = new SeqDomainList();
        for(SeqDomain sd : notAdded6.getList()){
            if(assemble.contains(sd.getSequence())) continue;

            String seq = sd.getSequence();
            boolean added = false;
            for(int i=1; i<seq.length()/2; i++){
                String prefix = seq.substring(0, seq.length()-i);
                String suffix = seq.substring(i);

                if(assemble.endsWith(prefix)){
                    assemble += seq.substring((seq.length()-i));
                    added = true;
                    break;
                }
                if(assemble.startsWith(suffix)){
                    assemble = seq.substring(0, i) + assemble;
                    added = true;
                    break;
                }
            }
            if(!added){
                System.out.println(sd.getTitle());
                notAdded7.add(sd);
            }
        }

        System.out.println(assemble);

    }


}
