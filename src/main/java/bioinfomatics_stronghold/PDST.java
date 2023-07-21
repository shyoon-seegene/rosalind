package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.SeqDomain;
import com.seegene.insilico.platform.core.bioutil.domain.SeqDomainList;
import com.seegene.insilico.platform.core.bioutil.io.FastSeqReader;
import com.seegene.insilico.platform.core.bioutil.io.Seqtools;

public class PDST {

    public static void main(String[] args) {

        SeqDomainList seqList = FastSeqReader.importFileToSeqDomainList("C:\\dev\\rosalind\\stronghold\\rosalind_pdst.txt");


        double[][] mat = new double[seqList.getListSize()][seqList.getListSize()];

        for(int i=0; i< seqList.getListSize(); i++) for(int j=0; j< seqList.getListSize(); j++){
            mat[i][j] = Seqtools.calDiff(seqList.get(i).getSequence(), seqList.get(j).getSequence()) / (double) seqList.get(i).getSequence().length();
        }

        for(double[] arr : mat){
            for(double v : arr){
                System.out.print(" " + v);
            }
            System.out.print("\n");
        }



    }
}
