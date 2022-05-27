
import java.io.File;
import java.io.FileNotFoundException;
import java.time.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a vertex in the graph with its adjacency list of edges.
 *
 * @version 2.0, April 2022
 * @author Saber Elsayed
 */
class Node implements NodeInteface,Comparable<Node>
{

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

    /**
     * Construct a new vertex in the graph with the supplied id, name, DOB and
     * suburb.
     */


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSuburb() {
        return suburb;
    }

    @Override
    public LocalDate getDateOB() {
        return dateOB;
    }

    @Override
    public String toString() {
        return "Node {id = " + id + " , name = " + name + " , DOB = " + dateOB + " , suburb = " + suburb + "}";
    }

    public HashMap<Integer, Edge> getAdj() {
        return adj;
    }

    @Override
    public int compareTo(Node other) {
        LocalDate today = LocalDate.now();
        LocalDate thisBirthday = this.dateOB;
        LocalDate thisNextBDay = thisBirthday.withYear(today.getYear());
        if (thisNextBDay.isBefore(today) || thisNextBDay.isEqual(today)) {
            thisNextBDay = thisNextBDay.plusYears(1);
        }
        Duration thisD = Duration.between(today, thisNextBDay);
        LocalDate otherBirthday = other.dateOB;
        LocalDate otherNextBDay = otherBirthday.withYear(today.getYear());
        if (otherNextBDay.isBefore(today) || otherNextBDay.isEqual(today)) {
            otherNextBDay = otherNextBDay.plusYears(1);
        }
        Duration otherD = Duration.between(today, otherNextBDay);
        return thisD.compareTo(otherD) * -1; //if you test it, and it goes from furthest away to closest just delete -1
    }


    /**
     * overrides a hash code value for the object. This method is supported for
     * the benefit of hash tables such as those provided by HashMap.
     *
     * @return hash code value for a Node object
     */

    @Override
    public int hashCode() {
        id = getId();
        int hash = (id ^ 2) % 42061;
        return hash;

        // get index into the table
        // reduce collisions and make it fast
        //getId();   // gets unique ID
        //id% 1003 // collison 2 0-1002 size of table     // using just ID wasting a bit of memory
        // insert one by one into a set
        // check size of set against hashmap
        // unique for each
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id.equals(node.id) && name.equals(node.name) && dateOB.equals(node.dateOB) && suburb.equals(node.suburb) && Objects.equals(adj, node.adj);
    }

    /*
    /**
     * Indicates whether another object is "equal to" this one or not
     *
     * @param obj - the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false
     * otherwise.
     */
/*
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
       }
         */



    public static void main(String args[])
    {
        Node node = new Node(2, "Bob", LocalDate.parse("2022-02-23"), "Deakin");
        System.out.println(node.toString());

        /*
        File myFile = new File("data.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String[] splitData = data.split("\t|,|\\s"); //index 0-3 profile data, index 4 onwards is friends data
            for (String[1])

         */
        }
    }


