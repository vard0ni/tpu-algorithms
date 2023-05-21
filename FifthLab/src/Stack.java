public class Stack {
    Node Top;
    public Stack()
    {
        Top = null;
    }

    public void push(Object data)
    {
        var newNode = new Node(data);
        newNode.next = Top;
        Top = newNode;
    }

    public Object pop()
    {
        if (Top == null)
        {
            return null;
        }
        var currentNode = Top;
        Top = Top.next;
        return currentNode.data;
    }
}
