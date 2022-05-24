
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * constructs an undirected graph with some basic operations: addNode,
 * removeNode, addEdge, getNeighbors, etc.
 *
 * @author Saber Elsayed
 * @version 2.0, April 2022
 * @see Edge
 * @see Node
 */
public class Graph implements GraphInterface {

    /**
     * holds all nodes (people) in this graph
     */
    protected HashMap<Integer, Node> nodeList = new HashMap<>();

    /**
     * Test main that creates a graph,
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Graph g = new Graph();
    }

    @Override
    public Node addNode(Integer id, String name, LocalDate dob, String suburb)
    {
        Node newNode = new Node(id, name, dob, suburb);
        nodeList.put(newNode.hashCode(), newNode); //we will implement some form of hashCode function later, rn it just pulls ID
        return newNode;
    }

    @Override
    public void addEdge(Node from, Node to) {
        Edge e = new Edge(to);
        from.adj.put(to.hashCode(),e);  // from ---> to
        e = new Edge(from);
        to.adj.put(from.hashCode(),e);   // from <--- to
    }

    @Override
    public void removeEdge(Node from, Node to) {
        from.adj.remove(from.hashCode());
        to.adj.remove(to.hashCode());
    }

    @Override
    public void removeNode(Node node) {
        nodeList.remove(node.hashCode());
    }
    @Override
    public Set<Edge> getNeighbors(Node node) {
        return (Set<Edge>)node.adj.values();
    }

    private int hashCode(int inputID){ //converts from ID to hashCode
        return inputID;
    }

    public Node getNodeFromID(int id){
        return nodeList.get(hashCode(id));
    }

    public String toString(){
        StringBuilder out = new StringBuilder();
        for (Node person : nodeList.values()){
            out.append(person.getName()).append(" | ");
            Collection<Edge> friends = person.getAdj().values();
            for (Edge bloke: friends) {
                out.append(bloke.getFriendName());
            }
            out.append(System.getProperty("line.separator"));
        }
        return out.toString();
    }
}