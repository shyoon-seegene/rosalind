package bioinfomatics_stronghold;

import com.seegene.insilico.platform.core.bioutil.domain.TaxPathDomain;
import com.seegene.insilico.platform.core.bioutil.domain.TaxTreeDomain;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TRIE {

    public static void main(String[] args) throws IOException {

        Map<Integer, Node> tree = new HashMap<>();
        Map<Integer, List<Integer>> childMap = new HashMap<>();

        List<String> lines = IOUtils.readLines(new FileReader("C:/dev/rosalind/stronghold/rosalind_trie.txt"));
        Node root = new Node();
        root.setId(1);
        root.setRank(0);
        root.setPid(1);
        root.setName("root");
        tree.put(1, root);
        int idOrder = 1;
        for(String line : lines){

            Node pNode = tree.get(1);
            for(char c : line.toCharArray()){
                List<Integer> childList = childMap.getOrDefault(pNode.getId(), new ArrayList<>());

                boolean exist = false;
                for(Integer id : childList){
//                    System.out.println(tree.get(id).getName() + "\t" + c);
                    if(tree.get(id).getName().equals(c+"")){
                        pNode = tree.get(id);
                        exist = true;
                        break;
                    }
                }
                if(exist) continue;
                else{
                    idOrder++;
                    Node node = new Node();
                    node.setId(idOrder);
                    node.setRank(pNode.getRank()+1);
                    node.setPid(pNode.getId());
                    node.setName(c+"");
                    tree.put(idOrder, node);
                    childMap.computeIfAbsent(node.getPid(), v -> new ArrayList<>()).add(idOrder);
                    pNode = node;
                }
            }
        }
        for(Integer key : tree.keySet()){
            Node n = tree.get(key);
            n.print();
        }

    }

    static class Node{

        private int rank = 0;
        private int id = -1;
        private int pid = -1;
        private String name = null;

        public void print(){
            System.out.println(pid + " " + id + " " + name);
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
