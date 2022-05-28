
import javax.management.InstanceAlreadyExistsException;
import java.time.LocalDate;
import java.util.*;


/**
 * constructs an undirected graph with some basic operations: addNode,
 * removeNode, addEdge, getNeighbors, etc.
 *
 * @author Tim McCrossin and Nicholas Liu (aka Chinese God)
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
        Node newNode = null;
       try {
           newNode = new Node(id, name, dob, suburb);
           Node s = nodeList.putIfAbsent(newNode.getId(), newNode);
           if (s != null) {
               throw new InstanceAlreadyExistsException("This node already exists");
           } else {
           }
       } catch (Exception e)
       {
           System.out.println(e.getMessage());
        }
        return newNode;
    }

    @Override
    public void addEdge(Node from, Node to) {
        Edge e = new Edge(to);
        if (!(to.getId() == null)) {
            if (!(from.adj.containsValue(e))) {
                from.adj.put(to.getId(), e);  // from ---> to
            } else {
                throw new IllegalArgumentException("edge exists already");
            }
            e = new Edge(from);
            if (!(to.adj.containsValue(e))) {
                to.adj.put(from.getId(), e);   // from <--- to
            } else {
                throw new IllegalArgumentException("edge exists already");
            }
        } else {
            throw new IllegalArgumentException("The node is null");
        }
    }

    @Override
    public void removeEdge(Node from, Node to)
    {
        if (!nodeList.containsKey(from.getId()) || !nodeList.containsKey(to.getId()))
        {
            throw new NoSuchElementException("Edge does not exist");
        }
        else
        {
            nodeList.get(from.getId()).adj.remove(to.getId());
            nodeList.get(to.getId()).adj.remove(from.getId());
        }
    }

    @Override
    public void removeNode(Node node)
    {
        if (!nodeList.containsKey(node.getId()))
        {
            throw new IllegalArgumentException("This Node does not exists");
        }
        nodeList.remove(node.getId(), node);
        for (int i =0; i< nodeList.size(); i++)
        {
            Edge temporary = new Edge(nodeList.get(i));
            if (node.adj.containsValue(temporary))
            {
                removeEdge(node, nodeList.get(i));
            }
        }
    }

    @Override
    public Set<Edge> getNeighbors(Node node)
    {
        if (!nodeList.containsKey(node.getId()))
        {
            throw new NoSuchElementException("Nodes are not in the graph!");
        }
        Set <Edge> neighbours = new HashSet<>(nodeList.get(node.getId()).adj.values());
        return neighbours;
    }


    private int hashCode(int inputID) { //converts from ID to hashCode
        return inputID;
    }

    public Node getNodeFromID(int id) {
        return nodeList.get(hashCode(id));
    }

     

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Node person : nodeList.values()){
            out.append(person.getName()).append(" | ");
            Collection<Edge> friends = person.getAdj().values();
            for (Edge bloke: friends) {
                //out.append(bloke.getFriendName()); // does not seem to be working
            }
            out.append(System.getProperty("line.separator"));
        }
        return out.toString();
    }
}