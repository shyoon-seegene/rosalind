package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.TaxNodeDomain;
import com.seegene.insilico.platform.core.bioutil.domain.TaxTreeDomain;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CTBL {

    static TaxTreeDomain tree = new TaxTreeDomain();
    static int pos = 0;
    static int nodeIdx = 0;
    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_ctbl.txt"));


        String tax = lines.get(0).substring(1).replace(");", "");

        System.out.println(tax);


    }

    static void readnwk(String newick, TaxNodeDomain parent){

        boolean isOpen = false;
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<newick.length(); i++){

            if(newick.charAt(i) == '('){
                isOpen = true;
                if(sb.length()>0){
                    String[] names = sb.toString().split(",");
                    for(String name : names){
                        TaxNodeDomain node = new TaxNodeDomain(++nodeIdx, name, parent.getRank()+1, parent.getTax_id());
                        tree.addNode(node);
                    }

                }
                continue;
//                while(newick.charAt(i)=='(' || newick.charAt(i)==','){
//                    i++;
//                    readnwk(newick.substring(0,i), node);
//                }
            }
            if(newick.charAt(i) == ','){
                if(isOpen){

                }else{

                }
                continue;
            }
            if(newick.charAt(i) == ')'){
                if(isOpen){
                    readnwk(newick.substring(0,i), null);
                }else{

                }
                continue;
            }
            if(!isOpen){
                sb.append(newick.charAt(i));
            }

        }



    }
}
