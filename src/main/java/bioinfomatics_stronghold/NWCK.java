package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.TaxNodeDomain;
import com.seegene.insilico.platform.core.bioutil.domain.TaxTreeDomain;
import org.apache.commons.io.IOUtils;
import org.forester.archaeopteryx.Archaeopteryx;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyMethods;
import org.forester.phylogeny.PhylogenyNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NWCK {

    public static void main(String[] args) throws IOException {
        String newick = "(A:0.1,B:0.2,(C:0.3,D:0.4):0.5)";
        String newick2 = "(A,B,(C,D));";
        String newick3 = "(dog,cat);";

        Phylogeny p = Phylogeny.createInstanceFromNhxString(newick3.replace(",", ":1,").replaceAll("\\)", ":1)").replace(";",":1;"));

        PhylogenyNode node1 = p.getNode("dog");
        PhylogenyNode node2 = p.getNode("cat");
        System.out.println(PhylogenyMethods.calculateDistance(node1, node2));

        BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\rosalind\\stronghold\\rosalind_nwck.txt"));
        String line = null;
        while((line=br.readLine())!=null){
            if(line.trim().length()==0) continue;
            String tree = line;
            String node = br.readLine();
            Phylogeny ph = Phylogeny.createInstanceFromNhxString(tree.replace(",", ":1,").replaceAll("\\)", ":1)").replace(";",":1;"));
            PhylogenyNode n1 = ph.getNode(node.split("\\s+")[0]);
            PhylogenyNode n2 = ph.getNode(node.split("\\s+")[1]);

            System.out.print( " " + (int) PhylogenyMethods.calculateDistance(n1, n2));
        }


    }

    public static void parseNewick(String newick, int rank) {

//        // Remove outer parentheses and trailing semicolon
//        newick = newick.replaceAll("^\\(", "").replaceAll("\\);$", "");
//
//        if(rank==0)
//
//        TaxNodeDomain node = new TaxNodeDomain();
//        node.setRank(rank);
//
//
//        int level = 0;
//        int lastSplit = -1;
//        for (int i = 0; i < newick.length(); i++) {
//            char c = newick.charAt(i);
//            if (c == '(') level++;
//            if (c == ')') level--;
//            if (level == 0 && c == ',') {
//                node.children.add(parseNewick(newick.substring(lastSplit+1, i)));
//                lastSplit = i;
//            }
//        }
//
//        String[] parts = newick.substring(lastSplit+1).split(":");
//        node.name = parts[0];
//        if (parts.length > 1) {
//            node.value = Double.parseDouble(parts[1].split(",")[0]);
//        }
//
//        return node;
    }

    public static void printTree(Node node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.name + ":" + node.value);
        for (Node child : node.children) {
            printTree(child, depth+1);
        }
    }

    public static class Node {
        public String name;
        public double value;
        public LinkedList<Node> children;
    }
}
