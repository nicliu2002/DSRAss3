
import java.time.*;
import java.util.HashMap;

/**
 * Represents a vertex in the graph with its adjacency list of edges.
 *
 * @version 2.0, April 2022
 * @author Saber Elsayed
 */
class Node implements NodeInteface,Comparable<Node>  {

    //id
    private Integer id;
    //person name
    private String name;

    //date of birth
    private LocalDate dateOB;
    //suburb a person lives at
    private String suburb;

    //contains a list of all friends of a person object
    protected HashMap<Integer, Edge> adj;


    public Node(Integer id, String name, LocalDate dob, String suburb) {
        this.id = id;
        this.name = name;
        this.dateOB = dob;
        this.suburb = suburb;
        adj = new HashMap<>();
    }

    @Override
    public int hashCode()
    { //we can revise this later but for now F(i) = i
        return(this.id);
    }

    /**
     * Construct a new vertex in the graph with the supplied id, name, DOB and
     * suburb.
     *
     */



    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getSuburb()
    {
        return suburb;
    }

    @Override
    public LocalDate getDateOB()
    {
        return dateOB;
    }

    @Override
    public String toString()
    {
        return "Node {id = " + id + " , name = " + name + " , DOB = " + dateOB + " , suburb = " + suburb + "}";
    }

    public HashMap<Integer, Edge> getAdj() {
        return adj;
    }

    @Override
    public int compareTo(Node other){
        LocalDate today = LocalDate.now();
        LocalDate thisBirthday = this.dateOB;
        LocalDate thisNextBDay = thisBirthday.withYear(today.getYear());
        if (thisNextBDay.isBefore(today) || thisNextBDay.isEqual(today)) {
            thisNextBDay = thisNextBDay.plusYears(1);
        }
        Duration thisD = Duration.between(today, thisNextBDay);
        LocalDate otherBirthday = this.dateOB;
        LocalDate otherNextBDay = otherBirthday.withYear(today.getYear());
        if (otherNextBDay.isBefore(today) || otherNextBDay.isEqual(today)) {
            otherNextBDay = otherNextBDay.plusYears(1);
        }
        Duration otherD = Duration.between(today, otherNextBDay);
        return thisD.compareTo(otherD)*-1;
    }

    public static void main (String args[])
    {
        Node node = new Node(2, "Bob", LocalDate.parse("2022-02-23"), "Deakin" );
        System.out.println(node.toString());
    }
}
