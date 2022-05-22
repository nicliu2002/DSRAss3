public class Edge
{
    protected Node friend;


    public Edge(Node friend)
    {
        this.friend = friend;
    }

    public Node getFriend() {
        return friend;
    }

    public String getFriendName(){
        return friend.getName();
    }

    @Override
    public String toString() {
        return "friend = " + friend;
    }

    public static void main (String[] args)
    {

    }
}
