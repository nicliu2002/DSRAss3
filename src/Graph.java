
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
    public Node addNode(Integer id, String name, LocalDate dob, String suburb) {
        Node newNode = new Node(id, name, dob, suburb);
        nodeList.put(newNode.hashCode(), newNode);
        return null;
    }

    @Override
    public void addEdge(Node from, Node to) {
        Edge e1 = new Edge(to);
        from.adj.put(from.hashCode(),e1);  // from ---> to
        Edge e2 = new Edge(from);
        to.adj.put(to.hashCode(),e2);   // from <--- to
    }

    @Override
    public void removeEdge(Node from, Node to) {
        from.adj.remove(from.getId());
        to.adj.remove(to.getId());
    }

    @Override
    public void removeNode(Node node) {
        nodeList.remove(node.getId());
    }

    @Override
    public Set<Edge> getNeighbors(Node node) {
        return (Set<Edge>)node.adj.values();
    }
}