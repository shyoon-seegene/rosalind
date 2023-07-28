package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.TaxNodeDomain;
import com.seegene.insilico.platform.core.bioutil.domain.TaxTreeDomain;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CTBL {

    private static final Logger log = LoggerFactory.getLogger(CTBL.class);

    static TaxTreeDomain tree = new TaxTreeDomain();
    static int pos = 0;
    static int nodeIdx = 0;
    int branchLabel = 0;
    public static void main(String[] args) throws IOException {


        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_ctbl.txt"));


        String tax = lines.get(0).substring(1).replace(");", "");

        System.out.println(tax);

        CTBL main = new CTBL();
        System.out.println(main.findNodes(StringUtils.removeEnd(lines.get(0).trim(), ";")));
        main.parse(lines.get(0));
        tree.setChildren();
//        System.out.println(tree.getNameMap().keySet());
//        System.out.println(tree.getNodeMap());
//        System.out.println(tree.getChildrenMap());
        Map<String, Integer> checkMap = new TreeMap<>();
        List<Set<Integer>> checkSetList = new ArrayList<>();
        for(Integer nodeIdx : tree.getNodeMap().keySet()){
            TaxNodeDomain node = tree.getNode(nodeIdx);
            if(node.getTaxon_name().equals("root")) continue;
            if(!node.getTaxon_name().startsWith("branch-")) checkMap.put(node.getTaxon_name(), nodeIdx);
            List<Integer> list = tree.getAllChildren(node.getTaxon_name());
            if(list.size()>0){
                Set checkSet = Set.copyOf(list);
                checkSetList.add(checkSet);
            }
        }
        Set<String> resultSet = new TreeSet<>();
        for(Set<Integer> checkSet : checkSetList){
            StringBuilder sb = new StringBuilder();
            for(String key : checkMap.keySet()){
                int id = checkMap.get(key);
                if(checkSet.contains(id)) sb.append("1");
                else sb.append("0");
            }
            resultSet.add(sb.toString());
        }
        for(String res : resultSet){
            System.out.println(res);
        }




//        for(TaxNodeDomain node : tree.getNodeMap().values()){
//            System.out.println(node.getTax_id() + "\t" + node.getParent_tax_id() + "\t" + node.getTaxon_name());
//        }


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
                    sb = new StringBuilder();
                }else{
                    parent = new TaxNodeDomain(++nodeIdx, null, parent.getRank()+1, parent.getTax_id());
                    readnwk(newick, parent);
                }
                while(newick.charAt(i) == ',' || newick.charAt(i) == ')'){
//                    readnwk(sb.toString());
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

    private List<String> findNodes(String nwkStr){

        List<String> nodes = new ArrayList<>();

        int start = 0;
        int depth = 0;
        boolean isClosed = true;
        for(int i=0; i<nwkStr.length(); i++){
            char c = nwkStr.charAt(i);
            if(c == '('){
                depth++;
                isClosed = false;
            }else if (c == ')' && --depth == 0){
                isClosed = true;
            }else if (c == ',' && isClosed){
                nodes.add(nwkStr.substring(start, i).trim());
                start = i+1;
            }
        }
        nodes.add(nwkStr.substring(start).trim());
        return nodes;
    }

    private void parse(final String newickStr) {
        branchLabel = 0;

//        final Tree<PhyloNode, Edge<PhyloNode>> phyloTree = new Tree<>();
        // first remove the trailing ; if any
        String stringToParse = StringUtils.removeEnd(newickStr.trim(), ";").trim();

        if (stringToParse.charAt(0) == '(' && getClosingParenthesis(stringToParse) == stringToParse.length() - 1) {
            stringToParse = stringToParse.substring(1, stringToParse.length() - 1);
        }

        // extract the information for the root node, this will be after the last closing parenthesis iff there is
        // a colon (TODO is this right? e.g. (A,B,(C,D),E)F; )
        double distance = 0.0;
        String rootName = "root";
        final int rparen = stringToParse.lastIndexOf(')');
        final int rcolon = stringToParse.lastIndexOf(':');
        if (rparen < rcolon) {
            distance = Double.parseDouble(stringToParse.substring(rcolon + 1));
            rootName = stringToParse.substring(rparen + 1, rcolon);
            stringToParse = stringToParse.substring(0, rcolon - rootName.length()).trim(); // adjust the string to exclude the distance
        }

        // create a new node for this root and add it to the tree.
//        final PhyloNode root = new PhyloNode(rootName, distance);
        TaxNodeDomain root = new TaxNodeDomain(nodeIdx, rootName, 0, 0);
//        log.trace("Parsing Newick file: Adding root node {}", root);
        tree.addNode(root);

        // if we don't have a comma we are just have the root node
        if (stringToParse.indexOf(',') == -1) {
            return;
        }

        if (stringToParse.charAt(0) == '(' && getClosingParenthesis(stringToParse) == stringToParse.length() - 1) {
            stringToParse = stringToParse.substring(1, stringToParse.length() - 1);
        }

        parseString(stringToParse, root);
    }

    private void parseString(String stringToParse, TaxNodeDomain parent) {
        for (String node : findNodes(stringToParse)) {
            parseNode(node, parent);
        }
    }

    private void parseNode(String node, TaxNodeDomain parent) {
        log.trace("parsing {} from parent node {} ", node, parent.getTax_id());
        if (node.charAt(0) == '(') {
            // this is a branch so create a branch node and set the parent
            final int rparen = node.lastIndexOf(')');
            TaxNodeDomain branchNode = addNodeToTree(node.substring(rparen + 1), parent);
            for (final String nd : findNodes(node.substring(1, rparen))) {
                parseNode(nd, branchNode);
            }
        } else if (node.indexOf(',') != -1) {
            for (final String nd : findNodes(node)) {
                parseNode(nd, parent);
            }
        } else {
            // this is a single node
            addNodeToTree(node, parent);
        }
    }

    private TaxNodeDomain addNodeToTree(String node, TaxNodeDomain parent) {
        log.trace("Adding {} to tree at {}.", node, parent);
        TaxNodeDomain phyloNode;

        int lcolon = node.indexOf(':');
        String nodeName;
        double distance = 0.0;

        if (node.isEmpty()) {
            // no details so use default values.
            nodeName = String.format("branch-%d", branchLabel++);
            distance = 0.0;
        } else if (lcolon == -1) {
            // we just have a node name and no distance.
            distance = 0.0;
            nodeName = node;
        } else {
            // we have a node name and a distance.
            distance = Double.parseDouble(node.substring(lcolon + 1));
            nodeName = node.substring(0, lcolon);
        }

        phyloNode = new TaxNodeDomain(++nodeIdx, nodeName, parent.getRank()+1, parent.getTax_id(), distance);
        tree.addNode(phyloNode);
        return phyloNode;
    }

    private int getClosingParenthesis(String strng) {

        if (!strng.trim().startsWith("(")) {
            throw new IllegalArgumentException(String.format("Illegal Argument [%s] does not start with an opening parenthesis", strng));
        }

        int depth = 0;
        for (int i = 0; i < strng.length(); i++) {
            if (strng.charAt(i) == '(') {
                depth++;
            }
            if (strng.charAt(i) == ')' && (--depth == 0)) {
                return i;
            }
        }
        return -1;
    }


}