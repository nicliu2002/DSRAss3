import java.io.*;
import java.time.*;
import java.util.*;

/**
 * represents accounts and their relationship as a graph; 
 *
 * @author Saber Elsayed
 * @version 2, April 2022
 */
public class SocialNetwork implements SocialNetworkInterface {

    protected Graph sn;

    /**
     * constructs a social network analyser object by reading data files
     */
    public SocialNetwork() {
        sn = new Graph();

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SocialNetwork driver = new SocialNetwork();

    }

    @Override
    public void processFile()
    {
        try { //processing file needs to have a try-catch
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            File myFile = new File("data.txt");
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] splitData = data.split("\t|,"); //index 0-3 profile data, index 4 onwards is friends data (friend IDs)
                sn.addNode(Integer.parseInt(splitData[0]), splitData[1], LocalDate.parse(splitData[2]), splitData[3]);
            }
            String t = br.readLine();
            while (t != null)
            {
                String[] splitData = t.split("\t|,");
                for (int i = 4; i < splitData.length; i++)
            {
                sn.addEdge(sn.nodeList.get(Integer.parseInt(splitData[0])), sn.nodeList.get(Integer.parseInt(splitData[i].replace(" ", ""))));
                //Make edges from every node in the list
            }
                t = br.readLine();
        }
    } catch( FileNotFoundException e)

    {
        e.printStackTrace();
    } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public List<Node> suggestFriends(Node currentPerson) {
        List<Node> N = new ArrayList<>();
        for (Edge i : sn.getNeighbors(currentPerson)) { //gets all the friends of currentPerson
            Node friend = i.getFriend();
            for (Edge j : sn.getNeighbors(friend)) { //gets all the friends of friend
                Node possibleSuggest = j.getFriend();
                if (!friend.equals(possibleSuggest) && possibleSuggest.getSuburb() == currentPerson.getSuburb() && !N.contains(possibleSuggest)){
                    //checks friend is not same friend + they live in the same suburb
                    N.add(possibleSuggest);
                }
            }
        }
        return N;
    }

    @Override
    public String remindBDEvents(Node currentPerson) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        StringBuilder out = new StringBuilder();
        LocalDate today = LocalDate.now();
        out.append("Hey ").append(currentPerson.getName()).append(": \n");
        for (Edge i : sn.getNeighbors(currentPerson)) { //gets all the friends of x
            Node friend = i.getFriend();
            queue.add(friend);
        }
        while(!queue.isEmpty()){
            Node person = queue.remove();
            LocalDate birthday = person.getDateOB();
            LocalDate nextBDay = birthday.withYear(today.getYear());
            if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
                nextBDay = nextBDay.plusYears(1);
            }
            Period p = Period.between(today, nextBDay);
            out.append(person.getName()).append(" has their birthday in ").append(p.getMonths()).append(" months, and ").append(p.getDays()).append(" days until their next birthday. \n");
        }
        return out.toString();
    }

    @Override
    public List<String> getMutualFriends(Node x, Node y)
    {
        List<String> list = new ArrayList<>();
        for (Edge i : sn.getNeighbors(x)) { //gets all the friends of x
            Node friend1 = i.getFriend();
            for (Edge j : sn.getNeighbors(y)) { //gets all the friends of y
                Node friend2 = i.getFriend();
                if (friend1 == friend2 && !list.contains(friend1.getName())) {
                    list.add(friend1.getName());
                }
            }
        }
        return list;
    }
}
