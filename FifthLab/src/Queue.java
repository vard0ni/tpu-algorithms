public class Queue {
    Node first;
    Node oldlast;
    Node last;
    public Queue()
    {
        first = null;
        oldlast = null;
        last = null;
    }
    public Object pop()
    {
        if (first == null)
        {
            return null;
        }
        var currentNode = first;
        first = first.next;
        return currentNode.data;
    }
    public void push(Object data)
    {
        oldlast = last;
        last = new Node(data);
        last.next = null;
        if (first == null)
        {
            first = last;
        }
        else oldlast.next = last;
    }
}
